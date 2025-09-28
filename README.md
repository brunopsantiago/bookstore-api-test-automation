# 📚 BookStore API Test Automation

<div align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![RestAssured](https://img.shields.io/badge/RestAssured-5.3.0-blue?style=for-the-badge&logo=rest)
![Cucumber](https://img.shields.io/badge/Cucumber-7.13.0-green?style=for-the-badge&logo=cucumber)
![Maven](https://img.shields.io/badge/Maven-3.9.6-red?style=for-the-badge&logo=apache-maven)
![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-✔-black?style=for-the-badge&logo=githubactions)

**Framework de Automação de API para o DemoQA BookStore**  
*Desenvolvido para o Desafio de Automação Accenture*

[![API Tests](https://github.com/brunopsantiago/bookstore-api-test-automation/actions/workflows/api-tests.yml/badge.svg)](https://github.com/brunopsantiago/bookstore-api-test-automation/actions)

</div>

## 🚀 Status do Projeto

### ✅ **IMPLEMENTADO**
- ✅ Fluxo completo de API (criação de usuário, autenticação, gestão de livros)
- ✅ Framework com RestAssured + Cucumber + TestNG
- ✅ CI/CD com GitHub Actions
- ✅ Relatórios HTML e JSON do Cucumber
- ✅ Logs detalhados de requests/responses
- ✅ Validações de status code e schema JSON

### 🚧 **EM DESENVOLVIMENTO**
- ⏳ Testes de frontend com Cypress/Playwright
- ⏳ Mais cenários de teste

## 🏃‍♂️ Execução Rápida

```bash
# Clone e execute
git clone https://github.com/brunopsantiago/bookstore-api-test-automation.git
cd bookstore-api-test-automation
mvn clean test

# Com relatórios detalhados
mvn clean test -Dcucumber.plugin="pretty,html:target/cucumber-reports/report.html"
```

### 🛠️ Tecnologias
| Tecnologia | Versão | Propósito |
|------------|--------|-----------|
| Java | 21 | Linguagem principal |
| RestAssured | 5.3.0 | Cliente HTTP para APIs REST |
| Cucumber | 7.13.0 | BDD e Gherkin |
| TestNG | 7.8.0 | Framework de testes |
| Maven | 3.9+ | Gerenciamento de dependências |
| Lombok | 1.18.30 | Redução de boilerplate code |
| Jackson | 2.15.2 | Serialização JSON |
| GitHub Actions | - | CI/CD Pipeline |

### 📊 Pipeline CI/CD
- **Trigger:** Push para main/develop e Pull Requests
- **Jobs:** Build → Test → Reports → Artifacts
- **Relatórios:** Cucumber HTML + JSON disponíveis como artifacts

### 🎯 Cenário Principal
```
Cenário: Fluxo completo de registro de usuário e aluguel de livros
  Dado que eu crio um novo usuário com credenciais válidas
  Quando eu gero um token de acesso para o usuário
  Então o usuário deve estar autorizado
  Quando eu recupero a lista de livros disponíveis
  E eu adiciono dois livros aleatórios à coleção do usuário
  Então os detalhes do usuário devem mostrar os livros selecionados
  E a coleção deve conter exatamente 2 livros
```

### 🐛 Troubleshooting
#### Erro de conexão
👉 Verifique se https://demoqa.com está online

#### Erro de dependência
mvn clean install

#### Erro de compilação
👉 Verifique JDK 21 instalado

### 📞 Contato
#### **Bruno Santiago** - brunopsantiago - bpsantiagu@gmail.com

<div align="center">
Desenvolvido para o Desafio de Automação Accenture

⬆ Voltar ao topo

</div> 
