# Library

Projeto de estudo full‑stack para gerenciamento de uma biblioteca.

O objetivo é aplicar boas práticas em todas as camadas de desenvolvimento desde back‑end com Java e Spring Boot, front‑end com Angular até testes E2E com Cypress.

O ciclo completo inclui modelagem, API REST, validação, documentação, tratamento de erros, integração com front‑end e testes automatizados.

## Como executar o back‑end

### Pré‑requisitos
- Java 21 (JDK 21)

### Passos
1. Clone o repositório e acesse a pasta do back‑end:
   ```bash
   git clone https://github.com/seu-usuario/library-api.git
   cd library-api/backend
   ```

2. Compile e execute a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```

A API estará disponível em `http://localhost:8080`.

A documentação Swagger pode ser acessada em `http://localhost:8080/swagger-ui.html`.
