package com.accenture.demoqa.models.request;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBooksRequest {
    private String userId;
    private List<ISBN> collectionOfIsbns;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ISBN {
        private String isbn;
    }
}