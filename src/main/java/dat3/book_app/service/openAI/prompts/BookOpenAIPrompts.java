package dat3.book_app.service.openAI.prompts;

import dat3.book_app.dto.openai.requests.OpenAiDavinciPrompt;
import org.springframework.stereotype.Service;

@Service
public class BookOpenAIPrompts implements BookAIPrompts<OpenAiDavinciPrompt> {
    private final int MaxTokens = 300;

    @Override
    public OpenAiDavinciPrompt summary(String author, String title, int length){
        var message = String.format("""
                Give me a summary of the book '%s' by '%s' in no more than %d words
                """,title,author,length);
        return new OpenAiDavinciPrompt(MaxTokens,message);
    }

    @Override
    public OpenAiDavinciPrompt similarBooks(String description, int maxResults){
        var messages = String.format("""
                Please provide me with %d books which description is similar to the following:
                    '%s'
                Each element in the list should comprise author and title and I want the result formatted 
                as json in compliance with the following example:
                {
                    "recommendations" : [
                        {
                            "authors": ["Noam Chomsky","Bingo Leif"],
                            "title": "Fateful Triangle"
                        },
                        {
                            "authors": ["Rune Lykkeberg"],
                            "title": "Kampen om Sandhederne"
                        }
                    ]
                }
                """,maxResults,description);
        return new OpenAiDavinciPrompt(MaxTokens,messages);
    }

    @Override
    public OpenAiDavinciPrompt similarBooks(String author, String title, int maxResults){
        var messages = String.format("""
                Please provide me with %d books which is similar to the following book:
                    '%s' by %s
                Each element in the list should comprise author and title and I want the result formatted 
                as json in compliance with the following example:
                {
                    "recommendations" : [
                        {
                            "authors": ["Noam Chomsky","Bingo Leif"],
                            "title": "Fateful Triangle"
                        },
                        {
                            "authors": ["Rune Lykkeberg"],
                            "title": "Kampen om Sandhederne"
                        }
                    ]
                }
                """,maxResults,title,author);
        return new OpenAiDavinciPrompt(3000,messages);
    }
}
