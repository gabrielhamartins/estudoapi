# Construindo uma API REST para cadastro de usuários e carros.

## Introdução

Antes de entrar como Starter na GFT eu participei de um programa da Zup chamado Orange Talents e lá 
fui desafiado a construir uma API REST. Na época eu não sabia praticamente nada de spring e rest api então
eu aprendi tudo fazendo na hora. Agora com o conhecimento adquirido durante o estágio eu resolvi usar este enunciado
para exercitar o que foi aprendido e aplicar no desafio.

## Enunciado do desafio: 

Você está fazendo uma API REST que precisará controlar veículos de usuários.

O primeiro passo deve ser a construção de um cadastro de usuários, sendo obrigatórios: nome, e-mail, CPF e data de nascimento, sendo que e-mail e CPF devem ser únicos.

O segundo passo é criar um cadastro de veículos, sendo obrigatórios: Marca, Modelo do Veículo e Ano. E o serviço deve consumir a API da FIPE (https://deividfortuna.github.io/fipe/) para obter os dados do valor do veículo baseado nas informações inseridas.

O terceiro passo é criar um endpoint que retornará um usuário com a lista de todos seus veículos cadastrados.

Você deve construir 3 endpoints neste sistema, o cadastro do usuário, o cadastro de veículo e a listagem dos veículos para um usuário específico.

No endpoint que listará seus veículos, devemos considerar algumas configurações a serem exibidas para o usuário final. Vamos criar dois novos atributos no objeto do carro, sendo eles:

1.) Dia do rodízio deste carro, baseado no último número do ano do veículo, considerando as condicionais:
Final 0-1: segunda-feira
Final 2-3: terça-feira
Final 4-5: quarta-feira
Final 6-7: quinta-feira
Final 8-9: sexta-feira

2.) Também devemos criar um atributo de rodízio ativo, que compara a data atual do sistema com as condicionais anteriores e, quando for o dia ativo do rodizio, retorna true; caso contrario, false.

Exemplo A: hoje é segunda-feira, o carro é da marca Fiat, modelo Uno do ano de 2001, ou seja, seu rodízio será às segundas-feiras e o atributo de rodízio ativo será TRUE.
Exemplo B: hoje é quinta-feira, o carro é da marca Hyundai, modelo HB20 do ano de 2021, ou seja, seu rodizio será às segundas-feiras e o atributo de rodízio ativo será FALSE.

- Caso os cadastros estejam corretos, é necessário voltar o Status 201. Caso hajam erros de preenchimento de dados, o Status deve ser 400.
- Caso a busca esteja correta, é necessário voltar o status 200. Caso haja erro na busca, retornar o status adequado e uma mensagem de erro amigável.

Se ficou fácil, te desafiamos a:

- Construir a aplicação sem utilizar Lombok;
- Desafiamos você a integrar a API da FIPE usando Spring-Cloud-Feign.

## Fluxo de execução do projeto

O endpoint (local) padrão do sistema é localhost:8080/v1/

0 - Criação dos modelos de dados usando MariaDB e Flyway
1 - CRUD's de usuário e veículo funcionando corretamente, tudo sendo testado no postman
2 - Criação das DTO's usando o MapStruct
3 - Handler customizado para as exceptions
4 - Validações
5 - Consumir a API da FIPE usando o OpenFeign
6 - Implementação do Spring Security
7 - Implementação do Swagger para documentação

![](https://github.com/gahamartins/Desafio-Orange-Talents/blob/main/images/1-springInitializer.PNG)

```
Figura 1 - Tela de configuração do Spring Initializr
```
## Login no sistema

Foram criadas duas credenciais para autenticação no sistema:

username:password
admin@email.com:admin
user@email.com:user

Sendo que o admin tem acesso a todos os endpoints e o user também, exceto os que excluem quaisquer dados no BD.

Ao efetuar o login, é retornado um JSON com o token JWT. Este token deve ser usado no header da requisição para que a api seja liberada.

Exemplo de requisição (POST) no endpoint localhost:8080/v1/auth e retorno de token:
![](https://github.com/gahamartins/Desafio-Orange-Talents/blob/main/images/2-applicationProperties.PNG)
```
Figura 2 - Requisição de login
```
Ou seja, foi criado localmente um banco de dados localizado na porta 5432 com o nome
“orange-talents-challenge” e dada a permissão para o Java acessar e editar ele dentro deste
projeto.

## Criação de usuários

Para a criação dos usuários foram feitas algumas validações, então nenhum campo (exceto data de nascimento) pode ser vazio ou nulo,
além de que o email e cpf devem ter formatos válidos e não podem já existir no BD. Exemplo de retorno com erros de validação:
![](https://github.com/gahamartins/Desafio-Orange-Talents/blob/main/images/3-userModel.PNG)
```
Figura 3 – Modelo de dado para o Usuário
```

## Criação de veículos

Para a criação de veículos, o ideal seria que o Front-End que fosse usar a aplicação consumisse a api da Fipe para buscar os dados.
São necessários apenas os dados marca, modelo e ano do veículo para cadastro, mas o retorno deste dado consome a api da Fipe em tempo real
retornando também o valor de tabela do veículo e dia/estado de rodízio, baseado na regra de negócio apresentada. Veja um exemplo:
![](https://github.com/gahamartins/Desafio-Orange-Talents/blob/main/images/4-carModel.PNG)
```
Figura 4 - Modelo de dado para o Carro
```


## Atribuir um carro a um usuário

Para atribuir um carro a um usuário, basta passar como parâmetros os seus respectivos id's no endpoint localhost:8080/v1/usuarios/{idUsuario}/veiculos/{idVeiculo}

## Swagger ui

O Swagger foi implementado para documentar a api, mostrando todos os endpoints e exemplos de requisição. Veja a tela inicial do Swagger-ui, que pode ser acessada no endpoint http://localhost:8080/swagger-ui.html
![](https://github.com/gahamartins/Desafio-Orange-Talents/blob/main/images/4-carModel.PNG)
```
Figura 4 - Modelo de dado para o Carro
```





**Código-Fonte** : 

**Gabriel H. A. Martins**

https://www.linkedin.com/in/gabrielhamartins


