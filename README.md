# Netdeal Fullstack Task
## O que o cliente espera da entrega:

- Um CRUD para cadastrar a hierarquia de colaboradores dentro de uma empresa e suas respectivas senhas;
- Visualizar a hierarquia e a força da senha de cada colaborador.

## O que esperamos da sua entrega para o cliente:

- Sua criatividade para uma boa experiência no CRUD e utilização da hierarquia dos colaboradores. Surpreenda o cliente;
- Organize bem seu código e siga boas práticas;
- O layout acima é só um exemplo, use a sua criatividade.

## Como você deve desenvolver o código:

- Desenvolver o front-end em AngularJS versão 1. Não utilizar bibliotecas de terceiros para os componentes;
- Desenvolver o back-end em Java, utilizando Spring Framework;
- Implementar a força da senha seguindo as regras deste site; OBRIGATÓRIO
- A regra de negócio da força da senha deve estar no back-end;
- A senha deve ser criptografada;
- Salvar o score da senha que foi processado;
- Você é livre para estruturar o projeto da maneira que achar mais organizada;
- Você pode adicionar funcionalidades ao componente como desejar;
- Os dados devem ser armazenados em um banco de dados (MySQL ou MongoDB);
- Implementar testes unitários para o backend.
- Utilizar SQL de Migração de banco de dados para construir as tabelas, não utilizar o auto generate do Hibernate.

## O que vamos avaliar no código:

- Acoplamento, coesão, clean code, patterns, etc;
- Performance;
- Experiência do usuário (área de clique, scroll jump, design, etc);

Avaliaremos o seu nível de senioridade de acordo com a quantidade de implementações dos requisitos listados.

# Dicas para executar o codigo
## Banco de dados MySQL
Na pasta `/backend` existe um aqui chamado `docker-compose.yml`
```
docker compose up -d
```

## Backend
- Versão do Java utilizada `OpenJdk 21`
Na pasta de `/backend` executar o comando do Maven para realizar o `clean` e depois o `install` após isso pode executar a aplicação como preferir pela ide ou linha de comando.

## Frontend
Acessar a pasta `/frontend` se possuir o node instalado pode utilizar o `package.json` para auxiliar na execução usando os comandos:
```
npm install -g http-server
npm run start
```
