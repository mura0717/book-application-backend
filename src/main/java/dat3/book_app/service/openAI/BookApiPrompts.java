package dat3.book_app.service.openAI;

import dat3.book_app.dto.openai.requests.OpenAiDavinciPrompt;
import org.springframework.stereotype.Service;

@Service
public class BookApiPrompts {
    private final int MaxTokens = 300;

    public OpenAiDavinciPrompt summary(String author, String title, int length){
        var message = String.format("""
                Give me a summary of the book '%s' by '%s' in no more than %d words
                """,title,author,length);
        return new OpenAiDavinciPrompt(MaxTokens,message);
    }

    public OpenAiDavinciPrompt similarBooks(String description, int maxResults){
        var messages = String.format("""
                Please provide me with %d books which description is similar to the following:
                    '%s'
                Each element in the list should comprise author and title and I want the result formatted 
                as json in compliance with the following example:
                [
                    {
                        "authors": ["Noam Chomsky","Bingo Leif"],
                        "title": "Fateful Triangle"
                    },
                    {
                        "authors": ["Rune Lykkeberg"],
                        "Title": "Kampen om Sandhederne"
                    }
                ]
                """,maxResults,description);
        return new OpenAiDavinciPrompt(MaxTokens,messages);
    }

    public OpenAiDavinciPrompt similarBooks(String author, String title, int maxResults){
        var messages = String.format("""
                Please provide me with %d books which is similar to the following book:
                    '%s' by %s
                Each element in the list should comprise author and title and I want the result formatted 
                as json in compliance with the following example:
                [
                    {
                        "authors": ["Noam Chomsky","Bingo Leif"],
                        "title": "Fateful Triangle"
                    },
                    {
                        "authors": ["Rune Lykkeberg"],
                        "Title": "Kampen om Sandhederne"
                    }
                """,title,maxResults,author);
        return new OpenAiDavinciPrompt(MaxTokens,messages);
    }
}
