package dat3.book_app.service.openAI.requests;

import dat3.book_app.dto.openai.requests.OpenAiDavinciPrompt;
import dat3.book_app.entity.openAI.OpenAiResponse;

public interface IOpenAIRequest<TPromptResult> {
    OpenAiResponse request(TPromptResult request);
}
