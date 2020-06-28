# Central de Monitoramento de Erros em Aplicações Web Squad 2

## Objetivo
Em projetos modernos é cada vez mais comum o uso de arquiteturas baseadas em serviços ou microsserviços. Nestes ambientes complexos, erros podem surgir em diferentes camadas da aplicação (backend, frontend, mobile, desktop) e mesmo em serviços distintos. Desta forma, é muito importante que os desenvolvedores possam centralizar todos os registros de erros em um local, de onde podem monitorar e tomar decisões mais acertadas. Neste projeto vamos implementar um sistema para centralizar registros de erros de aplicações.

A arquitetura do projeto é formada por:

## Backend - API
* Deve implementar as funcionalidades apresentadas nos wireframes;
* Deve ser acessada adequadamente tanto por navegadores desktop quanto mobile;
* Deve consumir a API do produto;
* Desenvolvida na forma de uma Single Page Application.

## Observações:
* Se a aceleração tiver ênfase no backend (Java, Python, C#, Go, PHP, etc) a equipe deve obrigatoriamente implementar a API. **A implementação do frontend não é necessária;**
* Se a aceleração tiver ênfase em frontend (React, Vue, Angular, etc) a equipe deve obrigatoriamente implementar o frontend da aplicação e o backend pode ser substituido por uma aplicação mock. **A implementação da API não é necessária,** caso o time deseje podem ser utilizados mocks.

## Ferramentas utilizadas:
* [Trello](https://trello.com/)
* [Postman](https://www.postman.com/)
* [swagger](https://swagger.io/)
* [Spring](https://spring.io/)
* [Maven](https://maven.apache.org/)
* [Heroku](https://www.heroku.com/)
* [Postgresql](https://www.postgresql.org/)
* [GitHub](https://github.com/)


## Instalação Back-End
Siga os passos abaixo:
* Baixe o repositório em seu ambiente utilizando: git clone https://github.com/hugogcorrea/CentralDeErros.git
* Crie o DB com o nome CentralDeErros em seu banco de dados.

## Especificações do Back-End central-erros:
* Endpoints para serem usados pelo frontend da aplicação.
* Endpoint que será usado para gravar os logs de erro em um banco de dados relacional.
* API segura, permitindo acesso apenas com um token de autenticação válido.

## JWT
/authenticate
{
    "username":"springuser", 
    "password":"password"
    
}

/register
{
    "username":"springuser", 
    "password":"password"
    
}

### Swagger
Nesse projeto foi utilizado o editor Swagger para a validação e documentação da API desenvolvida pelos integrantes da Squad 2.
Documentação e testes:
http://localhost:8080/swagger-ui.html

{
* fullName: "###########",
* username: "###########",

### WireFrame

<img width="1028" alt="image1" src="https://user-images.githubusercontent.com/18729026/85949005-74595d00-b92a-11ea-803a-8cb2167f6877.png">
<img width="1028" alt="image2" src="https://user-images.githubusercontent.com/18729026/85949006-758a8a00-b92a-11ea-9dbd-9c6b84476fa6.png">
<img width="1027" alt="image3" src="https://user-images.githubusercontent.com/18729026/85949008-758a8a00-b92a-11ea-8f88-cea0e58bda22.png">
<img width="1028" alt="image4" src="https://user-images.githubusercontent.com/18729026/85949009-77544d80-b92a-11ea-939b-8decc3153bf4.png">
<img width="1027" alt="image5" src="https://user-images.githubusercontent.com/18729026/85949010-77ece400-b92a-11ea-9e00-e732100fad9d.png">
<img width="1026" alt="image6" src="https://user-images.githubusercontent.com/18729026/85949011-78857a80-b92a-11ea-8e38-31f28743d05d.png">
<img width="1032" alt="image7" src="https://user-images.githubusercontent.com/18729026/85949012-791e1100-b92a-11ea-9e27-9073650e4bec.png">








* password:  ###########
}

Essa requisição terá como resposta um token no formato: {Bearer }. Essa resposta deve ser copiada na opção authorize para que o token seja enviado em qualquer operação da aplicação garantindo o acesso.
