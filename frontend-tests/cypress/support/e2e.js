// ***********************************************************
// This example support/e2e.js is processed and
// loaded automatically before your test files.
//
// This is a great place to put global configuration and
// behavior that modifies Cypress.
//
// You can change the location of this file or turn off
// automatically serving support files with the
// 'supportFile' configuration option.
//
// You can read more here:
// https://on.cypress.io/configuration
// ***********************************************************

// Import commands.js using ES2015 syntax:
import './commands'

// IMPORTE A BIBLIOTECA DE DRAG AND DROP AQUI ↓
import '@4tw/cypress-drag-drop'

// Handler para ignorar erros de CORS e uncaught exceptions
Cypress.on('uncaught:exception', (err, runnable) => {
  // Ignorar erros de script cross-origin e outros erros comuns
  if (err.message.includes('Script error') ||
      err.message.includes('cross origin') ||
      err.message.includes('ResizeObserver') ||
      err.message.includes('$ is not defined') ||
      err.message.includes('adsbygoogle') ||
      err.message.includes('google') ||
      err.message.includes('advertisement') ||
      err.message.includes('tracking') ||
      err.message.includes('analytics')) {
    console.log('Ignorando erro:', err.message);
    return false; // Retorna false para prevenir que Cypress falhe o teste
  }
  // Para outros erros, permite que Cypress falhe o teste
  return true;
});

// Bloquear propagandas e scripts pesados antes de cada teste
beforeEach(() => {
  console.log('Bloqueando propagandas e scripts pesados...');

  // Bloquear Google Ads
  cy.intercept('**/*.googlesyndication.com/*', { statusCode: 200, body: '' }).as('blockAds');
  cy.intercept('**/*.googleadservices.com/*', { statusCode: 200, body: '' }).as('blockAdServices');
  cy.intercept('**/*.doubleclick.net/*', { statusCode: 200, body: '' }).as('blockDoubleClick');
  cy.intercept('**/pagead/*', { statusCode: 200, body: '' }).as('blockPageAds');

  // Bloquear analytics e trackers
  cy.intercept('**/*.google-analytics.com/*', { statusCode: 200, body: '' }).as('blockAnalytics');
  cy.intercept('**/analytics.js', { statusCode: 200, body: '' }).as('blockAnalyticsJs');
  cy.intercept('**/gtag.js', { statusCode: 200, body: '' }).as('blockGtag');

  // Bloquear scripts de terceiros pesados
  cy.intercept('**/*.facebook.com/*', { statusCode: 200, body: '' }).as('blockFacebook');
  cy.intercept('**/*.twitter.com/*', { statusCode: 200, body: '' }).as('blockTwitter');

  // Bloquear fonts externas que podem demorar
  cy.intercept('**/*.gstatic.com/*', { statusCode: 200, body: '' }).as('blockGstatic');
  cy.intercept('**/*.bootstrapcdn.com/*', { statusCode: 200, body: '' }).as('blockBootstrap');
});

// Comando customizado para visitar páginas lentas
Cypress.Commands.add('visitSlowPage', (url, options = {}) => {
  const defaultOptions = {
    timeout: 60000,
    failOnStatusCode: false
  };

  console.log(`Visitando página lenta: ${url}`);
  return cy.visit(url, { ...defaultOptions, ...options });
});

// Comando customizado para esperar por elemento com retry
Cypress.Commands.add('waitForElement', (selector, options = {}) => {
  const defaultOptions = {
    timeout: 30000
  };

  const config = { ...defaultOptions, ...options };

  return cy.get(selector, { timeout: config.timeout })
    .should('be.visible');
});