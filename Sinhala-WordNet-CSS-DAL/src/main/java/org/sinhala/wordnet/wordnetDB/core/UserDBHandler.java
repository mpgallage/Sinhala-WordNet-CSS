package org.sinhala.wordnet.wordnetDB.core;

import java.util.List;

import org.sinhala.wordnet.wordnetDB.config.SpringMongoConfig;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaNoun;
import org.sinhala.wordnet.wordnetDB.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class UserDBHandler {
	
	public void addUser(User user){
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		mongoOperation.save(user);
		
		((AbstractApplicationContext) ctx).close();
		System.out.println(" user saved");
	}
	
	public void editUser(User user){
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
	
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(user.getUsername()));
		Update update = new Update();
		System.out.println(user.getRole());
		System.out.println(user.getUsername());
		update.set("role", user.getRole());

		mongoOperation.findAndModify(query, update,
				new FindAndModifyOptions().returnNew(true), User.class);
		((AbstractApplicationContext) ctx).close();
	}

	public List<User> getUsers(String currentUsername){
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		Query searchSynsetQuery = new Query(Criteria.where("username").ne(currentUsername));
		List<User> users = mongoOperation.find(
				searchSynsetQuery, User.class);
		((AbstractApplicationContext) ctx).close();
		return users;
	}
	
	public User getUserByUsername(String username){
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		Query searchSynsetQuery = new Query(Criteria.where("username").is(username));
		User user = mongoOperation.findOne(
				searchSynsetQuery, User.class);
		((AbstractApplicationContext) ctx).close();
		return user;
	}
}
