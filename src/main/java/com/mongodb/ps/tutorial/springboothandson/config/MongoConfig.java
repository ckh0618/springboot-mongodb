package com.mongodb.ps.tutorial.springboothandson.config;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ReadPreference;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import java.util.concurrent.TimeUnit;

@Configuration
public class MongoConfig implements InitializingBean {

    @Autowired
    private Environment env;


    @Autowired
    @Lazy
    private MappingMongoConverter mappingMongoConverter;

    @Override
    public void afterPropertiesSet() throws Exception {
        mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
    }

    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

//    @Bean
//    public MongoClient mongoClient() {
//        ConnectionString connectionString = new ConnectionString(
//                env.getProperty("spring.data.mongodb.uri")
//        );
//
//        MongoClientSettings mongoClientSettings =
//                MongoClientSettings.builder()
//                        .readPreference(ReadPreference.nearest())
//                        .applyConnectionString(connectionString)
//                        .applyToConnectionPoolSettings(builder ->
//                                builder.minSize(20).maxSize(20))
//                        .applyToSocketSettings(builder ->
//                                builder.connectTimeout(10, TimeUnit.SECONDS)
//                                        .readTimeout(1000,TimeUnit.MILLISECONDS))
//                        .build();
//        return MongoClients.create(mongoClientSettings);
//    }
}
