package com.qin.mongo.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "mongodb")
public class MongoDbProperties {
    private String host;
    private int port;
    private String database;

}
