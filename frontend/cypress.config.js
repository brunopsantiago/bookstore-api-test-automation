const { defineConfig } = require('cypress');
const cucumber = require('cypress-cucumber-preprocessor').default;

module.exports = defineConfig({
  e2e: {
    specPattern: 'cypress/e2e/**/*.feature',
    supportFile: 'cypress/support/e2e.js',
    setupNodeEvents(on, config) {
      on('file:preprocessor', cucumber());
      return config;
    },
    // CONFIGURAÇÕES PARA LIDAR COM PÁGINAS LENTAS
    pageLoadTimeout: 60000, // Aumentar para 60 segundos
    defaultCommandTimeout: 10000,
    requestTimeout: 10000,
    responseTimeout: 10000,
    execTimeout: 60000,
    taskTimeout: 60000,
    // Ignorar exceções não capturadas de scripts externos
    chromeWebSecurity: false,
    // Configurações específicas para Electron
    experimentalSourceRewriting: false,
    modifyObstructiveCode: false
  },
  viewportWidth: 1280,
  viewportHeight: 720,
  // Configurações globais de timeout
  retries: {
    runMode: 2,
    openMode: 0
  }
});