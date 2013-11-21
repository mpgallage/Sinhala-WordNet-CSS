package org.sinhala.wordnet.wordnetDB.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
public class SpringMongoConfig extends AbstractMongoConfiguration {
Mongo mongo;
	@Override
	public String getDatabaseName() {
		return "WordNet";
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		mongo =new MongoClient("127.0.0.1");
		return mongo;
	}
	public void closs(){
		mongo.close();
	}
}