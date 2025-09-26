package com.accenture.demoqa.models.request;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateTokenRequest {

    private String userName;
    private String password;

}