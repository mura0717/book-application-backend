package dat3.book_app.service.openAI;

import dat3.book_app.dto.openai.response.OpenAiResponse;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class OpenAiBookService {
    private final IOpenAIRequest _httpRequest;
    private final BookApiPrompts _bookPromptMessages;
    public OpenAiBookService(IOpenAIRequest httpRequest, BookApiPrompts bookPromptMessages) {
        _httpRequest = httpRequest;
        _bookPromptMessages = bookPromptMessages;
    }

    public String bookSummary(String author, String title, int length){
        var prompt = _bookPromptMessages.summary(author,title,length);
        var content = _httpRequest.request(prompt);
        return getText(content);
    }

    public List<String> recommendedBooks(String description, int maxResults){
        if(description == null)
            return new ArrayList<>(){
                {
                    add("Description is not present");
                }
            };
        var prompt = _bookPromptMessages.similarBooks(description, maxResults);
        var content = _httpRequest.request(prompt);
        var result = getText(content);
        return formatResult(result);
    }

    public List<String> recommendedBooks(String author, String title, int maxResults){
        var prompt = _bookPromptMessages.similarBooks(author,title, maxResults);
        var content = _httpRequest.request(prompt);
        var result = getText(content);
        return formatResult(result);
    }

    private String getText(OpenAiResponse content){
        var choices = content.getChoices();
        var first = choices.stream().findFirst().orElse(null);
        return first != null ? first.getText() : null;
    }

    private List<String> formatResult(String src){
        var strings = new ArrayList<String>();
        var scan = new Scanner(src).useDelimiter(";");
        while (scan.hasNext()){
            var str = scan.next();
            var nStr = str.replace("\n","");
            strings.add(nStr);
        }
        return strings;
    }
}
