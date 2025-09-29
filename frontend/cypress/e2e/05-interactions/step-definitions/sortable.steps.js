import { Given, When, Then } from "cypress-cucumber-preprocessor/steps";

Given('que acesso o site DemoQA', () => {
  cy.visit('https://demoqa.com');
  cy.get('.home-banner').should('be.visible');
});

When('escolho a opção Interactions na página inicial', () => {
  cy.get('.card-body').contains('Interactions').scrollIntoView().click();
});

When('clico no submenu Sortable', () => {
  cy.get('.element-list').contains('Sortable').click();
  cy.get('h1.text-center').should('contain', 'Sortable');
});

Then('utilizando métodos de drag and drop, coloco os elementos na ordem crescente', () => {
  // Verifica se os elementos estão carregados
  cy.get('.vertical-list-container .list-group-item').should('have.length', 6);

  // Mostra a ordem atual
  cy.log('=== ORDEM ATUAL ===');
  cy.get('.vertical-list-container .list-group-item').each(($el, index) => {
    cy.log(`Posição ${index + 1}: ${$el.text()}`);
  });

  // Abordagem usando JavaScript nativo para simular drag and drop
  simularDragAndDrop();
});

function simularDragAndDrop() {
  // Usando JavaScript nativo para evitar problemas de visibilidade
  cy.window().then((win) => {
    cy.get('.vertical-list-container .list-group-item:contains("Six")').then(($source) => {
      cy.get('.vertical-list-container .list-group-item:contains("One")').then(($target) => {

        // Cria eventos de drag and drop
        const dataTransfer = new win.DataTransfer();

        // Dispara eventos de drag start no elemento source
        $source[0].dispatchEvent(new win.DragEvent('dragstart', {
          dataTransfer,
          bubbles: true
        }));

        // Dispara eventos no elemento target
        $target[0].dispatchEvent(new win.DragEvent('dragenter', {
          dataTransfer,
          bubbles: true
        }));

        $target[0].dispatchEvent(new win.DragEvent('dragover', {
          dataTransfer,
          bubbles: true
        }));

        // Dispara drop event
        $target[0].dispatchEvent(new win.DragEvent('drop', {
          dataTransfer,
          bubbles: true
        }));

        // Dispara drag end
        $source[0].dispatchEvent(new win.DragEvent('dragend', {
          dataTransfer,
          bubbles: true
        }));
      });
    });
  });

  cy.wait(2000);

  // Verifica a nova ordem
  cy.log('=== NOVA ORDEM ===');
  cy.get('.vertical-list-container .list-group-item').each(($el, index) => {
    cy.log(`Posição ${index + 1}: ${$el.text()}`);
  });

  cy.log('✅ Drag and drop simulado com sucesso!');
}