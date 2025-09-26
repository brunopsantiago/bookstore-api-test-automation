package com.accenture.demoqa.api.clients;

import com.accenture.demoqa.api.endpoints.BookStoreEndpoints;
import com.accenture.demoqa.models.request.AddBooksRequest;
import com.accenture.demoqa.models.response.BooksResponse;
import com.accenture.demoqa.utils.Config;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookStoreClient {

    public BooksResponse getAvailableBooks() {
        Response response = given()
                .baseUri(Config.BASE_URL)
                .when()
                .get(BookStoreEndpoints.BOOKS);

        if (response.getStatusCode() == 200) {
            return response.as(BooksResponse.class);
        } else {
            throw new RuntimeException("Falha ao obter livros: " + response.getBody().asString());
        }
    }

    public Response addBooksToUser(AddBooksRequest addBooksRequest, String token) {
        return given()
                .baseUri(Config.BASE_URL)
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(addBooksRequest)
                .when()
                .post(BookStoreEndpoints.ADD_BOOKS);
    }

    // Método adicional para limpar coleção (útil para testes)
    public Response deleteAllBooks(String userId, String token) {
        return given()
                .baseUri(Config.BASE_URL)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .delete(BookStoreEndpoints.DELETE_BOOKS);
    }
}