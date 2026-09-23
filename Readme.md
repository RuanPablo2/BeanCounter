# 📊 Bean Counter (Financial Dashboard) - Front-end (Angular)

![Angular](https://img.shields.io/badge/Angular-DD0031?style=flat&logo=angular&logoColor=white)
![TypeScript](https://img.shields.io/badge/TypeScript-007ACC?style=flat&logo=typescript&logoColor=white)
![Angular Material](https://img.shields.io/badge/Material-FF4081?style=flat&logo=angular&logoColor=white)
![SCSS](https://img.shields.io/badge/SCSS-CC6699?style=flat&logo=sass&logoColor=white)
![Google Gemini](https://img.shields.io/badge/AI_Powered-Google_Gemini-8E75B2?style=flat&logo=google&logoColor=white)
![Netlify](https://img.shields.io/badge/Deployed_on-Netlify-00C7B7?style=flat&logo=netlify&logoColor=white)

🚀 **Live Demo:** [Acesse a aplicação em produção aqui](https://beancounter-iota.vercel.app)

## 💻 Sobre o Projeto
Esta é a interface de usuário (SPA - Single Page Application) de um sistema de gestão financeira pessoal. Focado em usabilidade e performance, o painel oferece aos usuários uma visão clara de suas finanças, permitindo o gerenciamento completo de receitas e despesas por inserção manual ou **via Inteligência Artificial**.

**Nota de Arquitetura:** Este projeto consome uma API RESTful desenvolvida em **Java com Spring Boot**. O código-fonte do back-end pode ser encontrado no repositório dedicado da API.

## ✨ Destaques Técnicos do Front-end
Este projeto foi construído utilizando as melhores práticas do ecossistema Angular moderno:
* **Inteligência Artificial (NLP) via Google Gemini:** Entrada inteligente de dados. O usuário relata um gasto ou ganho em linguagem natural (ex: *"Gastei 50 reais de Uber ontem"*). O sistema processa o texto, calcula datas relativas, categoriza o gasto, salva no banco e retorna um feedback amigável via `MatSnackBar`.
* **Filtros Dinâmicos e Inteligentes (Client-side):** 
  * Filtro Rápido com `MatChips` gerados automaticamente em tempo real com base nas categorias extraídas dos dados exibidos.
  * Busca instantânea na tabela usando `MatTableDataSource` (sem requisições extras ao back-end).
  * Filtro por período de datas através do `MatDateRangePicker`.
* **Autenticação e Segurança:** Fluxo completo de Login e Registro de usuários, com proteção de rotas (Route Guards) e gerenciamento de sessão com Token JWT interceptado dinamicamente nas requisições.
* **Arquitetura Standalone:** Utilização nativa de Standalone Components no Angular 18, dispensando o uso de `NgModules` para um código mais limpo, modular e de fácil manutenção.
* **Theme Management (Dark Mode):** Sistema dinâmico de temas (Claro/Escuro) construído com CSS Variables e SCSS nativo, incluindo persistência da preferência do usuário via `localStorage`.

## 📸 Screenshots

### 🔐 Autenticação (Login e Registro)
<img width="553" height="542" alt="image" src="<img width="889" height="641" alt="1" src="https://github.com/user-attachments/assets/7c2ed21b-749f-42e5-8178-4fd9f810c07a" />" />
<img width="547" height="622" alt="image" src="<img width="846" height="638" alt="2" src="https://github.com/user-attachments/assets/6d42152b-fdc0-4e53-a229-4ff54fdf59df" />" />

### 🤖 Entrada via IA & Filtros Dinâmicos
<img width="975" height="641" alt="4" src="https://github.com/user-attachments/assets/afd522e9-fd17-41c5-adca-0e3959b0d6cc" />

### ☀️ Dashboard - Light Mode
<img width="994" height="639" alt="3" src="https://github.com/user-attachments/assets/5c3e8789-939d-4878-af0c-289e74f38e91" />

### 🌙 Dashboard - Dark Mode
<img width="973" height="640" alt="dark mode" src="https://github.com/user-attachments/assets/90c507f0-4455-402e-88f7-1c49167cecb9" />

### ⚙️ Filtros
<img width="979" height="607" alt="6" src="https://github.com/user-attachments/assets/c664b4e3-fa45-4078-9917-929f98553576" />


## 🛠️ Stack Tecnológica
* **Framework:** Angular 18 (Standalone Components)
* **Linguagem:** TypeScript
* **Estilização:** SCSS (Sass)
* **Biblioteca de UI:** Angular Material
* **Integração Externa:** Google Gemini API (via Back-end)
* **Gerenciamento de Estado:** RxJS
* **Deploy/CI-CD:** Netlify

## 🚀 Como executar o projeto localmente

### Pré-requisitos
* Node.js (v18 ou superior)
* Angular CLI (`npm install -g @angular/cli`)

### Instalação e Execução
```bash
# Clone este repositório
git clone [https://github.com/RuanPablo2/BeanCounter-ui](https://github.com/RuanPablo2/BeanCounter-ui)

# Acesse a pasta do projeto
cd BeanCounter-ui

# Instale as dependências
npm install

# Inicie o servidor local
ng serve
