package com.accenture.demoqa.api.clients;

import com.accenture.demoqa.api.endpoints.AccountEndpoints;
import com.accenture.demoqa.models.request.CreateUserRequest;
import com.accenture.demoqa.models.request.GenerateTokenRequest;
import com.accenture.demoqa.models.response.CreateUserResponse;
import com.accenture.demoqa.models.response.TokenResponse;
import com.accenture.demoqa.models.response.UserResponse;
import com.accenture.demoqa.utils.Config;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AccountClient {

    public CreateUserResponse createUser(CreateUserRequest userRequest) {
        Response response = given()
                .baseUri(Config.BASE_URL)
                .contentType("application/json")
                .body(userRequest)
                .when()
                .post(AccountEndpoints.CREATE_USER);

        if (response.getStatusCode() == 201) {
            return response.as(CreateUserResponse.class);
        } else {
            throw new RuntimeException("Falha ao criar usuário: " + response.getBody().asString());
        }
    }

    public TokenResponse generateToken(GenerateTokenRequest tokenRequest) {
        Response response = given()
                .baseUri(Config.BASE_URL)
                .contentType("application/json")
                .body(tokenRequest)
                .when()
                .post(AccountEndpoints.GENERATE_TOKEN);

        if (response.getStatusCode() == 200) {
            return response.as(TokenResponse.class);
        } else {
            throw new RuntimeException("Falha ao gerar token: " + response.getBody().asString());
        }
    }

    public boolean isAuthorized(GenerateTokenRequest authRequest) {
        Response response = given()
                .baseUri(Config.BASE_URL)
                .contentType("application/json")
                .body(authRequest)
                .when()
                .post(AccountEndpoints.AUTHORIZED);

        return response.getStatusCode() == 200 && response.getBody().as(Boolean.class);
    }

    public UserResponse getUserDetails(String userId, String token) {
        Response response = given()
                .baseUri(Config.BASE_URL)
                .header("Authorization", "Bearer " + token)
                .pathParam("userId", userId)
                .when()
                .get(AccountEndpoints.USER_DETAILS);

        if (response.getStatusCode() == 200) {
            return response.as(UserResponse.class);
        } else {
            throw new RuntimeException("Falha ao obter detalhes do usuário: " + response.getBody().asString());
        }
    }
}