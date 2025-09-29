# language: pt

Funcionalidade: Formulário de Prática - DemoQA

  Cenário: Preencher formulário completo e validar popup de sucesso
    Dado que acesso o site DemoQA
    Quando escolho a opção Forms na página inicial
    E clico no submenu Practice Form
    E preencho todo o formulário com valores aleatórios
    E faço upload de um arquivo .txt
    E submeto o formulário
    Então garanto que um popup foi aberto após o submit
    E fecho o popup