const { Given, When, Then, And } = require('cypress-cucumber-preprocessor/steps');

Given('que acesso o site DemoQA', () => {
  // Usar visit simples com timeout aumentado
  cy.visit('https://demoqa.com/', {
    timeout: 60000,
    failOnStatusCode: false
  });

  // Aguardar a página carregar
  cy.get('body', { timeout: 30000 }).should('exist');
  cy.get('div.card', { timeout: 30000 }).first().should('be.visible');
});

When('escolho a opção Forms na página inicial', () => {
  // Encontrar e clicar no card Forms
  cy.contains('div.card', 'Forms', { timeout: 15000 })
    .scrollIntoView()
    .should('be.visible')
    .click({ force: true });
});

And('clico no submenu Practice Form', () => {
  // Aguardar menu e clicar no Practice Form
  cy.get('.element-list', { timeout: 15000 }).should('be.visible');
  cy.contains('Practice Form', { timeout: 10000 })
    .should('be.visible')
    .click({ force: true });
});

And('preencho todo o formulário com valores aleatórios', () => {
  const dados = gerarDadosAleatorios();

  // Aguardar formulário carregar
  cy.get('#firstName', { timeout: 20000 }).should('be.visible');

  // Preencher informações básicas
  cy.get('#firstName').type(dados.nome);
  cy.get('#lastName').type(dados.sobrenome);
  cy.get('#userEmail').type(dados.email);

  // Selecionar gênero
  if (dados.genero === 'Male') {
    cy.get('#gender-radio-1').check({ force: true });
  } else if (dados.genero === 'Female') {
    cy.get('#gender-radio-2').check({ force: true });
  } else {
    cy.get('#gender-radio-3').check({ force: true });
  }

  // Celular
  cy.get('#userNumber').type(dados.celular);

  // Data de nascimento
  cy.get('#dateOfBirthInput').click();
  cy.get('.react-datepicker__month-select').select(dados.dataNascimento.mes);
  cy.get('.react-datepicker__year-select').select(dados.dataNascimento.ano.toString());
  cy.get('.react-datepicker__day')
    .not('.react-datepicker__day--outside-month')
    .contains(dados.dataNascimento.dia)
    .click();

  // Assuntos
  dados.assuntos.forEach(assunto => {
    cy.get('#subjectsInput').type(assunto);
    cy.get('.subjects-auto-complete__option').first().click();
  });

  // Hobbies
  if (dados.hobbies.includes('Sports')) {
    cy.get('#hobbies-checkbox-1').check({ force: true });
  }
  if (dados.hobbies.includes('Reading')) {
    cy.get('#hobbies-checkbox-2').check({ force: true });
  }
  if (dados.hobbies.includes('Music')) {
    cy.get('#hobbies-checkbox-3').check({ force: true });
  }

  // Upload
  cy.get('#uploadPicture').selectFile('cypress/fixtures/exemplo.txt');

  // Endereço
  cy.get('#currentAddress').type(dados.endereco);

  // Estado
  cy.get('#state').click();
  cy.contains(dados.estado).click();

  // Cidade
  cy.get('#city').click();
  cy.contains(dados.cidade).click();
});

And('faço upload de um arquivo .txt', () => {
  cy.log('Upload já realizado no passo anterior');
});

And('submeto o formulário', () => {
  cy.get('#submit').click({ force: true });
});

Then('garanto que um popup foi aberto após o submit', () => {
  cy.get('.modal-content', { timeout: 15000 }).should('be.visible');
  cy.get('.modal-title').should('contain', 'Thanks for submitting the form');
});

And('fecho o popup', () => {
  cy.window().then((win) => {
    const closeButton = win.document.querySelector('#closeLargeModal');
    if (closeButton) {
      closeButton.click();
    }
  });

  cy.get('.modal-content').should('not.exist');
});

// Função para gerar dados aleatórios
function gerarDadosAleatorios() {
  const nomes = ['João', 'Maria', 'Pedro', 'Ana', 'Carlos', 'Juliana'];
  const sobrenomes = ['Silva', 'Santos', 'Oliveira', 'Costa', 'Pereira', 'Rodrigues'];
  const dominios = ['gmail.com', 'yahoo.com', 'hotmail.com', 'outlook.com'];

  const nome = nomes[Math.floor(Math.random() * nomes.length)];
  const sobrenome = sobrenomes[Math.floor(Math.random() * sobrenomes.length)];
  const dominio = dominios[Math.floor(Math.random() * dominios.length)];

  const ano = 1990 + Math.floor(Math.random() * 11);
  const mesIndex = Math.floor(Math.random() * 12);
  const dia = 1 + Math.floor(Math.random() * 28);

  const meses = [
    'January', 'February', 'March', 'April', 'May', 'June',
    'July', 'August', 'September', 'October', 'November', 'December'
  ];

  const stateCityOptions = [
    { state: 'NCR', cities: ['Delhi', 'Gurgaon', 'Noida'] },
    { state: 'Uttar Pradesh', cities: ['Agra', 'Lucknow', 'Merrut'] },
    { state: 'Haryana', cities: ['Karnal', 'Panipat'] },
    { state: 'Rajasthan', cities: ['Jaipur', 'Jaiselmer'] }
  ];

  const stateOption = stateCityOptions[Math.floor(Math.random() * stateCityOptions.length)];
  const estado = stateOption.state;
  const cidade = stateOption.cities[Math.floor(Math.random() * stateOption.cities.length)];

  return {
    nome: nome,
    sobrenome: sobrenome,
    email: `${nome.toLowerCase()}.${sobrenome.toLowerCase()}@${dominio}`,
    genero: ['Male', 'Female', 'Other'][Math.floor(Math.random() * 3)],
    celular: `119${Math.floor(10000000 + Math.random() * 90000000)}`,
    dataNascimento: { dia: dia, mes: meses[mesIndex], ano: ano },
    assuntos: ['Maths', 'Physics'],
    hobbies: ['Sports', 'Reading', 'Music'],
    endereco: 'Rua Principal, 123 - Centro, São Paulo - SP',
    estado: estado,
    cidade: cidade
  };
}
