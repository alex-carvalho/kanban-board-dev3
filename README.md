# kanban-board-dev3

Projeto desenvolvido para disciplina Desenvolvimento de Software III da Unisinos.

Desenvolvido utilizando WildFly Swarm, CDI, JPA, JTA, Flyway e no front-end JSF, Bootstrap, jQuery.

No ambiente de desenvolvimento é utilizado banco de dados H2 e na desmostração PostgreSQL.

Url da demostração hopedada no Heroku: https://kanban-dev3.herokuapp.com/ 

**Como executar em desenvolvimento:**
* Maven plugin
```
mvn wildfly-swarm:run
```

* Executando o jar:
```
mvn package 

java -jar ./target/myapp-swarm.jar
```
* Pela IDE executar a classe principal:
 ```
 org.wildfly.swarm.Swarm
 ```

**Em ambiente de produção é preciso ter as seguintes variaveis de ambiente:**
```
JDBC_DATABASE_URL
JDBC_DATABASE_USERNAME 
JDBC_DATABASE_PASSWORD
```

Também é preciso passar o seguinte argumento para o programa:
```
-Sprd
```

Exemplo de configuração do Heroku pode ser vista [aqui.](Procfile)