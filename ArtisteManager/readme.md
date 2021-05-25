# Squelette projet Client/Serveur


## Installation

Si vous n'avez pas d'IDE préféré, vous pouvez prendre IntelliJ => [Download Page](https://www.jetbrains.com/fr-fr/idea/download/#section=windows)

## Fork

Commencer par renommer le projet avec vos deux ou trois noms à minima. Vous pouvez l'appeler nom1-nom2-nom-de-votre-projet ou juste nom1-nom2 si vous n'êtes vraiment pas inspirés.
Tant qu'il y a vos noms tout ira bien ;)

Si vous n'êtes pas familier avec Maven, dans le fichier pom.xml à la racine du projet il y'a deux balises (artifactId et description) que vous devez changer pour nommer le projet comme vous le souhaitez.

## Documentation utile

On commence par un peu de culture pour ceux qui sont intéressés à en apprendre plus sur [Maven](https://maven.apache.org/what-is-maven.html)

#### SpringBoot MongoDB 

Méthodes de base déjà présentes dans vos repo qui implémentent l'interface __MongoRepository__:

```java 
  S save(S entity);      

  Optional<T> findById(ID primaryKey); 

  Iterable<T> findAll();               

  long count();                        

  void delete(T entity);               

  boolean existsById(ID primaryKey);   

```

[Tableau récapitulatif des mots clefs à utiliser en fonction des requêtes base que vous souhaitez faire](https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongodb.repositories.queries)

[Comment écrire un pipeline d'agrégation dans votre repo](https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongodb.repositories.queries.aggregation)


## License
[MIT](https://choosealicense.com/licenses/mit/)
