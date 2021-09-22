## Encontra CEP

Aplicação para consulta de CEP para retorno de endereço, o qual o cliente envia uma requisição
HTTP utilizando o protocolo de comunicação REST, nesta aplicação
fazemos uma integração com a API Webservice viacep, utilizando a classe
RestTemplate. Também, há validações do CEP,se for invalido, retornam 400 "bad request".
Caso o CEP não for encontrado, modificamos o CEP para retentativas no viacep,
tendo um CEP zerado (0000000) retornamos um erro de CEP inválido.


Tendo como tecnologias para o desenvolvimento dessa API:

- Spring boot/ Java 11
- JUnit
- REST
- MVC


![](img/fluxo_aplicacao.png)

## Pre-requisites

- Java 11
- Maven 


## Dependencies


**Health Check**

Dependência para verificar a saúde da aplicação
```gradle
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>2.9.2</version>
		</dependency>
```
**Swagger** 

Dependência para documentar a API

```gradle
        <dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>3.0.0</version>
		</dependency>
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>2.9.2</version>
		</dependency>
```
**Http Components**

Dependência para criar a comunicação, utilizando RestTemplate, o qual se comunica com o serviço Webservice Via CEP.
```gradle
		<dependency>
			<groupId>org.apache.httpcomponents</groupId>
			<artifactId>httpclient</artifactId>
			<version>4.3.4</version>
		</dependency>
```
## Running application

Para rodar a aplicação localmente, o seguinte comando maven pode ser utilizado:

```gradle
./mvn clean install run
```

## Endpoints

**GET /v1/endereco**

```gradle
curl 'http://localhost:8080/v1/endereco?cep=04028000'
```

**Health Check**


````gradle
 curl 'http://localhost:8080/actuator/health'  
 
````
