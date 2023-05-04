package dat3.book_app.entity.openAI;

import dat3.book_app.dto.openai.response.OpenAiChoices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpenAiResponse
{
    private String id;
    private String object;
    private int created;
    private String model;
    private ArrayList<OpenAiChoices> choices;
}
