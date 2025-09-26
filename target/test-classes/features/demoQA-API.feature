# language: pt

Funcionalidade: Fluxo Completo da API DemoQA BookStore

  #Principal scenario
  Cenário: Fluxo completo de registro de usuário e aluguel de livros
    Dado que eu crio um novo usuário com credenciais válidas
    Quando eu gero um token de acesso para o usuário
    Então o usuário deve estar autorizado
    Quando eu recupero a lista de livros disponíveis
    E eu adiciono dois livros aleatórios à coleção do usuário
    Então os detalhes do usuário devem mostrar os livros selecionados
    E a coleção deve conter exatamente 2 livros

#  Cenário: Fluxo completo com validações detalhadas
#    Dado que eu crio um usuário na plataforma DemoQA
#    Quando eu solicito um token de autenticação
#    Então a autorização do usuário deve ser confirmada com sucesso
#    Quando eu consulto os livros disponíveis na livraria
#    E eu seleciono e adiciono dois livros à minha coleção
#    Então meu perfil deve exibir os livros alugados corretamente
#
#  Esquema do Cenário : Fluxo com diferentes tipos de livros
#    Dado que eu tenho um usuário criado com token válido
#    Quando eu busco livros disponíveis
#    E eu adiciono os livros <livro1> e <livro2> à minha coleção
#    Então minha coleção deve conter exatamente os livros adicionados
#      Exemplos:
#      | livro1       | livro2           |
#      | Programming  | Design Patterns  |
#      | JavaScript   | API Testing      |
#
#  Cenário: Tentativa de adicionar livros sem autenticação
#    Dado que eu crio um novo usuário
#    Quando eu tento adicionar livros sem token de acesso
#    Então a operação deve retornar erro de não autorizado
#
#  Cenário: Validação da estrutura dos dados do usuário
#    Dado que eu completo o fluxo de registro e aluguel de livros
#    Quando eu consulto os detalhes do usuário
#    Então os dados devem conter:
#    | Campo        | Tipo     | Obrigatório |
#    | userID       | String   | sim         |
#    | username     | String   | sim         |
#    | books        | List     | sim         |
#    | books[].isbn | String   | sim         |
#    | books[].title| String   | sim         |