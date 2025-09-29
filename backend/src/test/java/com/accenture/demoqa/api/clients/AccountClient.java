package com.accenture.demoqa.api.clients;

import com.accenture.demoqa.models.request.CreateUserRequest;
import com.accenture.demoqa.models.request.GenerateTokenRequest;
import com.accenture.demoqa.models.response.CreateUserResponse;
import com.accenture.demoqa.models.response.TokenResponse;
import com.accenture.demoqa.models.response.UserResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AccountClient {

    private static final String BASE_URL = "https://demoqa.com";

    public CreateUserResponse createUser(CreateUserRequest request) {
        System.out.println("=== CRIANDO USUÁRIO NO CLIENT ===");
        System.out.println("📤 REQUEST:");
        System.out.println("URL: POST " + BASE_URL + "/Account/v1/User");
        System.out.println("Headers: accept=application/json, Content-Type=application/json");
        System.out.println("Body: " + request.toString());

        Response response = given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .body(request)
                .when()
                .post("/Account/v1/User");

        // LOG COMPLETO DA RESPOSTA
        System.out.println("📥 RESPONSE:");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Headers: " + response.getHeaders());
        System.out.println("=== FIM CRIAÇÃO DE USUÁRIO ===");

        return response.as(CreateUserResponse.class);
    }

    public TokenResponse generateToken(GenerateTokenRequest request) {
        System.out.println("=== GERANDO TOKEN NO CLIENT ===");
        System.out.println("📤 REQUEST:");
        System.out.println("URL: POST " + BASE_URL + "/Account/v1/GenerateToken");
        System.out.println("Headers: accept=application/json, Content-Type=application/json");
        System.out.println("Body: " + request.toString());

        Response response = given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .body(request)
                .when()
                .post("/Account/v1/GenerateToken");

        // LOG COMPLETO DA RESPOSTA
        System.out.println("📥 RESPONSE:");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Headers: " + response.getHeaders());
        System.out.println("=== FIM GERAÇÃO DE TOKEN ===");

        return response.as(TokenResponse.class);
    }

    public UserResponse getUserDetails(String userId, String token) {
        System.out.println("=== GET USER DETAILS ===");
        System.out.println("📤 REQUEST:");
        System.out.println("URL: GET " + BASE_URL + "/Account/v1/User/" + userId);
        System.out.println("Headers: accept=application/json, Authorization=Bearer " + (token != null ? token.substring(0, Math.min(token.length(), 20)) + "..." : "null"));

        Response response = given()
                .baseUri(BASE_URL)
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + token)
                .pathParam("UUID", userId)
                .when()
                .get("/Account/v1/User/{UUID}");

        // LOG COMPLETO DA RESPOSTA
        System.out.println("📥 RESPONSE:");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Headers: " + response.getHeaders());
        System.out.println("=== FIM GET USER DETAILS ===");

        if (response.getStatusCode() == 200) {
            return response.as(UserResponse.class);
        } else {
            System.out.println("❌ ERRO: " + response.getBody().asString());
            return null;
        }
    }

    public boolean isAuthorized(String userId, String token) {
        System.out.println("=== VERIFICANDO AUTORIZAÇÃO ===");
        System.out.println("📤 REQUEST:");
        System.out.println("URL: GET " + BASE_URL + "/Account/v1/User/" + userId);
        System.out.println("Headers: accept=application/json, Authorization=Bearer " + (token != null ? token.substring(0, Math.min(token.length(), 20)) + "..." : "null"));

        Response response = given()
                .baseUri(BASE_URL)
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + token)
                .pathParam("UUID", userId)
                .when()
                .get("/Account/v1/User/{UUID}");

        // LOG COMPLETO DA RESPOSTA
        System.out.println("📥 RESPONSE:");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Headers: " + response.getHeaders());

        boolean isAuthorized = response.getStatusCode() == 200;
        System.out.println("✅ Usuário autorizado: " + isAuthorized);
        System.out.println("=== FIM VERIFICAÇÃO AUTORIZAÇÃO ===");

        return isAuthorized;
    }
}