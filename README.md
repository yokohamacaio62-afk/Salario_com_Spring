Worker

API REST desenvolvida com Spring Boot para gerenciar trabalhadores, departamentos e contratos por hora, com cálculo da renda mensal de cada trabalhador.

Tecnologias
Java 25
Spring Boot 4.1.1
Spring Web
Spring Data JPA
Banco de dados H2 (em memória)
Maven
Modelo de domínio
Department: departamento ao qual o trabalhador pertence (id, name).
Worker: trabalhador (id, name, level, baseSalary, department, contracts).
HourContract: contrato por hora (id, date, valuePerHour, hours).
WorkerLevel: enum com o nível do trabalhador (JUNIOR, MID_LEVEL, SENIOR).

Relacionamentos:

Um departamento possui vários trabalhadores.
Um trabalhador possui vários contratos por hora.
Estrutura do projeto
src/main/java/com/dev/worker
├── config          # SeedingConfig: popula o banco com dados de teste
├── controller      # WorkerController: endpoints da API
├── dto             # WorkerIncomeDTO: resposta do cálculo de renda
├── entities        # Department, Worker, HourContract, WorkerLevel
├── repositories    # DepartmentRepository, WorkerRepository
└── WorkerApplication.java
Como executar

Pré-requisitos: JDK 25 e Maven (ou use o wrapper incluído no projeto).

bash
# clonar o repositório
git clone https://github.com/yokohamacaio62-afk/worker.git
cd worker

# executar
./mvnw spring-boot:run

No Windows:

bash
mvnw.cmd spring-boot:run

Também é possível rodar pelo IntelliJ, abrindo WorkerApplication.java e clicando em Run (Shift + F10).

A aplicação sobe em http://localhost:8080.

Endpoints
Calcular a renda de um trabalhador em um mês
GET /workers/{id}/income/{year}/{month}
Parâmetro	Descrição
id	ID do trabalhador
year	Ano (ex.: 2018)
month	Mês, de 1 a 12

Exemplo:

GET http://localhost:8080/workers/1/income/2018/8

Exemplo de resposta (os campos seguem o WorkerIncomeDTO):

json
{
  "name": "Alex",
  "department": "Design",
  "income": 3000.0
}

A renda é calculada como o salário base somado ao valor de todos os contratos do mês e ano informados (valor por hora × horas).

Banco de dados H2

O banco é em memória e os dados de exemplo são criados na inicialização pela classe SeedingConfig.

Console do H2: http://localhost:8080/h2-console

Campo	Valor
JDBC URL	jdbc:h2:mem:testdb
User	sa
Password	(em branco)
Configuração

Arquivo src/main/resources/application.properties:

properties
spring.application.name=workers

spring.jpa.open-in-view=true

# H2 connection configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=

# H2 client configuration
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# SQL show configuration
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
