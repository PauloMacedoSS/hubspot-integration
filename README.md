# HubSpot Integration API

Uma API REST desenvolvida com Spring Boot (Java 11) para integrar com o HubSpot usando OAuth2. A aplicação permite autenticação, criação de contatos e recebimento de webhooks de criação de contatos.

## 🚀 Funcionalidades
- 🔑 Geração de URL de autorização OAuth2 com o HubSpot
- 🔄 Troca de código de autorização por token de acesso
- 👤 Criação de contatos no CRM HubSpot
- 📩 Recebimento de webhooks do tipo `contact.creation`

## 📁 Estrutura do Projeto
```
src/main/java/com/seuprojeto
├── config/         # Configurações do OAuth
├── controller/     # Controllers dos endpoints
├── service/        # Lógica de negócio
├── dto/            # Data Transfer Objects
├── webhook/        # Processamento de webhooks
└── IntegrationApplication.java
```

## ⚙️ Requisitos
- Java 11
- Maven 3.6+

## 📦 Instalação e Execução
```bash
# Clonar o repositório

$ cd hubspot-integration-api

# Configurar o application.yml com suas credenciais do HubSpot
$ vim src/main/resources/application.yml
```
Exemplo de `application.yml`:
```yaml
spring:
  application:
    name: hubspot-integration

hubspot:
  client-id: SEU_CLIENT_ID
  client-secret: SEU_CLIENT_SECRET
  redirect-uri: http://localhost:8080/api/oauth/callback
  scope: contacts
  auth-url: https://app.hubspot.com/oauth/authorize
  token-url: https://api.hubapi.com/oauth/v1/token
  api-url: https://api.hubapi.com
```

```bash
# Build e execução
$ mvn clean install
$ mvn spring-boot:run
```

## 🧪 Testando os Endpoints

### 1. Gerar URL de Autorização
```
GET http://localhost:8080/api/oauth/authorize-url
```

### 2. Callback OAuth (interno, chamado pelo HubSpot após login)
```
GET http://localhost:8080/api/oauth/callback?code=CODE
```

### 3. Criar Contato
```
POST http://localhost:8080/api/contacts
Content-Type: application/json

{
  "email": "usuario@teste.com",
  "firstName": "Usuário",
  "lastName": "Teste"
}
```

### 4. Receber Webhook
```
POST http://localhost:8080/api/webhook
Content-Type: application/json

{
  "eventType": "contact.creation",
  "data": {
    ...
  }
}
```

## 🔒 Segurança
- Implementação OAuth2 com `client_id` e `client_secret`
- Verificação futura de assinaturas de webhooks (em desenvolvimento)

## 📚 Referências
- [Documentação OAuth HubSpot](https://developers.hubspot.com/docs/api/oauth/)
- [HubSpot Webhooks](https://developers.hubspot.com/docs/api/webhooks)

---
