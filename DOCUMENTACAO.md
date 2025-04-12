# 📄 Documentação Técnica – HubSpot Integration API

## 📌 Visão Geral

Esta API REST desenvolvida em **Java 11 com Spring Boot 2.7.15** tem como objetivo integrar um sistema backend com a plataforma **HubSpot**. A integração utiliza o fluxo **OAuth2** para autenticação e permite a **criação de contatos** e o **processamento de webhooks** de eventos de criação de contatos.

---

## ⚙️ Arquitetura e Decisões Técnicas

### 👨‍💻 Tecnologias Escolhidas

| Tecnologia         | Justificativa                                                               |
|--------------------|------------------------------------------------------------------------------|
| **Java 11**        | LTS (Long-Term Support), estabilidade e suporte para APIs modernas.          |
| **Spring Boot**    | Rapidez no desenvolvimento, estrutura modular e integração com REST APIs.   |
| **Maven**          | Ferramenta padrão de build para gerenciamento de dependências.              |
| **Jackson**        | Serialização/deserialização eficiente de JSON.                              |
| **Lombok**         | Redução de boilerplate (getters, setters, construtores).                    |

---

### 📁 Organização do Projeto

A estrutura do projeto segue o padrão MVC com separação clara de responsabilidades:

src/main/java/com/seuprojeto ├── config/ # Configurações do OAuth ├── controller/ # Controllers dos endpoints ├── service/ # Lógica de negócio ├── client/ # Clientes externos (opcional) ├── dto/ # Data Transfer Objects ├── webhook/ # Processamento de webhooks └── Application.java

yaml
Copiar
Editar

---

## 🔒 Segurança

- Implementação OAuth2 com `client_id`, `client_secret` e `redirect_uri`.
- Tokens armazenados apenas em memória.
- (Melhoria futura) Validação de assinatura HMAC de webhooks via header `X-HubSpot-Signature`.

---

## 📦 Bibliotecas e Plugins Utilizados

### Dependências

| Biblioteca                      | Função                                                        |
|--------------------------------|---------------------------------------------------------------|
| `spring-boot-starter-web`      | Criação dos endpoints REST.                                  |
| `jackson-databind`             | Conversão entre JSON e objetos Java.                         |
| `spring-boot-configuration-processor` | Suporte a `@ConfigurationProperties`.             |
| `lombok`                       | Redução de código repetitivo.                                |
| `spring-boot-starter-test`     | Suporte a testes unitários e de integração.                  |

### Plugins

- `spring-boot-maven-plugin`: Geração e execução da aplicação Spring.
- `maven-compiler-plugin`: Define versão do Java (11).

---

## 🚀 Pontos Fortes

- Projeto modular, fácil de manter.
- Baixo acoplamento entre camadas.
- Base sólida para escalabilidade e extensões futuras (ex: Deals, Tickets, Webforms).

---

## 🔮 Melhorias Futuras

1. **Persistência de Token**
   - Salvar `access_token` e `refresh_token` em banco de dados seguro.

2. **Renovação Automática de Token**
   - Implementar processo automático de renovação via `refresh_token`.

3. **Validação de Webhooks**
   - Implementar verificação HMAC com `X-HubSpot-Signature`.

4. **Rate Limiting / Retry Automático**
   - Gerenciar limites de requisição e falhas com bibliotecas como `Resilience4j`.

5. **Cobertura de Testes**
   - Testes unitários e de integração com cobertura automatizada.

6. **Suporte Multi-Conta**
   - Permitir múltiplas integrações com diferentes contas HubSpot.
