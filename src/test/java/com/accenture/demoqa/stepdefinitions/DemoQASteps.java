package com.accenture.demoqa.stepdefinitions;

import com.accenture.demoqa.api.clients.AccountClient;
import com.accenture.demoqa.api.clients.BookStoreClient;
import com.accenture.demoqa.models.request.AddBooksRequest;
import com.accenture.demoqa.models.request.CreateUserRequest;
import com.accenture.demoqa.models.request.GenerateTokenRequest;
import com.accenture.demoqa.models.response.BooksResponse;
import com.accenture.demoqa.models.response.CreateUserResponse;
import com.accenture.demoqa.models.response.TokenResponse;
import com.accenture.demoqa.models.response.UserResponse;
import com.accenture.demoqa.utils.TestDataGenerator;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;

public class DemoQASteps {

    private AccountClient accountClient = new AccountClient();
    private BookStoreClient bookStoreClient = new BookStoreClient();
    private String userName;
    private String password;
    private String userId;
    private String token;
    private BooksResponse availableBooks;
    private UserResponse userDetails;
    private Response response;

    @Dado("que eu crio um novo usuário com credenciais válidas")
    public void criarNovoUsuario() {
        this.userName = TestDataGenerator.generateUsername();
        this.password = "Test@1234";

        CreateUserRequest userRequest = new CreateUserRequest(userName, password);
        CreateUserResponse createUserResponse = accountClient.createUser(userRequest);

        Assert.assertNotNull(createUserResponse, "Response da criação não deve ser nulo");
        this.userId = createUserResponse.getUserId();
        Assert.assertNotNull(userId, "User ID não deve ser nulo");
        Assert.assertFalse(userId.isEmpty(), "User ID não deve estar vazio");

        System.out.println("Usuário criado: " + userName + " | ID: " + userId);
        System.out.println("Senha do usuario: " + userName + " | ID: " + password);
    }

    @Quando("eu gero um token de acesso para o usuário")
    public void gerarTokenAcesso() {
        GenerateTokenRequest tokenRequest = new GenerateTokenRequest(userName, password);
        TokenResponse tokenResponse = accountClient.generateToken(tokenRequest);

        Assert.assertNotNull(tokenResponse, "Response do token não deve ser nulo");
        this.token = tokenResponse.getToken();
        Assert.assertNotNull(token, "Token não deve ser nulo");
        Assert.assertFalse(token.isEmpty(), "Token não deve estar vazio");

        System.out.println("Token gerado: " + (token != null ? "SUCESSO" : "FALHA"));
    }

    @Entao("o usuário deve estar autorizado")
    public void verificarAutorizacaoUsuario() {
        GenerateTokenRequest authRequest = new GenerateTokenRequest(userName, password);
        boolean isAuthorized = accountClient.isAuthorized(authRequest);
        Assert.assertTrue(isAuthorized, "Usuário deve estar autorizado");

        System.out.println("Usuário autorizado: " + isAuthorized);
    }

    @Quando("eu recupero a lista de livros disponíveis")
    public void recuperarListaLivros() {
        this.availableBooks = bookStoreClient.getAvailableBooks();
        Assert.assertNotNull(availableBooks, "Response de livros não deve ser nulo");
        Assert.assertNotNull(availableBooks.getBooks(), "Lista de livros não deve ser nula");
        Assert.assertFalse(availableBooks.getBooks().isEmpty(), "Lista de livros não deve estar vazia");

        System.out.println("Livros encontrados: " + availableBooks.getBooks().size());
    }

    @Quando("eu adiciono dois livros aleatórios à coleção do usuário")
    public void adicionarLivrosColecao() {
//        // Limpar coleção existente primeiro (se houver)
//        try {
//            Response deleteResponse = bookStoreClient.deleteAllBooks(userId, token);
//            System.out.println("Coleção limpa: " + deleteResponse.getStatusCode());
//        } catch (Exception e) {
//            System.out.println("Nenhuma coleção para limpar ou erro: " + e.getMessage());
//        }
//
//        List<BooksResponse.Book> books = availableBooks.getBooks();
//        Assert.assertTrue(books.size() >= 2, "Deve haver pelo menos 2 livros disponíveis");
//
//        // Selecionar dois livros diferentes
//        String isbn1 = books.get(0).getIsbn();
//        String isbn2 = books.get(1).getIsbn();
//
//        // lLCriar objetos ISBN usando o construtor correto
//        AddBooksRequest.ISBN isbnObj1 = new AddBooksRequest.ISBN(isbn1);
//        AddBooksRequest.ISBN isbnObj2 = new AddBooksRequest.ISBN(isbn2);
//
//        // Criar a lista e o request
//        List<AddBooksRequest.ISBN> isbnList = List.of(isbnObj1, isbnObj2);
//        AddBooksRequest addBooksRequest = new AddBooksRequest(userId, isbnList);
//
//        response = bookStoreClient.addBooksToUser(addBooksRequest, token);
//
//        System.out.println("Status ao adicionar livros: " + response.getStatusCode());
//        System.out.println("Response: " + response.getBody().asString());
//
//        Assert.assertEquals(response.getStatusCode(), 201, "Livros devem ser adicionados com sucesso");
    }

    @Entao("os detalhes do usuário devem mostrar os livros selecionados")
    public void verificarDetalhesUsuarioComLivros() {
//        userDetails = accountClient.getUserDetails(userId, token);
//
//        Assert.assertNotNull(userDetails, "Detalhes do usuário não devem ser nulos");
//        Assert.assertNotNull(userDetails.getBooks(), "Usuário deve ter coleção de livros");
//        Assert.assertEquals(userDetails.getBooks().size(), 2, "Usuário deve ter exatamente 2 livros");
//
//        System.out.println("Livros na coleção do usuário: " + userDetails.getBooks().size());
//        userDetails.getBooks().forEach(book ->
//                System.out.println(" - " + book.getTitle() + " (ISBN: " + book.getIsbn() + ")")
//        );
    }

    @Então("a coleção deve conter exatamente {int} livros")
    public void a_coleção_deve_conter_exatamente_livros(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}