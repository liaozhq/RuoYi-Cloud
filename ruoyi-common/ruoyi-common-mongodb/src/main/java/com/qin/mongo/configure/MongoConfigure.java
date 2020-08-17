package com.qin.mongo.configure;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoClients;
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
    @Value("${mongodb.connections_per_host}")
    private Integer connections_per_host;
    @Value("${mongodb.min_connections_per_host}")
    private Integer min_connections_per_host;

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
        MongoClientOptions.Builder build = new MongoClientOptions.Builder();
        if(connections_per_host == 0) {
            build.connectionsPerHost(1000);   //与目标数据库能够建立的最大connection数量为50
        }else{
            build.connectionsPerHost(connections_per_host);
        }

        if(min_connections_per_host == 0) {
            build.minConnectionsPerHost(200);
        }else{
            build.minConnectionsPerHost(min_connections_per_host);
        }

//        build.autoConnectRetry(true);
        build.threadsAllowedToBlockForConnectionMultiplier(50); //如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待
        /*
         * 一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间为2分钟
         * 这里比较危险，如果超过maxWaitTime都没有获取到这个连接的话，该线程就会抛出Exception
         * 故这里设置的maxWaitTime应该足够大，以免由于排队线程过多造成的数据库访问失败
         */
        build.maxWaitTime(1000*60*2);
        build.connectTimeout(1000*60*1);    //与数据库建立连接的timeout设置为1分钟
        MongoClientOptions myOptions = build.build();
        return new MongoClient(host, myOptions);
    }
}
