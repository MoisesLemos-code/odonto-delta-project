## Como executar em desenvolvimento 💻

Esse é um guia básico de como executar em desenvolvimento o projeto, mais informações sobre requisitos, variáveis de ambiente
e formas de configuração podem ser encontradas na [nota técnica](./nota-tecnica.md).

### Passo 1 - Configuração do ambiente
Para utilização dos sistemas Molens é necessário primeiro fazer uma configuração local da máquina
para utilizar as ferramentas corporativas.

### Passo 2 - Subindo containers docker
Estes comandos irá subir todas as dependências como containers docker. Para isso ele deverá ser executado na pasta
raíz do repositório.

```bash
docker-compose -f docker-database.yml up -d
```
```bash
docker-compose -f docker-dependencies.yml up -d
```

### Passo 3 - Instalando dependências do front

No diretório raíz do projeto existirão duas pastas: _odonto-delta-api_ e _odonto-delta-app_. `odonto-delta-app` contêm dependências a serem instaladas,
para isso acesse esta pasta e execute o seguinte comando:

```bash
npm install
``` 
> Nota: Esse comando pode demorar um pouco.

### Passo 4 - Criar banco de dados
É necessário criar o banco de dados "molens" e o schema "odonto".

### Passo 5 - IntelliJ
É recomendado utilizar o IntelliJ como IDE e instalar os plugins _Lombok_ e _VueJs_.

Para subir a API, utilize a funcionalidade _Run Configurations_ do IntelliJ, presente em `Run > Edit Configurations`.
Na criação da _Run Configurations_ aponte para a classe principal do projeto `br.com.molens.odontoDelta.OdontoDeltaApplication`.
Em seguida, copie as variáveis de ambiente abaixo para o seu _Run Configuration_ e execute a API do Odonto Delta.

#### Variáveis de ambiente

```bash
TZ=America/Sao_Paulo;
DOMAIN=localhost;
MONGODB_HOST=mongo;
MONGODB_PORT=27017;
MONGODB_CONFIG_DB=config-db;
RABBITMQ_HOST=rabbitmq;
RABBITMQ_PORT=5672;
RABBITMQ_USERNAME=guest;
RABBITMQ_PASSWORD=guest;
DATABASE_VENDOR=postgresql;
DATABASE_HOST=database;
DATABASE_PORT=5432;
DATABASE_URL_NODB=jdbc:postgresql://database:5432/;
DATABASE_URL=jdbc:postgresql://database:5432/molens;
DATABASE_NAME=molens;
DATABASE_CLASSNAME=org.postgresql.Driver;
DATABASE_USER=postgres;
DATABASE_USERNAME=postgres;
DATABASE_PASSWORD=postgres;
DATABASE_PLATFORM=org.hibernate.dialect.PostgreSQLDialect;
VALIDATION_QUERY=SELECT 1;
DATABASE_VALIDATION_QUERY=SELECT 1;
LIQUIBASE_CONTEXTS=desenv;
VALIDATION_QUERY=SELECT 1;
DATABASE_VALIDATION_QUERY=SELECT 1;
```

Para executar o Front-end pode-se adicionar mais uma nova configuração de run no IntelliJ, só que agora para
_NPM_. Deve-se selecionar o _package.json_ do _odonto-delta-app_ e adicionar como comando `run` e como
script `serve`, ou pode-se abrir o diretório _odonto-delta-app_ e executar o comando abaixo.

```bash
npm run serve
```

### Passo 6 - Acessando projeto

Pronto, agora basta acessar: http://localhost:8080

#### Usuário para testes

Login:

```bash
admin
```
Senha:
```bash
123
```

[‹ voltar ao README](../README.md)

-----
© Copyright 2021 - All rights reserved | Todos os direitos Reservados

__AZ Tecnologia em Gestão__
