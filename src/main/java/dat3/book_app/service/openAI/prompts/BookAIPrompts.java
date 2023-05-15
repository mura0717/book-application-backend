package dat3.book_app.service.openAI.prompts;

import dat3.book_app.dto.openai.requests.OpenAiDavinciPrompt;

public interface BookAIPrompts<TPromptResult> {
    TPromptResult summary(String author, String title, int length);

    TPromptResult similarBooks(String description, int maxResults);

    TPromptResult similarBooks(String author, String title, int maxResults);
}
