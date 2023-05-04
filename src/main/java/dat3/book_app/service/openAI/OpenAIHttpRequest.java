package dat3.book_app.service.openAI;

import dat3.book_app.dto.openai.requests.OpenAiDavinciPrompt;
import dat3.book_app.entity.openAI.OpenAiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import static reactor.core.publisher.Mono.*;

@Service
public class OpenAIHttpRequest implements IOpenAIRequest {
    private final String Uri = "https://api.openai.com/v1/completions";

    @Value("OpenAI-Api-Key")
    private String apiKey;

    @Override
    public OpenAiResponse request(OpenAiDavinciPrompt request){
        return WebClient.create()
                .post()
                .uri(Uri)
                .header("Authorization",authHeaderValue(apiKey))
                .body(just(request), OpenAiDavinciPrompt.class)
                .retrieve()
                .bodyToMono(OpenAiResponse.class)
                .block();
    }

    private String authHeaderValue(String apiToken){
        return "Bearer " + apiToken;
    }
}
