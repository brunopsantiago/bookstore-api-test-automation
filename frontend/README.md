# 🎨 BookStore Frontend Test Automation

<div align="center">

![Cypress](https://img.shields.io/badge/Cypress-15.3.0-green?style=for-the-badge&logo=cypress)
![Cucumber](https://img.shields.io/badge/Cucumber-4.3.1-brightgreen?style=for-the-badge&logo=cucumber)
![JavaScript](https://img.shields.io/badge/JavaScript-ES6+-yellow?style=for-the-badge&logo=javascript)
![Node.js](https://img.shields.io/badge/Node.js-22.20.0-green?style=for-the-badge&logo=nodedotjs)
![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-✔-black?style=for-the-badge&logo=githubactions)

**Framework de Automação Frontend para o DemoQA**  
*Complemento Frontend para o Desafio de Automação Accenture*

</div>

## 🚀 Status do Projeto

### ✅ **IMPLEMENTADO**
- ✅ Testes E2E para todas as seções do DemoQA
- ✅ Framework com Cypress + Cucumber (BDD)
- ✅ CI/CD com GitHub Actions
- ✅ Testes de formulários, alertas, widgets e interações
- ✅ Validações de UI/UX e funcionalidades

### 📋 **CENÁRIOS COBERTOS**

#### 📝 **01 - Forms**
- ✅ **Practice Form**: Preenchimento completo e validação de popup de sucesso
- ✅ Upload de arquivos .txt e seleções múltiplas

#### ⚡ **02 - Alerts, Frame & Windows**
- ✅ **Browser Windows**: Abertura e validação de nova janela com mensagem
- ✅ Manipulação de múltiplas janelas do browser

#### 🏷️ **03 - Elements**
- ✅ **Web Tables**: Operações CRUD completas (Criar, Editar, Deletar)
- 🎯 **BÔNUS**: Cenário com dados dinâmicos via Cucumber Scenario Outline

#### 🎛️ **04 - Widgets**
- ✅ **Progress Bar**: Controle dinâmico (start, stop, reset) e validação de progresso

#### 🔄 **05 - Interactions**
- ✅ **Sortable**: Drag and drop para ordenação crescente de elementos

## 🏃‍♂️ Execução Rápida

```bash
# Navegar para o projeto frontend
cd frontend-tests

# Instalar dependências
npm install

# Executar todos os testes
npm run test:all

# Executar testes sequencialmente (recomendado)
npm run test:sequential

# Executar por categoria
npm run test:forms
npm run test:alerts  
npm run test:tables
npm run test:widgets
npm run test:interactions

# Interface gráfica do Cypress
npm run cy:open
```

## 🛠️ Tecnologias
| Tecnologia | Versão  | Propósito |
|------------|---------|-----------|
| Cypress | 15.3.0  | Framework de teste E2E |
| Cucumber | 4.3.1   | BDD e Gherkin |
| JavaScript | ES6+    | Linguagem principal |
| Node.js | 22.20.0 | Runtime environment |
| @4tw/cypress-drag-drop | -       | Biblioteca para drag & drop |


## 🏗️ Estrutura do Projeto
```text
frontend-tests/
├── cypress/
│   ├── e2e/
│   │   ├── 01-forms/              # ✅ Practice Forms
│   │   │   ├── features/
│   │   │   └── step-definitions/
│   │   ├── 02-alerts/             # ✅ Browser Windows
│   │   ├── 03-elements/           # ✅ Web Tables + BÔNUS
│   │   ├── 04-widgets/            # ✅ Progress Bar
│   │   └── 05-interactions/       # ✅ Sortable (Drag & Drop)
│   ├── fixtures/
│   ├── screenshots/
│   └── support/
│       └── e2e.js                 # Configurações globais
├── cypress.config.js
└── package.json
```

## 📊 Comandos Disponíveis
```bash
# Desenvolvimento
npm run cy:open                    # Abrir interface Cypress
npm run cy:run                     # Executar em modo headless

# Testes Específicos
npm run test:forms                 # Apenas formulários
npm run test:alerts                # Apenas alertas e janelas
npm run test:tables                # Apenas tabelas (incluindo bônus)
npm run test:widgets               # Apenas widgets
npm run test:interactions          # Apenas interações

# Execuções Especiais  
npm run test:sequential            # Sequencial com delays
npm run test:all-headed            # Todos com interface
```
## 🎯 Cenários de Destaque
### 📝 Practice Form - Preenchimento Completo

```gherkin
Cenário: Preencher formulário completo e validar popup de sucesso
  Dado que acesso o site DemoQA
  Quando escolho a opção Forms na página inicial
  E clico no submenu Practice Form
  E preencho todo o formulário com valores aleatórios
  E faço upload de um arquivo .txt
  E submeto o formulário
  Então garanto que um popup foi aberto após o submit
  E fecho o popup
```

### ⚡ Browser Windows - Nova Janela
```gherkin
Cenário: Abrir nova janela e validar conteúdo
  Dado que acesso o site DemoQA
  Quando escolho a opção "Alerts, Frame & Windows" na página inicial
  E clico no submenu "Browser Windows"
  E clico no botão "New Window"
  Então uma nova janela deve ser aberta
  E valido a mensagem "This is a sample page" na nova janela
  E fecho a nova janela aberta
```

### 🏷️ Web Tables - CRUD Completo + BÔNUS
```gherkin
Cenário: Criar, editar e deletar registro na tabela
  Dado que acesso o site DemoQA
  Quando escolho a opção "Elements" na página inicial
  E clico no submenu "Web Tables"
  E crio um novo registro na tabela
  E edito o novo registro criado
  E deleto o novo registro criado

# 🎯 BÔNUS - Cenário com dados dinâmicos via Cucumber
Esquema do Cenário: Criar múltiplos registros dinamicamente
  Dado que acesso a página Web Tables
  E limpo a tabela se tiver mais de <limite> registros
  Quando crio um registro com dados dinâmicos
  Então o registro <firstName> <lastName> deve ser criado com sucesso
  E deleto o registro <firstName> <lastName>
```

### 🎛️ Progress Bar - Controle Dinâmico
```gherkin
Cenário: Controlar barra de progresso
  Dado que acesso a pagina do Progress Bar
  Quando clico no botao Start
  E paro a barra antes de 25%
  Entao verifico que esta abaixo de 25%
  Quando clico em Start novamente
  E espero ate 100%
  Entao clico em Reset
```

### 🔄 Sortable - Drag and Drop
```gherkin
Cenário: Ordenar elementos em ordem crescente usando drag and drop
  Dado que acesso o site DemoQA
  Quando escolho a opção Interactions na página inicial
  E clico no submenu Sortable
  Então utilizando métodos de drag and drop, coloco os elementos na ordem crescente
```

## 🎯 Funcionalidades Avançadas
### Scenario Outline - Dados Dinâmicos
Implementação de 12 registros dinâmicos via Cucumber Scenario Outline:

- ✅ Dados parametrizados (nome, email, idade, salário, departamento)
- ✅ Limpeza automática da tabela
- ✅ Criação e deleção em massa
- ✅ Validação de múltiplos cenários

### Drag & Drop Nativo
- Solução robusta usando JavaScript nativo para contornar limitações do Cypress com elementos visibility: hidden

### Gestão de Múltiplas Janelas
- Controle completo de abas e janelas do browser durante os testes

## 🐛 Troubleshooting
### Erro de Conexão
👉 Verifique se https://demoqa.com está online

### Erro de Memória (Electron Crash)
```bash
# Usar Chrome em vez do Electron
npx cypress run --browser chrome

# Ou executar testes sequencialmente
npm run test:sequential
```

### Teste Bônus Web Tables
- ⚠️ O cenário bônus com Scenario Outline pode apresentar instabilidades devido à complexidade das operações em massa

### Elemento Não Encontrado
- 👉 Verificar seletores atualizados no site DemoQA
- 💡 Dica: Execute npm run test:sequential para evitar problemas de memória durante a execução completa dos testes!
- 🎯 Destaque: Implementação completa de 5 funcionalidades principais + cenário bônus avançado com dados dinâmicos!

### 📞 Contato
#### **Bruno Santiago** - brunopsantiago - bpsantiagu@gmail.com
<div align="center">
Desenvolvido para o Desafio de Automação Accenture

⬆ Voltar ao topo

</div> 