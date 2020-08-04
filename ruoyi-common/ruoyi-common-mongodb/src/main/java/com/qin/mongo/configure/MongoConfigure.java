package com.qin.mongo.configure;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfigure {

    @Autowired
    private MongoTemplate template;

    @Value("${mongodb.host}")
    private String host;
    @Value("${mongodb.port}")
    private int port;
    @Value("${mongodb.database}")
    private String database;

    @Bean
    public MongoPageHelper mongoPageHelper(){
        return new MongoPageHelper(template);
    }

    @Bean
    @ConditionalOnClass(MongoClient.class)
    public MongoTemplate mongoTemplate(MongoClient mongoClient){

        return new MongoTemplate(mongoClient, database);
    }

    @Bean
    public MongoClient mongoClient(){
        return new MongoClient(host, port);
//        return new MongoClient("192.168.0.161", 27017);
    }
}
