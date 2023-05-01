package dat3.book_app.service.openAI;

import dat3.book_app.dto.openai.requests.OpenAiDavinciPrompt;
import dat3.book_app.dto.openai.response.OpenAiResponse;
import dat3.book_app.service.http.HttpRequest;
import org.springframework.beans.factory.annotation.Value;

public class OpenAIHttpRequest extends HttpRequest{
    private final String Uri = "https://api.openai.com/v1/completions";

    @Value("OpenAI-Api-Key")
    private String apiKey;

    public OpenAiResponse request(OpenAiDavinciPrompt request){
        return postRequest(Uri, OpenAiDavinciPrompt.class,request,
                OpenAiResponse.class,apiKey);
    }
}
