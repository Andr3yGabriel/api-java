
# API de Controle de Despesas
## Descrição
Este projeto é uma API REST desenvolvida em Java utilizando o framework Spring. O objetivo principal desta API é permitir que os usuários possam gerenciar suas despesas de maneira eficiente. A API se integra a um banco de dados H2 para armazenamento das informações e conta com autenticação de usuário utilizando Spring Security, garantindo a segurança dos dados.

## Funcionalidades
* Cadastro de Usuário: Permite o cadastro de novos usuários na aplicação.
* Autenticação: Implementação de autenticação de usuário utilizando Spring Security.
* Gerenciamento de Despesas: CRUD (Create, Read, Update, Delete) de despesas do usuário.
* Consulta de Despesas: Permite a consulta das despesas cadastradas por diferentes critérios.

## Tecnologias Utilizadas
* Java: Linguagem de programação utilizada para desenvolvimento da API.
* Spring Boot: Framework para facilitar a configuração e o desenvolvimento da aplicação.
* Spring Data JPA: Abstração para acesso ao banco de dados.
* Spring Security: Framework para autenticação e controle de acesso.
* H2 Database: Banco de dados em memória utilizado para armazenamento das despesas.
* Maven: Gerenciador de dependências e build da aplicação.

# Como Executar o Projeto
## 1 - Pré-requisitos:

* Java 11 ou superior instalado
* Maven instalado

## 2 - Clonar o Repositório:
`git clone https://github.com/seu-usuario/seu-repositorio.git](https://github.com/Andr3yGabriel/api-java`  
`cd api-java`

## 3 - Compilar e Executar:

`mvn clean install`  
`mvn spring-boot:run`

## 4 - Acessar a API:

A API estará disponível em: http://localhost:8080

# Endpoints Principais

## Autenticação
* POST /auth/login: Realiza a autenticação do usuário.

## Usuários
* POST /auth/register: Cadastro de novo usuário.

## Despesas (Rotas protegidas por autenticação)
* GET /expenses: Lista todas as despesas do usuário autenticado.
* POST /expenses: Cadastra uma nova despesa.
* PUT /expenses/{id}: Atualiza uma despesa existente.
* DELETE /expenses/{id}: Remove uma despesa existente.

# Configuração do Banco de Dados

O projeto utiliza o banco de dados H2 em memória. A configuração padrão está definida no arquivo application.properties e pode ser alterada conforme necessário.
~~~
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
~~~

# Segurança
A segurança da API é gerenciada pelo Spring Security. Para acessar os endpoints protegidos, é necessário autenticar-se utilizando as credenciais cadastradas. A autenticação é baseada em tokens JWT.

# Contribuindo
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e enviar pull requests.
