<h1 align="center">Reader Equals</h1>
<h4 align="center">Projeto criado para leitura de arquivos de log de maquinas de vendas.</h4>

<p align="center">
  <img src="https://github.com/guhcostan/reader-equals/blob/master/frontend/src/assets/logo_equals.png?raw=true">
</p>

https://reader-equals.herokuapp.com

## Tecnologias

### Backend
    - Spring Boot
    - Hibernate
    - Lombok
    - PostgreSQL
    - JUnit
    
### Frontend
    - React
    - Element UI
    - Cria React UI

## Instalação

OS X & Linux:

- Instalar PostgreSQL 9.6 e criar esquema reader.

```sh
cd frontend
npm install
npm run serve
cd ../backend
mvn spring-boot:run
```

## Deploy

```sh
cd frontend
npm install
npm run build
cd ../backend
mvn clean package
```

## Release History

- 11/07/2019 - 1.0.0 - Primeira versão gerada com serviços basicos.


## Meta

Gustavo Costa – [@guhcostan](https://instagram.com/guhcostan) – gustavo.neves@aluno.ufla.br

Distributed under the XYZ license. See ``LICENSE`` for more information.

## Contributing
1. Fork it (<https://github.com/guhcostan/reader-equals/fork>)
2. Create your feature branch (`git checkout -b feature/fooBar`)
3. Commit your changes (`git commit -am 'Add some fooBar'`)
4. Push to the branch (`git push origin feature/fooBar`)
5. Create a new Pull Request
