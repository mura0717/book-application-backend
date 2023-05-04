package dat3.book_app.service.openAI;

import dat3.book_app.entity.bookRecommendations.BookRecommendation;
import dat3.book_app.entity.bookRecommendations.BookRecommendations;
import dat3.book_app.entity.openAI.OpenAiResponse;
import dat3.book_app.utils.JsonDeserializer;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class OpenAiBookService implements AIBookService {
    private final IOpenAIRequest _httpRequest;
    private final BookApiPrompts _bookPromptMessages;
    public OpenAiBookService(IOpenAIRequest httpRequest, BookApiPrompts bookPromptMessages) {
        _httpRequest = httpRequest;
        _bookPromptMessages = bookPromptMessages;
    }

    @Override
    public String bookSummary(String author, String title, int length){
        var prompt = _bookPromptMessages.summary(author,title,length);
        var content = _httpRequest.request(prompt);
        return getContent(content);
    }

    @Override
    public List<BookRecommendation> recommendedBooks(String description, int maxResults){
        if(description == null)
            return new ArrayList<>();
        var prompt = _bookPromptMessages.similarBooks(description, maxResults);
        var content = _httpRequest.request(prompt);
        var result = getContent(content);
        return fromJson(result);
    }

    @Override
    public List<BookRecommendation> recommendedBooks(String author, String title, int maxResults){
        var prompt = _bookPromptMessages.similarBooks(author,title, maxResults);
        var content = _httpRequest.request(prompt);
        var result = getContent(content);
        return fromJson(result);
    }

    private String getContent(OpenAiResponse content){
        var choices = content.getChoices();
        var first = choices.stream().findFirst().orElse(null);
        return first != null ? first.getText() : null;
    }

    private List<BookRecommendation> fromJson(String json){
        var recommendations = JsonDeserializer.deserialize(json, BookRecommendations.class);
        if(recommendations == null)
            return new ArrayList<>();
        return recommendations.getRecommendations();
    }
}
