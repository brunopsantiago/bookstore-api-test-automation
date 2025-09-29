# language: pt
Funcionalidade: Progress Bar - DemoQA

  Cenario: Controlar barra de progresso
    Dado que acesso a pagina do Progress Bar
    Quando clico no botao Start
    E paro a barra antes de 25%
    Entao verifico que esta abaixo de 25%
    Quando clico em Start novamente
    E espero ate 100%
    Entao clico em Reset