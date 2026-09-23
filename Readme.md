# 🚀 BeanCounter API

![Java](https://img.shields.io/badge/Java-17-blue) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-%234169E1) ![Docker](https://img.shields.io/badge/Docker-Oci-2496ED) ![Google Gemini](https://img.shields.io/badge/AI_Powered-Google_Gemini-8E75B2)

API REST para gerenciamento de finanças pessoais, focada em segurança, isolamento de dados (Tenant Isolation) e processamento de linguagem natural (NLP). O projeto permite que usuários registrem receitas e despesas manualmente ou através de uma Inteligência Artificial, e acompanhem seus saldos através de um dashboard dinâmico. 

## 📐 Tecnologias Utilizadas

- **Spring Boot 3** (Web, Security, Data JPA, Validation)
- **Spring RestClient** (Integração assíncrona HTTP/JSON)
- **Google Gemini API** (Inteligência Artificial generativa via `gemini-3-flash-preview`)
- **Spring Security + JWT** (Autenticação Stateless e Autorização)
- **Springdoc OpenAPI (Swagger)** (Documentação interativa e testes de API)
- **Banco de Dados:** PostgreSQL 15
- **Infraestrutura:** Oracle Cloud Infrastructure (OCI) com Docker & Docker Compose

## 🚀 Deploy em Produção

A aplicação está hospedada na Oracle Cloud, rodando em contêineres Docker. A raiz da aplicação redireciona automaticamente para a interface do Swagger, facilitando a exploração e o teste dos endpoints.

> **URL da API (Swagger):** [https://beancounter-ruanpablo2.duckdns.org/](https://beancounter-ruanpablo2.duckdns.org/)

## ⚙️ Arquitetura e Organização do Projeto

A aplicação segue uma estrutura modular e limpa:

```text
📦 beancounter
 ┣ 📂 config        # Configurações do Spring Security, JWT, CORS e Swagger
 ┣ 📂 controller    # Endpoints REST da API e redirecionamento raiz
 ┣ 📂 dto           # Data Transfer Objects (Request e Response)
 ┣ 📂 exception     # Exceções personalizadas (GlobalExceptionHandler)
 ┣ 📂 model         # Entidades do banco de dados (User, Transaction)
 ┣ 📂 repository    # Interfaces do Spring Data JPA
 ┣ 📂 security      # Filtros JWT e UserDetails
 ┣ 📂 services      # Regras de negócio, NLP via Gemini e validações
 ┣ 📜 application-prod.properties  # Configurações de produção
```

## 🛠 Funcionalidades Implementadas

### 🤖 Smart Transactions (Integração IA)
- Inserção de gastos por linguagem natural (Ex: *"Gastei 50 reais no Uber hoje"*).
- Extração automatizada de `description`, `amount`, `type` e `category`.
- Cálculo dinâmico de datas relativas com base no timezone (`America/Sao_Paulo`).

### 🔐 Autenticação e Segurança
- Login e registro de usuários.
- Geração e validação de Tokens JWT com expiração temporal.
- Isolamento total de dados: um usuário não consegue acessar ou manipular dados de outro (Tenant Isolation).

### 💰 Gestão de Transações
- Cadastro manual de transações (Receitas e Despesas).
- Listagem dinâmica baseada em `startDate` e `endDate`.
- Edição e exclusão seguras com validação rigorosa de propriedade da entidade no banco.

### 📊 Dashboard Financeiro
- Resumo consolidado do período (Total de Entradas, Total de Saídas e Saldo Atual).
- Agregação e cálculos de fallback executados nativamente.

## 🔗 Endpoints Principais

### 🔐 Autenticação
- `POST /auth/register` → Criação de nova conta
- `POST /auth/login` → Autenticação e retorno do Bearer Token JWT

### 📦 Transações
- `POST /transactions/smart` → **[NOVO]** Processa texto em linguagem natural via IA e cria a transação estruturada
- `POST /transactions` → Criar uma nova transação manual
- `GET /transactions?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD` → Listar transações no período
- `PUT /transactions/{id}` → Atualizar dados de uma transação
- `DELETE /transactions/{id}` → Deletar uma transação

### 📊 Dashboard
- `GET /dashboard?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD` → Retorna o balanço financeiro do período selecionado

---

# 📘 Documentação Técnica

## 🛠 Tratamento de Exceções Global

Para evitar o vazamento de stack traces e padronizar o consumo pelo Front-end, o sistema utiliza um `@RestControllerAdvice`. Qualquer erro na API é formatado em um JSON limpo, com códigos internos de erro.

### 📌 Principais Exceções Tratadas:

| Exceção                   | Status HTTP | Código Interno             | Descrição                                                           |
| ------------------------- | ----------- | -------------------------- | ------------------------------------------------------------------- |
| `BusinessException`       | 400         | `AUTH_001`, `TRANS_001`    | Regras de negócio (ex: E-mail já em uso).                           |
| `BadCredentialsException` | 401         | `AUTH_INVALID_CREDENTIALS` | Senha ou e-mail incorretos no login.                                |
| `SecurityException`       | 403         | `SECURITY_ERR`             | Tentativa de manipular dados de outro usuário.                      |
| `MethodArgumentNotValid`  | 422         | `VALIDATION_ERR`           | Falha nas anotações `@Valid` (ex: senha curta).                     |
| `Exception` (Cata-tudo)   | 500         | `INTERNAL_SERVER_ERROR`    | Erros inesperados de servidor, banco de dados ou timeout da IA.     |

## 🚀 Como Rodar o Projeto Localmente

### Pré-requisitos
- Docker e Docker Compose
- Java 17+
- Chave de API do Google Gemini (Google AI Studio)

### Passos

1. Clone o repositório:
   ```bash
   git clone [https://github.com/RuanPablo2/BeanCounter.git](https://github.com/RuanPablo2/BeanCounter.git)
   cd BeanCounter
   ```

2. Configure as variáveis de ambiente:
   Crie um arquivo `application-dev.properties` ou injete a seguinte variável com a sua chave da IA:
   ```properties
   gemini.api.key=SUA_CHAVE_AQUI
   ```

3. Suba a infraestrutura do Banco de Dados (PostgreSQL):
   ```bash
   docker-compose up -d
   ```

4. Compile e rode a API localmente:
   ```bash
   ./mvnw spring-boot:run
   ```

5. Acesse a documentação interativa:
   Abra o navegador em `http://localhost:8080/` para ser redirecionado para o **Swagger UI**.

## 👨‍💻 Autor

Desenvolvido por Ruan Pablo (https://github.com/RuanPablo2). Feedbacks e contribuições são bem-vindos!
