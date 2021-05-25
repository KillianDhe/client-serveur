package fr.iut.ArtisteManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@SpringBootApplication
public class ArtisteManagerApplication {

    static Logger logger = LoggerFactory.getLogger(ArtisteManagerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ArtisteManagerApplication.class, args);
        logger.info("Message=Application started");
    }

    /**
     * Surcharge du client MongoDB Spring pour ajouter la création automatique des indexes.
     */
    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDbFactory, MongoMappingContext context) {
        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
        context.setAutoIndexCreation(true);
        return new MongoTemplate(mongoDbFactory, converter);
    }

}
