# 🚀 BeanCounter API

![Java](https://img.shields.io/badge/Java-17-blue) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-%234169E1) ![Maven](https://img.shields.io/badge/Maven-Build%20Tool-yellow) ![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D)

API REST para gerenciamento de finanças pessoais, focada em segurança e isolamento de dados (Tenant Isolation). O projeto permite que usuários registrem receitas e despesas, e acompanhem seus saldos através de um dashboard inteligente. Estruturado com boas práticas de mercado, o sistema conta com autenticação JWT, tratamento global de erros e documentação interativa.

## 📐 Tecnologias Utilizadas

- **Spring Boot** (Web, Security, Data JPA, Validation)
- **Spring Security + JWT** (Autenticação Stateless e Autorização)
- **Springdoc OpenAPI (Swagger)** (Documentação interativa e testes de API)
- **Banco de Dados:** PostgreSQL (Ambiente Docker e Produção)
- **Docker & Docker Compose** (Infraestrutura local com banco e pgAdmin)
- **Railway** (Deploy em produção com CI/CD)

## 🚀 Deploy em Produção

A aplicação está hospedada na nuvem utilizando o Railway, permitindo acesso rápido e fácil para testes do fluxo completo. A raiz da aplicação redireciona automaticamente para a interface do Swagger, facilitando a exploração dos endpoints.

> **URL da API (Swagger):** [https://beancounter-production.up.railway.app/](https://beancounter-production.up.railway.app/)

## ⚙️ Arquitetura e Organização do Projeto

A aplicação segue uma estrutura modular, separando responsabilidades de forma clara e escalável:

```text
📦 beancounter
 ┣ 📂 config        # Configurações do Spring Security, JWT e Swagger
 ┣ 📂 controller    # Endpoints da API e redirecionamento raiz
 ┣ 📂 dto           # Data Transfer Objects (Request e Response)
 ┣ 📂 exception     # Exceções personalizadas (GlobalExceptionHandler)
 ┣ 📂 model         # Entidades do banco de dados (User, Transaction)
 ┣ 📂 repository    # Interfaces do Spring Data JPA
 ┣ 📂 security      # Filtros JWT e UserDetails
 ┣ 📂 services      # Regras de negócio e validações
 ┣ 📜 application.properties  # Configurações do ambiente
```

## 🛠 Funcionalidades Implementadas

### 🔐 Autenticação e Segurança

- Login e registro de usuários.
- Geração e validação de Tokens JWT com expiração temporal.
- Isolamento total de dados: um usuário não consegue acessar, editar ou deletar dados de outro.

### 💰 Gestão de Transações

- Cadastro de transações (Receitas e Despesas).
- Listagem dinâmica baseada em `startDate` e `endDate`.
- Fallback inteligente: ausência de parâmetros de data retorna automaticamente o balanço do mês atual.
- Edição e exclusão seguras via validação de propriedade do token.

### 📊 Dashboard Financeiro

- Resumo consolidado do período (Total de Entradas, Total de Saídas e Saldo Atual).
- Cálculos diretos no banco de dados (agilidade e economia de processamento, prevenção contra valores nulos).

## 🔗 Endpoints Principais

### 🔐 Autenticação

- `POST /auth/register` → Criação de nova conta
- `POST /auth/login` → Autenticação e retorno do Bearer Token JWT

### 📦 Transações

- `POST /transactions` → Criar uma nova transação
- `GET /transactions?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD` → Listar transações no período (padrão: mês atual)
- `PUT /transactions/{id}` → Atualizar dados de uma transação
- `DELETE /transactions/{id}` → Deletar uma transação

### 📊 Dashboard

- `GET /dashboard?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD` → Retorna o balanço financeiro do período selecionado

---

# 📘 Documentação Técnica

## 🛠 Tratamento de Exceções Global

Para evitar o vazamento de stack traces e padronizar o consumo por aplicações Front-end, o sistema utiliza um `@RestControllerAdvice`. Qualquer erro na API é capturado e formatado em um JSON limpo, com códigos internos de erro.

### 📌 Principais Exceções Tratadas:

| Exceção                   | Status HTTP | Código Interno             | Descrição                                                           |
| ------------------------- | ----------- | -------------------------- | ------------------------------------------------------------------- |
| `BusinessException`       | 400         | `AUTH_001`, `TRANS_001`    | Regras de negócio (ex: E-mail já em uso, Transação não encontrada). |
| `BadCredentialsException` | 401         | `AUTH_INVALID_CREDENTIALS` | Senha ou e-mail incorretos no login.                                |
| `SecurityException`       | 403         | `SECURITY_ERR`             | Tentativa de manipular dados de outro usuário (Tenant Isolation).   |
| `MethodArgumentNotValid`  | 422         | `VALIDATION_ERR`           | Falha nas anotações `@Valid` (ex: senha muito curta).               |
| `HttpMessageNotReadable`  | 400         | `BAD_REQUEST`              | Corpo do JSON mal formatado na requisição.                          |
| `Exception` (Cata-tudo)   | 500         | `INTERNAL_SERVER_ERROR`    | Erros inesperados de servidor ou banco de dados.                    |

### 🔧 Exemplo de Resposta Padrão de Erro:

```json
{
  "timestamp": "2026-03-18T14:35:00.123",
  "status": 401,
  "errorCode": "AUTH_INVALID_CREDENTIALS",
  "message": "Invalid email or password",
  "path": "/auth/login"
}
```

## 🚀 Como Rodar o Projeto Localmente

### Pré-requisitos

- Docker e Docker Compose
- Java 17+
- Maven

### Passos

1. Clone o repositório:

   ```bash
   git clone [https://github.com/RuanPablo2/BeanCounter.git](https://github.com/RuanPablo2/BeanCounter.git)
   cd BeanCounter
   ```

2. Suba a infraestrutura do Banco de Dados (PostgreSQL + pgAdmin):

   ```bash
   docker-compose up -d
   ```

   ou utilize o próprio H2 no perfil "test".

3. Compile e rode a API localmente:

   ```bash
   mvn spring-boot:run
   ```

4. Acesse a documentação interativa:
   Abra o navegador em `http://localhost:8080/` para ser redirecionado automaticamente para o **Swagger UI**.

## 👨‍💻 Autor

Desenvolvido por Ruan Pablo (https://github.com/RuanPablo2). Feedbacks e contribuições são bem-vindos!
