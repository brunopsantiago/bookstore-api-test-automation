# 📚 BookStore Test Automation

**Framework completo de automação para API e Frontend**

## 🎯 Visão Geral

Este projeto contém duas suítes de automação independentes:

### 🔧 [Backend API Tests](./backend/README.md)
- **Tecnologias:** Java, RestAssured, Cucumber, Maven
- **Pipeline:** [`api-tests.yml`](.github/workflows/api-tests.yml)
- **Status:** ✅ **Implementado e Funcionando**

### 🎨 [Frontend E2E Tests](./frontend/README.md)
- **Tecnologias:** Cypress, JavaScript, Node.js
- **Pipeline:** [`frontend-tests.yml`](.github/workflows/frontend-tests.yml)
- **Status:** ✅ **Implementado e Funcionando**

## 🚀 Execução Rápida

### Backend (API)
```bash
cd backend
mvn clean test
```

###  Frontend (UI)
```bash
cd frontend
npm install
npx cypress open
```
## 📊 Pipelines CI/CD

- **🔧 API Tests**: [![API Tests](https://github.com/brunopsantiago/bookstore-api-test-automation/actions/workflows/api-tests.yml/badge.svg)](https://github.com/brunopsantiago/bookstore-api-test-automation/actions/workflows/api-tests.yml)
- **🎨 Frontend Tests**: [![Frontend Tests](https://github.com/brunopsantiago/bookstore-api-test-automation/actions/workflows/frontend-tests.yml/badge.svg)](https://github.com/brunopsantiago/bookstore-api-test-automation/actions/workflows/frontend-tests.yml)
## 📁 Estrutura do Projeto
```text
bookstore-api-test-automation/
├── 🔧 backend/          # Testes de API
├── 🎨 frontend/         # Testes E2E Cypress  
├── 📖 README.md         # Este arquivo
└── .github/workflows/   # Pipelines CI/CD
```
## 👨‍💻 Desenvolvimento
### Cada módulo tem documentação específica:

```markdown
## 👨‍💻 Desenvolvimento

Cada módulo tem documentação específica:
- 📖 [Backend Documentation](./backend/README.md)
- 📖 [Frontend Documentation](./frontend/README.md)
```


