# wishlist-thiago-lenz
Projeto WishList desenvolvido por Thiago Lenz. Spring Boot, java 11+, TDD 

## Rodando o projeto com Docker e MongoDB

### Tem uma pasta chamada Docker
- Rodar via shell script o arquivo `./run_docker_compose.sh`, dependendo do computador vai precisar de sudo.
- Vai subir uma instancia do MongoDB

## Executar o arquivo Main 
- Arquivo main `WishListThiagoLenzApplication`

## O projeto contém swagger 

-Disponível nesse link após subir a aplicação:
    http://localhost:8080/swagger-ui/index.html#/

- Todos os testes manuais de REST podem ser executados por ai. 

## Processo de desenvolvimento 
- Olhando pelo histórico de commits comecei pela definição de projeto
- Criei algumas configurações de docker compose, 
- Criei uma classe base chamada `ProdutosService` e fiz os metodos principais vazios
- Fiz testes unitários básico quebrando intecionalmente
- Em seguida fui desenvolvendo a classe para atender todas as regras dos testes
- Foi necessário ajustar os testes no processo 
- Em seguida fiz a API, Facade e integração entre as classes
- Comecei a rodar os testes e percebi um erro de conexao com MongoDB
- Ajustei o erro e os testes via API funcionaram 
- Adicionei ExceptionHandler no projeto, além de Beans validation 
- Fiz mais alguns ajustes e refatorações
- Adicionei também o test Container para poder subir a aplicação com um container em memória do MongoDB
- Não desenvolvi testes integrados de chamada via API. 
- Por fim coloquei Swagger para poder utilizar no browser

# Para compilar na linha de comando 

- Executar o seguinte script no shell na raiz do projeto
`mvn clean install`

