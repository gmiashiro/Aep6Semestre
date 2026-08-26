# Contrato Arquitetural - AEP Mongo Java 2026

## 1. Stack Tecnológica
- **Linguagem**: Java 21
- **Framework**: Spring Boot 3.5.16
- **Banco de Dados**: MongoDB 7.0 (via Spring Data MongoDB)
- **Testes**: JUnit 5, Mockito, Testcontainers

## 2. Arquitetura em Camadas
A aplicação segue uma arquitetura em camadas estrita, onde cada uma tem uma responsabilidade única:
- **Controller**: Lida apenas com o protocolo HTTP (status, rotas, validação de entrada `@Valid`). Não deve consultar o banco de dados diretamente.
- **DTO**: Contratos de entrada e saída (implementados utilizando `record`). Nunca expomos o Model diretamente na API. São divididos por intenção (ex: `CreateRequest`, `Response`).
- **Service**: Orquestra os casos de uso.
- **Mapper**: Faz a conversão explícita entre DTOs e Models. Não deve salvar ou responder requisições HTTP.
- **Repository**: Gerencia a persistência herdando de `MongoRepository`.
- **Model**: Representa o documento no MongoDB (anotado com `@Document` e `@Id`).

## 3. Regras de Implementação
- **NÃO** utilize JPA ou Hibernate (`@Entity`, `@Table`). Usamos Spring Data MongoDB.
- A injeção de dependência deve ser feita sempre por **construtor** (evite `@Autowired` direto nas propriedades).
- Atualizações (PUT) devem receber o ID pela URL. O corpo da requisição não escolhe qual registro será atualizado. O Service deve buscar o documento existente pelo ID e atualizar os campos utilizando o Mapper antes de salvar.
- Tratamento de exceções deve ser global e centralizado utilizando `@RestControllerAdvice` (ex: `GlobalExceptionHandler`).