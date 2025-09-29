package com.accenture.demoqa.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class CreateUserResponse {
    @JsonProperty("userID") 
    private String userId;

    private String username;
    private List<Object> books;

    // Getter correto
    public String getUserId() {
        return userId;
    }
}
