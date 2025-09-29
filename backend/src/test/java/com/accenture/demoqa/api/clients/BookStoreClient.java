package com.accenture.demoqa.api.clients;

import com.accenture.demoqa.models.request.AddBooksRequest;
import com.accenture.demoqa.models.response.BooksResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookStoreClient {

    private static final String BASE_URL = "https://demoqa.com";

    public BooksResponse getAvailableBooks() {
        System.out.println("=== RECUPERANDO LIVROS DISPONÍVEIS ===");
        System.out.println("📤 REQUEST:");
        System.out.println("URL: GET " + BASE_URL + "/BookStore/v1/Books");
        System.out.println("Headers: accept=application/json");

        Response response = given()
                .baseUri(BASE_URL)
                .header("accept", "application/json")
                .when()
                .get("/BookStore/v1/Books");

        // LOG COMPLETO DA RESPOSTA
        System.out.println("📥 RESPONSE:");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Headers: " + response.getHeaders());
        System.out.println("=== FIM RECUPERAÇÃO DE LIVROS ===");

        return response.as(BooksResponse.class);
    }

    public Response addBooksToUser(AddBooksRequest request, String token) {
        System.out.println("=== ADICIONANDO LIVROS AO USUÁRIO ===");
        System.out.println("📤 REQUEST:");
        System.out.println("URL: POST " + BASE_URL + "/BookStore/v1/Books");
        System.out.println("Headers: accept=application/json, Content-Type=application/json, Authorization=Bearer " + (token != null ? token.substring(0, Math.min(token.length(), 20)) + "..." : "null"));
        System.out.println("Body: " + request.toString());

        Response response = given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(request)
                .when()
                .post("/BookStore/v1/Books");

        // LOG COMPLETO DA RESPOSTA - ESPECIALMENTE IMPORTANTE PARA ERROS 401
        System.out.println("📥 RESPONSE:");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Headers: " + response.getHeaders());

        if (response.getStatusCode() == 401) {
            System.out.println("❌ ERRO 401 - USER NOT AUTHORIZED!");
            System.out.println("💡 Possíveis causas:");
            System.out.println("   - Token inválido ou expirado");
            System.out.println("   - Token não está sendo enviado corretamente");
            System.out.println("   - UserID não corresponde ao token");
        }

        System.out.println("=== FIM ADIÇÃO DE LIVROS ===");

        return response;
    }

    // Método para verificar autorização (POST /Account/v1/Authorized)
    public boolean verifyAuthorization(String userName, String password) {
        System.out.println("=== VERIFICANDO AUTORIZAÇÃO (POST) ===");
        System.out.println("📤 REQUEST:");
        System.out.println("URL: POST " + BASE_URL + "/Account/v1/Authorized");
        System.out.println("Headers: accept=application/json, Content-Type=application/json");
        System.out.println("Body: {\"userName\": \"" + userName + "\", \"password\": \"" + password + "\"}");

        Response response = given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .body("{\"userName\": \"" + userName + "\", \"password\": \"" + password + "\"}")
                .when()
                .post("/Account/v1/Authorized");

        // LOG COMPLETO DA RESPOSTA
        System.out.println("📥 RESPONSE:");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Headers: " + response.getHeaders());
        System.out.println("=== FIM VERIFICAÇÃO AUTORIZAÇÃO (POST) ===");

        return response.getStatusCode() == 200 && Boolean.TRUE.equals(response.as(Boolean.class));
    }
}