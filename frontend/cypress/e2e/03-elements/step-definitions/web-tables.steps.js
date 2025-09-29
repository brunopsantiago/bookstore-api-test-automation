const { Given, When, Then, And } = require('cypress-cucumber-preprocessor/steps');

let novoRegistro = {};

Given('que acesso o site DemoQA', () => {
  cy.visit('https://demoqa.com/');
});

Given('que acesso a página Web Tables', () => {
  cy.visit('https://demoqa.com/webtables');
});

When('escolho a opção {string} na página inicial', (opcao) => {
  cy.contains('div.card', opcao).click();
});

And('clico no submenu {string}', (submenu) => {
  cy.contains(submenu).click();
});

And('crio um novo registro na tabela', () => {
  // Gerar dados para o novo registro
  novoRegistro = gerarDadosRegistro();

  // Clicar no botão Add
  cy.get('#addNewRecordButton').click();

  // Preencher o formulário de registro
  cy.get('#firstName').type(novoRegistro.firstName);
  cy.get('#lastName').type(novoRegistro.lastName);
  cy.get('#userEmail').type(novoRegistro.email);
  cy.get('#age').type(novoRegistro.age);
  cy.get('#salary').type(novoRegistro.salary);
  cy.get('#department').type(novoRegistro.department);

  // Submeter o formulário
  cy.get('#submit').click();

  // Verificar que o registro foi adicionado
  cy.get('.rt-tbody')
    .should('contain', novoRegistro.firstName)
    .and('contain', novoRegistro.lastName);
});

And('edito o novo registro criado', () => {
  // Encontrar e clicar no ícone de edição do registro criado
  cy.contains('.rt-td', novoRegistro.firstName)
    .parent('.rt-tr')
    .within(() => {
      cy.get('[title="Edit"]').click();
    });

  // Editar alguns campos
  const novoSalario = (parseInt(novoRegistro.salary) + 1000).toString();
  const novoDepartamento = 'Engineering';

  cy.get('#salary').clear().type(novoSalario);
  cy.get('#department').clear().type(novoDepartamento);

  // Salvar as alterações
  cy.get('#submit').click();

  // Verificar que as alterações foram salvas
  cy.contains('.rt-td', novoRegistro.firstName)
    .parent('.rt-tr')
    .should('contain', novoSalario)
    .and('contain', novoDepartamento);
});

And('deleto o novo registro criado', () => {
  // Salvar o número de registros antes da deleção
  let registrosAntes;
  cy.get('.rt-tr-group').then(($rows) => {
    registrosAntes = $rows.filter((index, row) =>
      Cypress.$(row).find('.rt-td').text().trim() !== ''
    ).length;
  });

  // Encontrar e clicar no ícone de deletar do registro
  cy.contains('.rt-td', novoRegistro.firstName)
    .parent('.rt-tr')
    .within(() => {
      cy.get('[title="Delete"]').click();
    });

  // Verificar que o registro foi removido
  cy.get('.rt-tbody').should('not.contain', novoRegistro.firstName);

  // Verificar que o número de registros diminuiu
  cy.get('.rt-tr-group').then(($rows) => {
    const registrosDepois = $rows.filter((index, row) =>
      Cypress.$(row).find('.rt-td').text().trim() !== ''
    ).length;

    expect(registrosDepois).to.be.lessThan(registrosAntes);
  });
});

// BÔNUS - STEPS PARA SCENARIO OUTLINE
And('limpo a tabela se tiver mais de {int} registros', (limite) => {
  cy.get('.rt-tr-group').then(($rows) => {
    const registrosExistentes = $rows.filter((index, row) =>
      Cypress.$(row).find('.rt-td').text().trim() !== ''
    ).length;

    cy.log(`Registros existentes: ${registrosExistentes}, Limite: ${limite}`);

    // Se tiver mais registros que o limite, limpar alguns
    if (registrosExistentes > limite) {
      const quantidadeDeletar = registrosExistentes - limite;
      cy.log(`Deletando ${quantidadeDeletar} registros para manter o limite...`);

      for (let i = 0; i < quantidadeDeletar; i++) {
        cy.get('.rt-tr-group').first().within(() => {
          cy.get('[title="Delete"]').click();
        });
        cy.wait(200);
      }
    }
  });
});

When('crio um registro com:', (dataTable) => {
  const registro = dataTable.rowsHash();

  cy.log(`Criando registro: ${registro['First Name']} ${registro['Last Name']}`);

  // Clicar no botão Add
  cy.get('#addNewRecordButton').click();

  // Preencher o formulário
  cy.get('#firstName').type(registro['First Name']);
  cy.get('#lastName').type(registro['Last Name']);
  cy.get('#userEmail').type(registro['Email']);
  cy.get('#age').type(registro['Age']);
  cy.get('#salary').type(registro['Salary']);
  cy.get('#department').type(registro['Department']);

  // Submeter
  cy.get('#submit').click();
});

Then('o registro {string} {string} deve ser criado com sucesso', (firstName, lastName) => {
  // Verificar que o registro foi criado
  cy.get('.rt-tbody')
    .should('contain', firstName)
    .and('contain', lastName);

  cy.log(`✅ Registro ${firstName} ${lastName} criado com sucesso!`);
});

And('deleto o registro {string} {string}', (firstName, lastName) => {
  // Encontrar e deletar o registro específico
  cy.contains('.rt-td', firstName)
    .parent('.rt-tr')
    .within(() => {
      cy.get('[title="Delete"]').click();
    });

  // Verificar que foi deletado
  cy.get('.rt-tbody').should('not.contain', firstName);
  cy.log(`✅ Registro ${firstName} ${lastName} deletado!`);
});

// Funções auxiliares
function gerarDadosRegistro() {
  const nomes = ['João', 'Maria', 'Pedro', 'Ana', 'Carlos', 'Juliana'];
  const sobrenomes = ['Silva', 'Santos', 'Oliveira', 'Costa', 'Pereira'];
  const departamentos = ['IT', 'Finance', 'HR', 'Marketing', 'Sales'];

  const nome = nomes[Math.floor(Math.random() * nomes.length)];
  const sobrenome = sobrenomes[Math.floor(Math.random() * sobrenomes.length)];

  return {
    firstName: nome,
    lastName: sobrenome,
    email: `${nome.toLowerCase()}.${sobrenome.toLowerCase()}@empresa.com`,
    age: (25 + Math.floor(Math.random() * 20)).toString(),
    salary: (3000 + Math.floor(Math.random() * 5000)).toString(),
    department: departamentos[Math.floor(Math.random() * departamentos.length)]
  };
}

function gerarDadosRegistroComIndice(indice) {
  const departamentos = ['IT', 'Finance', 'HR', 'Marketing', 'Sales'];

  return {
    firstName: `TestUser${indice}`,
    lastName: `LastName${indice}`,
    email: `testuser${indice}@empresa.com`,
    age: (25 + indice).toString(),
    salary: (3000 + (indice * 100)).toString(),
    department: departamentos[indice % departamentos.length]
  };
}