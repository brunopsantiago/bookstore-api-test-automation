# language: pt

Funcionalidade: Web Tables - DemoQA
  Como usuário do site DemoQA
  Eu quero testar as operações CRUD na tabela web
  Para validar a funcionalidade completa de tabelas

  Cenário: Criar, editar e deletar registro na tabela
    Dado que acesso o site DemoQA
    Quando escolho a opção "Elements" na página inicial
    E clico no submenu "Web Tables"
    E crio um novo registro na tabela
    E edito o novo registro criado
    E deleto o novo registro criado

  # BÔNUS - Cenário com dados dinâmicos via Cucumber (Scenario Outline)
  Esquema do Cenário: Criar múltiplos registros dinamicamente através do cucumber
    Dado que acesso a página Web Tables
    E limpo a tabela se tiver mais de <limite> registros
    Quando crio um registro com:
      | First Name | <firstName> |
      | Last Name  | <lastName>  |
      | Email      | <email>     |
      | Age        | <age>       |
      | Salary     | <salary>    |
      | Department | <department>|
    Então o registro <firstName> <lastName> deve ser criado com sucesso
    E deleto o registro <firstName> <lastName>

    Exemplos:
      | limite | firstName | lastName | email              | age | salary | department |
      | 5      | João      | Silva    | joao@empresa.com   | 30  | 5000   | TI         |
      | 5      | Maria     | Santos   | maria@empresa.com  | 25  | 4500   | RH         |
      | 5      | Pedro     | Oliveira | pedro@empresa.com  | 35  | 6000   | Finance    |
      | 5      | Ana       | Costa    | ana@empresa.com    | 28  | 5500   | Marketing  |
      | 5      | Carlos    | Pereira  | carlos@empresa.com | 32  | 5200   | TI         |
      | 5      | Juliana   | Rodrigues| juliana@empresa.com| 27  | 4800   | RH         |
      | 5      | Paulo     | Almeida  | paulo@empresa.com  | 40  | 7000   | Finance    |
      | 5      | Fernanda  | Lima     | fernanda@empresa.com| 26 | 4700   | Marketing  |
      | 5      | Ricardo   | Souza    | ricardo@empresa.com| 33  | 5800   | TI         |
      | 5      | Amanda    | Ferreira | amanda@empresa.com | 29  | 5100   | RH         |
      | 5      | Roberto   | Martins  | roberto@empresa.com| 36  | 6200   | Finance    |
      | 5      | Patricia  | Barbosa  | patricia@empresa.com| 31 | 5400   | Marketing  |