# Guia de Operação e Definition of Done (DoD)

## 1. Comandos de Operação
- **Subir Infraestrutura Local**: `docker compose up -d`
- **Derrubar Infraestrutura**: `docker compose down`
- **Rodar a Aplicação (Modo Dev)**: `SPRING_PROFILES_ACTIVE=dev ./mvnw spring-boot:run` (Gera dados de exemplo sem duplicação).
- **Testes Rápidos (Unitários)**: `./mvnw clean test`
- **Testes Completos (Integração + Cobertura)**: `./mvnw clean verify`

## 2. Definition of Done (Critérios de Aceite)
Toda nova tarefa só será considerada completa se atender aos seguintes critérios:
- [ ] O código compila sem erros no Java 21.
- [ ] A cobertura de testes de linha (JaCoCo) alcançou o **mínimo de 70%**.
- [ ] Os testes de integração (classes `*IT`) estão passando isoladamente com o Testcontainers (MongoDB descartável).
- [ ] O Swagger UI (`/docs`) e os contratos OpenAPI estão atualizados e refletem fielmente as operações HTTP.
- [ ] Nenhuma camada está violando suas restrições arquiteturais definidas no arquivo `AGENTS.md`.