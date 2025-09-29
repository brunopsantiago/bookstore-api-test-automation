import { Given, When, Then } from "cypress-cucumber-preprocessor/steps";

// Step: que acesso a pagina do Progress Bar
Given('que acesso a pagina do Progress Bar', () => {
  cy.visit('https://demoqa.com/progress-bar');
  cy.get('h1.text-center').should('contain', 'Progress Bar');
  cy.log('✅ Página carregada corretamente');
});

// Step: clico no botao Start
When('clico no botao Start', () => {
  cy.get('#startStopButton')
    .should('be.visible')
    .should('contain', 'Start')
    .click();

  cy.get('#startStopButton', { timeout: 5000 })
    .should('contain', 'Stop');
  cy.log('✅ Botão mudou para Stop - progresso iniciado');
});

// Step: paro a barra antes de 25%
When('paro a barra antes de 25%', () => {
  cy.log('Aguardando barra chegar perto de 25%...');

  const checkProgress = () => {
    cy.get('#progressBar .progress-bar').invoke('attr', 'aria-valuenow').then((value) => {
      const progress = parseInt(value);
      cy.log(`Progresso atual: ${progress}%`);

      if (progress >= 15 && progress < 25) {
        cy.get('#startStopButton').click();
        cy.log(`✅ Barra parada em: ${progress}%`);
      } else if (progress < 15) {
        cy.wait(200).then(checkProgress);
      } else {
        cy.get('#startStopButton').click();
        cy.log(`⚠️ Barra parada em: ${progress}% (passou de 25%)`);
      }
    });
  };

  checkProgress();
});

// Step: verifico que esta abaixo de 25%
Then('verifico que esta abaixo de 25%', () => {
  cy.get('#progressBar .progress-bar').invoke('attr', 'aria-valuenow').then((value) => {
    const progress = parseInt(value);
    cy.log(`Progresso final verificado: ${progress}%`);
    expect(progress).to.be.lessThan(25);
  });

  cy.get('#startStopButton').should('contain', 'Start');
});

// Step: clico em Start novamente
When('clico em Start novamente', () => {
  cy.get('#startStopButton')
    .should('contain', 'Start')
    .click();

  cy.get('#startStopButton').should('contain', 'Stop');
});

// Step: espero ate 100%
When('espero ate 100%', () => {
  cy.log('Aguardando barra chegar a 100%...');
  cy.get('#progressBar .progress-bar', { timeout: 30000 })
    .should('have.attr', 'aria-valuenow', '100');
  cy.log('✅ Barra chegou a 100%');
});

// Step: clico em Reset - CORRIGIDO
Then('clico em Reset', () => {
  // CORREÇÃO: Usando seletor mais específico e garantindo o clique
  cy.get('button#resetButton.mt-3.btn.btn-primary')
    .should('be.visible')
    .should('contain', 'Reset')
    .click({ force: true });

  // CORREÇÃO: Aguarda a transição e verifica de forma mais flexível
  cy.get('#startStopButton', { timeout: 5000 })
    .should('be.visible')
    .should('contain', 'Start');

  // CORREÇÃO: Verificação mais tolerante para o reset
  cy.get('#progressBar .progress-bar', { timeout: 5000 })
    .invoke('attr', 'aria-valuenow')
    .then((value) => {
      const progress = parseInt(value);
      cy.log(`Progresso após reset: ${progress}%`);
      expect(progress).to.equal(0);
    });

  cy.log('✅ Barra resetada para 0%');
});