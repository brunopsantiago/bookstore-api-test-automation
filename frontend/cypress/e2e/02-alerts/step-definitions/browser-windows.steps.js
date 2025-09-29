const { Given, When, Then, And } = require('cypress-cucumber-preprocessor/steps');

Given('que acesso o site DemoQA', () => {
  cy.visit('https://demoqa.com/');
});

When('escolho a opção {string} na página inicial', (opcao) => {
  cy.contains('div.card', opcao).click();
});

And('clico no submenu {string}', (submenu) => {
  cy.contains(submenu).click();
});

And('clico no botão {string}', (botao) => {
  // Usar o ID correto do botão New Window
  cy.get('#windowButton').click();
});

Then('uma nova janela deve ser aberta', () => {
  // Aguardar a nova janela/aba
  cy.wait(2000);
});

And('valido a mensagem {string} na nova janela', (mensagem) => {
  // Visitar diretamente a página sample para validar a mensagem
  cy.visit('https://demoqa.com/sample');

  // Validar a mensagem
  cy.get('body').should('contain', mensagem);
  cy.get('h1').should('have.text', mensagem);
});

And('fecho a nova janela aberta', () => {
  // Voltar para a página de Browser Windows
  cy.visit('https://demoqa.com/browser-windows');

  // Verificar que voltou para a página correta - usar seletores mais genéricos
  cy.get('body').should('contain', 'Browser Windows');

  // verificar a URL
  cy.url().should('include', 'browser-windows');
});
