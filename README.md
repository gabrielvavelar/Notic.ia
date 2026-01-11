# Notic.ia 📰

## Sobre o Projeto
Notic.ia é um projeto que envia resumos diários das principais notícias coletadas no site G1 para o e-mail dos inscritos.  
O sistema coleta automaticamente as notícias usando web scraping, gera um resumo utilizando a API do Gemini e envia o conteúdo para os assinantes.

## ⚙️ Funcionamento
O sistema opera em um fluxo totalmente automatizado:

1. **Agendamento:** O `Spring Scheduler` inicia o processo diariamente às 07:00h.

2. **Extração:** O `Jsoup` realiza o web scraping das notícias em destaque.

3. **Processamento:** A API do Gemini processa os textos brutos e gera resumos concisos.

4. **Entrega:** O sistema dispara e-mails formatados via `Thymeleaf` de forma assíncrona através de `Spring Async`.

## 💡 Decisões de Projeto
- **Web Scraping vs RSS:** Optei pelo web scraping em vez do feed RSS padrão para garantir a captura apenas das 
notícias que o portal classifica como "Destaque Principal" na UI, oferecendo maior relevância ao usuário final.

- **Arquitetura REST:** Embora o projeto use templates para e-mail, a estrutura é de uma API REST, permitindo que o 
cadastro de assinantes seja consumida por qualquer front-end.

## 🛠️ Stack Tecnológica
- Java 21
- Spring Boot
- PostgreSQL
- H2 Database (testes)
- Swagger / OpenAPI
- MapStruct
- Jsoup
- Google Gemini API

## 🌱 Recursos e Ecossistema Spring
- Spring Data JPA / Hibernate – Persistência de dados
- Spring Validation – Validação de dados
- Spring Mail – Envio de e-mails
- Spring Scheduler – Execução de tarefas agendadas
- Spring Async – Processamento assíncrono
- Thymeleaf – Templates HTML para e-mails

## ✒️ Autor
[Gabriel Venancio de Avelar](https://github.com/gabrielvavelar)
