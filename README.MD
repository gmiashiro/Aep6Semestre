# BioMap - AEP6S 1°B

Uma prova de conceito (PoC) de um sistema de mapeamento de espécies de plantas presentes na cidade de Maringá.

## Problema e ligação com a ODS:
É muito difícil se importar e cuidar daquilo que não conhece, e o meio ambiente não é diferente. O BioMap tem como objetivo trazer informações sobre as diferentes espécies de plantas presentes na cidade de Maringá, levando maior conscientização aos moradores sobre a grande biodiversidade que inunda as ruas da cidade, incentivando a sua preservação, estando alinhado com o Objetivo 11, de cidades e comunidades sustentáveis, da lista de Objetivos de Desenvolvimento Sustentável (ODS) da ONU.

## Tecnologias:

As principais tecnologias utilizadas no desenvolvimento dessa PoC são: 
- **Back-end:** Java.
- **Banco de dados:** MongoDb.
- **Testes:** JUnit e Mockito. 

## Como executar:
- **O sistema:**
  - Faça o download desse repositório;
  - Crie uma conexão do MongoDb na porta 27017;
  - Acesse o código-fonte por meio de uma IDE (de preferência, o IntelliJ);
  - Rode o arquivo: AepApplication;
    - Caminho: src/main/java/br/com/sextoSementre/aep/AepApplication.java
  - Em um navegador, acesse o link: http://localhost:8080/swagger-ui/index.html
  - Pronto! Na interface do Swagger, você poderá testar todos os endpoints do sistema!


- **Os testes:**
    - Faça o download desse repositório;
    - Acesse o código-fonte por meio de uma IDE (de preferência, o IntelliJ);
    - Clique com o botão direito na pasta do caminho: src/test/java;
    - Escolha a opção: Run "All Tests" with Coverage;
    - Pronto! O sistema irá rodar os testes e te apresentar a cobertura que eles têm do código-fonte;