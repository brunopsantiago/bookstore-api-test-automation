class FormularioPraticaPage {

  elementos = {
    // Navegação
    cardForms: ':nth-child(2) > :nth-child(1) > .card-up',
    menuPracticeForm: ':nth-child(2) > .element-list > .menu-list > #item-0',

    // Campos do formulário
    campoNome: '#firstName',
    campoSobrenome: '#lastName',
    campoEmail: '#userEmail',
    radioGenero: (genero) => `//label[contains(.,"${genero}")]`,
    campoCelular: '#userNumber',
    campoDataNascimento: '#dateOfBirthInput',
    seletorMes: '.react-datepicker__month-select',
    seletorAno: '.react-datepicker__year-select',
    diaCalendario: (dia) => `.react-datepicker__day:not(.react-datepicker__day--outside-month):contains("${dia}")`,
    campoAssuntos: '#subjectsInput',
    checkboxHobbies: (hobby) => `//label[contains(.,"${hobby}")]`,
    uploadArquivo: '#uploadPicture',
    campoEndereco: '#currentAddress',
    dropdownEstado: '#state',
    opcaoEstado: (estado) => `//div[contains(text(),"${estado}")]`,
    dropdownCidade: '#city',
    opcaoCidade: (cidade) => `//div[contains(text(),"${cidade}")]`,
    botaoEnviar: '#submit',

    // Modal/Popup de sucesso
    modalSucesso: '.modal-content',
    tituloModal: '#example-modal-sizes-title-lg',
    botaoFecharModal: '#closeLargeModal'
  };

  acessarSite() {
    cy.visit('https://demoqa.com/');
  }

  clicarOpcaoForms() {
    cy.get(this.elementos.cardForms).click();
  }

  clicarSubmenuPracticeForm() {
    cy.get(this.elementos.menuPracticeForm).click();
  }

  preencherFormularioCompleto(dados) {
    // Informações pessoais
    cy.get(this.elementos.campoNome).type(dados.nome);
    cy.get(this.elementos.campoSobrenome).type(dados.sobrenome);
    cy.get(this.elementos.campoEmail).type(dados.email);

    // Gênero
    cy.xpath(this.elementos.radioGenero(dados.genero)).click();

    // Celular
    cy.get(this.elementos.campoCelular).type(dados.celular);

    // Data de nascimento
    cy.get(this.elementos.campoDataNascimento).click();
    cy.get(this.elementos.seletorMes).select(dados.dataNascimento.mes);
    cy.get(this.elementos.seletorAno).select(dados.dataNascimento.ano.toString());
    cy.get(this.elementos.diaCalendario(dados.dataNascimento.dia)).first().click();

    // Assuntos
    dados.assuntos.forEach(assunto => {
      cy.get(this.elementos.campoAssuntos).type(assunto);
      cy.get('.subjects-auto-complete__menu-list').contains(assunto).click();
    });

    // Hobbies
    dados.hobbies.forEach(hobby => {
      cy.xpath(this.elementos.checkboxHobbies(hobby)).click();
    });

    // Endereço
    cy.get(this.elementos.campoEndereco).type(dados.endereco);

    // Estado e Cidade
    cy.get(this.elementos.dropdownEstado).click();
    cy.xpath(this.elementos.opcaoEstado(dados.estado)).click();

    cy.get(this.elementos.dropdownCidade).click();
    cy.xpath(this.elementos.opcaoCidade(dados.cidade)).click();
  }

  fazerUploadArquivo(nomeArquivo) {
    cy.get(this.elementos.uploadArquivo).selectFile(`cypress/fixtures/${nomeArquivo}`);
  }

  submeterFormulario() {
    cy.get(this.elementos.botaoEnviar).click();
  }

  validarPopupAberto() {
    cy.get(this.elementos.modalSucesso).should('be.visible');
    cy.get(this.elementos.tituloModal).should('contain', 'Thanks for submitting the form');
  }

  fecharPopup() {
    cy.get(this.elementos.botaoFecharModal).click();
    cy.get(this.elementos.modalSucesso).should('not.exist');
  }
}

export default FormularioPraticaPage;