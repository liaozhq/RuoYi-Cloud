package com.qin.mongo.configure;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@EnableConfigurationProperties({MongoDbProperties.class})
public class MongoConfigure {

    @Autowired
    private MongoTemplate template;

    @Autowired
    private MongoDbProperties mongoDbProperties;

    @Bean
    public MongoPageHelper mongoPageHelper(){
        return new MongoPageHelper(template);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient){

        return new MongoTemplate(mongoClient, mongoDbProperties.getDatabase());
    }

    @Bean
    public MongoClient mongoClient(){
        return new MongoClient(mongoDbProperties.getHost(), mongoDbProperties.getPort());
    }
}
