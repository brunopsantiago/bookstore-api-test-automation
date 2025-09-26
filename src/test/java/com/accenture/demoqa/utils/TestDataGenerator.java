package com.accenture.demoqa.utils;

import com.accenture.demoqa.models.request.AddBooksRequest;
import java.util.Arrays;
import java.util.UUID;

public class TestDataGenerator {

    public static String generateUsername() {
        return "testuser_" + UUID.randomUUID().toString().substring(0, 8);
    }

//    public static AddBooksRequest createAddBooksRequest(String userId, String isbn1, String isbn2) {
//        AddBooksRequest.CollectionOfIsbn book1 = new AddBooksRequest.CollectionOfIsbn(isbn1);
//        AddBooksRequest.CollectionOfIsbn book2 = new AddBooksRequest.CollectionOfIsbn(isbn2);
//
//        return new AddBooksRequest(
//                userId,
//                Arrays.asList(book1, book2)
//        );
//    }
}