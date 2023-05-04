package dat3.book_app.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDeserializer {
    public static <T> T deserialize(String json,Class<T> descriptor){
        var mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, descriptor);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
