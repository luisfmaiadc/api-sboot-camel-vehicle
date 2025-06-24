<h1 align="center">api-sboot-camel-vehicle</h1> 
<p align="center" style="margin-bottom: 20;">
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" /> 
  <img src="https://img.shields.io/badge/springboot-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/apache%20camel-E62B1E?style=for-the-badge&logo=apache&logoColor=white" alt="Apache Camel" />
  <img src="https://img.shields.io/badge/apache%20maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven" />
</p> 

<p align="center">O <b>api-sboot-camel-vehicle</b> é um componente de orquestração baseado em <b>Apache Camel</b>, desenvolvido para integrar a API de veículos com a API de oficinas. Ele atua como uma camada intermediária que recebe requisições REST e as direciona para os serviços adequados, encapsulando regras de roteamento e transformação de dados quando necessário.</p>

<h2>📌 Visão Geral</h2>
<p align="justify">Este projeto foi criado com o objetivo de desacoplar a lógica de integração entre os microsserviços de veículos e oficinas. Utilizando o <b>Apache Camel</b>, o componente expõe rotas REST que consomem a <b>api-sboot-jdbi-workshops</b> e retornam os dados tratados de acordo com a necessidade do consumidor.</p> 

<p>Com isso, a responsabilidade de orquestração e integração fica centralizada, facilitando a manutenção e a escalabilidade da arquitetura.</p>

<h2>🚀 Tecnologias Utilizadas</h2>

- <b>Java 21 + Spring Boot 3.5.0</b>
- <b>Apache Camel</b> (orquestração de rotas e integração)
- <b>Spring Web</b>
- <b>Rest DSL + ToD (toDynamic)</b> do Camel

<h2>🏗️ Estrutura do Projeto</h2>

```bash
api-sboot-camel-vehicle
│-- src/main/java/com/portfolio/luisfmdc/api-sboot-camel-vehicle
│   ├── route/                         # Classe principal com as rotas Camel
│   ├── processor/                     # Processadores customizados
│   └── CamelVehicleApplication.java   # Classe principal Spring Boot
│
│-- src/main/resources
│   ├── application.properties         # Configuração do serviço
│
│-- pom.xml                            # Dependências do projeto
```

<h2>📡 Rotas Disponíveis</h2>

<p>O componente expõe os seguintes endpoints:</p>

| Método | Caminho                              | Descrição                                                   |
| ------ | ------------------------------------ | ----------------------------------------------------------- |
| GET    | `/api/orch/workshops`                | Busca oficinas com filtros via query parameters.            |
| GET    | `/api/orch/workshops/specialty/{id}` | Busca oficinas relacionadas a uma especialidade específica. |

<p>🔗 Essas rotas são redirecionadas internamente para a api-sboot-jdbi-workshops.</p>

<h2>🛠️ Configuração e Execução</h2>
<h3>📌 application.properties</h3>

```properties
spring.application.name=api-sboot-camel-vehicle
camel.servlet.mapping.context-path=/api/orch/*
camel.springboot.main-run-controller=true
server.port=8082
api.workshop.url=http://localhost:8081/workshop
```

<h3>🚀 Executando a aplicação</h3>

```sh
git clone https://github.com/luisfmaiadc/api-sboot-camel-vehicle.git
cd api-sboot-camel-vehicle

mvn clean install
mvn spring-boot:run
```

<h2>🔁 Integração Entre Microsserviços</h2>

```bash
1. [Cliente/API Consumers] → /api/orch/workshops
2. [api-sboot-camel-vehicle] → /workshop (api-sboot-jdbi-workshops)
```

<p>Este componente atua como um roteador inteligente, traduzindo e propagando as chamadas REST feitas pelo cliente para a API de oficinas, sem que o cliente precise conhecer diretamente a outra API.</p>

<h2>📚 Mais Informações</h2> <p>Este projeto faz parte de uma arquitetura de microsserviços desenvolvida com fins didáticos e exploratórios, com foco em desacoplamento entre componentes, reutilização de lógica e uso de padrões de integração como os propostos pelo <b>Apache Camel</b>.</p>