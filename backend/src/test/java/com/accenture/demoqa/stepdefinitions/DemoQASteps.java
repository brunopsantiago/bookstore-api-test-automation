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
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
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
        System.out.println("=== INICIANDO CRIAÇÃO DE USUÁRIO ===");

        this.userName = TestDataGenerator.generateUsername();
        this.password = "Test@1234";

        System.out.println("Username gerado: " + userName);
        System.out.println("Password: " + password);

        CreateUserRequest userRequest = new CreateUserRequest(userName, password);
        System.out.println("Request Body: " + userRequest.toString());

        CreateUserResponse createUserResponse = accountClient.createUser(userRequest);

        Assert.assertNotNull(createUserResponse, "Response da criação não deve ser nulo");
        this.userId = createUserResponse.getUserId();
        Assert.assertNotNull(userId, "User ID não deve ser nulo");
        Assert.assertFalse(userId.isEmpty(), "User ID não deve estar vazio");

        System.out.println("✅ Usuário criado com sucesso!");
        System.out.println("📝 Username: " + userName);
        System.out.println("🆔 UserID: " + userId);
        System.out.println("🔐 Password: " + password);
        System.out.println("=== FIM CRIAÇÃO DE USUÁRIO ===");
    }

    @Quando("eu gero um token de acesso para o usuário")
    public void gerarTokenAcesso() {
        System.out.println("=== GERANDO TOKEN DE ACESSO ===");
        System.out.println("Username: " + userName);
        System.out.println("Password: " + password);

        try {
            GenerateTokenRequest tokenRequest = new GenerateTokenRequest(userName, password);
            System.out.println("Request Body: " + tokenRequest.toString());

            TokenResponse tokenResponse = accountClient.generateToken(tokenRequest);

            Assert.assertNotNull(tokenResponse, "Response do token não deve ser nulo");
            this.token = tokenResponse.getToken();
            Assert.assertNotNull(token, "Token não deve ser nulo");
            Assert.assertFalse(token.isEmpty(), "Token não deve estar vazio");

            System.out.println("✅ Token gerado com sucesso!");
            System.out.println("🔑 Token: " + token);
            System.out.println("Status: " + tokenResponse.getStatus());
            System.out.println("Result: " + tokenResponse.getResult());

        } catch (Exception e) {
            System.out.println("❌ Erro ao gerar token: " + e.getMessage());
            throw e;
        }
        System.out.println("=== FIM GERAÇÃO DE TOKEN ===");
    }


    @Entao("o usuário deve estar autorizado")
    public void verificarAutorizacaoUsuario() {
        System.out.println("=== VERIFICANDO AUTORIZAÇÃO DO USUÁRIO ===");
        System.out.println("UserID: " + userId);
        System.out.println("Token: " + token);
        System.out.println("Username: " + userName);

        // Método 1: Verificar via GET /User/{UUID} (com token)
        boolean isAuthorized = accountClient.isAuthorized(userId, token);

        if (isAuthorized) {
            // Se autorizado, busca os detalhes completos do usuário
            userDetails = accountClient.getUserDetails(userId, token);
            Assert.assertNotNull(userDetails, "Detalhes do usuário não devem ser nulos");

            System.out.println("✅ Usuário autorizado com sucesso!");
            System.out.println("📋 Detalhes do usuário:");
            System.out.println("   UserID: " + userDetails.getUserId());
            System.out.println("   Username: " + userDetails.getUsername());
            System.out.println("   Quantidade de livros: " +
                    (userDetails.getBooks() != null ? userDetails.getBooks().size() : 0));
        } else {
            System.out.println("❌ Usuário NÃO autorizado via GET /User/{UUID}!");

            // Método 2: Tentar verificar via POST /Authorized (com credenciais)
            System.out.println("🔄 Tentando verificar via POST /Authorized...");
            boolean isAuthorizedPost = bookStoreClient.verifyAuthorization(userName, password);
            System.out.println("✅ Autorizado via POST /Authorized: " + isAuthorizedPost);
        }

        Assert.assertTrue(isAuthorized, "Usuário deve estar autorizado");
        System.out.println("=== FIM VERIFICAÇÃO DE AUTORIZAÇÃO ===");
    }

    @Quando("eu recupero a lista de livros disponíveis")
    public void recuperarListaLivros() {
        System.out.println("=== RECUPERANDO LISTA DE LIVROS ===");

        this.availableBooks = bookStoreClient.getAvailableBooks();
        Assert.assertNotNull(availableBooks, "Response de livros não deve ser nulo");
        Assert.assertNotNull(availableBooks.getBooks(), "Lista de livros não deve ser nula");
        Assert.assertFalse(availableBooks.getBooks().isEmpty(), "Lista de livros não deve estar vazia");

        System.out.println("✅ Lista de livros recuperada com sucesso!");
        System.out.println("📚 Total de livros disponíveis: " + availableBooks.getBooks().size());

        // Log dos primeiros 3 livros para verificação
        availableBooks.getBooks().stream().limit(3).forEach(book ->
                System.out.println("   📖 " + book.getTitle() + " (ISBN: " + book.getIsbn() + ")")
        );
        System.out.println("=== FIM LISTA DE LIVROS ===");
    }

    @Quando("eu adiciono dois livros aleatórios à coleção do usuário")
    public void adicionarLivrosColecao() {
        System.out.println("=== ADICIONANDO LIVROS À COLEÇÃO ===");
        System.out.println("UserID: " + userId);
        System.out.println("Token: " + token);

        // Primeiro verifica se tem livros disponíveis
        Assert.assertNotNull(availableBooks, "Lista de livros não deve ser nula");
        Assert.assertTrue(availableBooks.getBooks().size() >= 2,
                "Deve haver pelo menos 2 livros disponíveis");

        List<BooksResponse.Book> books = availableBooks.getBooks();

        // Selecionar dois livros diferentes
        String isbn1 = books.get(0).getIsbn();
        String isbn2 = books.get(1).getIsbn();

        System.out.println("📖 Livro 1 selecionado - ISBN: " + isbn1 + " - Título: " + books.get(0).getTitle());
        System.out.println("📖 Livro 2 selecionado - ISBN: " + isbn2 + " - Título: " + books.get(1).getTitle());

        // Criar objetos ISBN usando o construtor correto
        AddBooksRequest.ISBN isbnObj1 = new AddBooksRequest.ISBN(isbn1);
        AddBooksRequest.ISBN isbnObj2 = new AddBooksRequest.ISBN(isbn2);

        // Criar a lista e o request
        List<AddBooksRequest.ISBN> isbnList = List.of(isbnObj1, isbnObj2);
        AddBooksRequest addBooksRequest = new AddBooksRequest(userId, isbnList);

        System.out.println("Request Body: " + addBooksRequest.toString());

        response = bookStoreClient.addBooksToUser(addBooksRequest, token);

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 201, "Livros devem ser adicionados com sucesso");
        System.out.println("✅ Livros adicionados com sucesso à coleção do usuário!");
        System.out.println("=== FIM ADIÇÃO DE LIVROS ===");
    }

    @Entao("os detalhes do usuário devem mostrar os livros selecionados")
    public void verificarDetalhesUsuarioComLivros() {
        System.out.println("=== VERIFICANDO DETALHES DO USUÁRIO COM LIVROS ===");

        userDetails = accountClient.getUserDetails(userId, token);

        Assert.assertNotNull(userDetails, "Detalhes do usuário não devem ser nulos");
        Assert.assertNotNull(userDetails.getBooks(), "Usuário deve ter coleção de livros");
        Assert.assertEquals(userDetails.getBooks().size(), 2, "Usuário deve ter exatamente 2 livros");

        System.out.println("✅ Coleção verificada com sucesso!");
        System.out.println("📚 Livros na coleção do usuário: " + userDetails.getBooks().size());
        userDetails.getBooks().forEach(book ->
                System.out.println("   📖 " + book.getTitle() + " (ISBN: " + book.getIsbn() + ")")
        );
        System.out.println("=== FIM VERIFICAÇÃO DE LIVROS ===");
    }

    @Então("a coleção deve conter exatamente {int} livros")
    public void a_coleção_deve_conter_exatamente_livros(Integer quantidadeEsperada) {
        System.out.println("=== VERIFICANDO QUANTIDADE DE LIVROS ===");
        System.out.println("Quantidade esperada: " + quantidadeEsperada);

        userDetails = accountClient.getUserDetails(userId, token);

        Assert.assertNotNull(userDetails, "Detalhes do usuário não devem ser nulos");
        Assert.assertNotNull(userDetails.getBooks(), "Coleção de livros não deve ser nula");

        int quantidadeAtual = userDetails.getBooks().size();
        System.out.println("Quantidade atual na coleção: " + quantidadeAtual);

        Assert.assertEquals(quantidadeAtual, quantidadeEsperada.intValue(),
                "A coleção deve conter exatamente " + quantidadeEsperada + " livros");

        System.out.println("✅ Quantidade de livros verificada com sucesso!");
        System.out.println("=== FIM VERIFICAÇÃO DE QUANTIDADE ===");
    }
}