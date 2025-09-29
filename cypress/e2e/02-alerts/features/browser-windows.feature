# language: pt

Funcionalidade: Browser Windows - DemoQA
  Como usuário do site DemoQA
  Eu quero testar a abertura de novas janelas
  Para validar a funcionalidade de múltiplas janelas

  Cenário: Abrir nova janela e validar conteúdo
    Dado que acesso o site DemoQA
    Quando escolho a opção "Alerts, Frame & Windows" na página inicial
    E clico no submenu "Browser Windows"
    E clico no botão "New Window"
    Então uma nova janela deve ser aberta
    E valido a mensagem "This is a sample page" na nova janela
    E fecho a nova janela aberta