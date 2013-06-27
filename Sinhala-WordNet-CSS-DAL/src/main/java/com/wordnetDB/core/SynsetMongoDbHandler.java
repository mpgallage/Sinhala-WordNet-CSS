package com.wordnetDB.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.wordnetDB.config.SpringMongoConfig;
import com.wordnetDB.model.SinhalaNoun;
import com.wordnetDB.model.SinhalaPointerTyps;
import com.wordnetDB.model.SinhalaSencePointer;
import com.wordnetDB.model.SinhalaSynset;
import com.wordnetDB.model.SinhalaWord;
import com.wordnetDB.model.SinhalaWordPointer;

public class SynsetMongoDbHandler {
	
	
	
	
	public void add(){
		
		
		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		
		SinhalaWordPointer wordPointer1 = new SinhalaWordPointer("123", "456", SinhalaPointerTyps.MEMBER_HOLONYM);
		SinhalaWordPointer wordPointer2 = new SinhalaWordPointer("789", "741", SinhalaPointerTyps.ANTONYM);
		List<SinhalaWordPointer> wordPointerList = new ArrayList<SinhalaWordPointer>();
		wordPointerList.add(wordPointer1);
		wordPointerList.add(wordPointer2);
		SinhalaWord word1 = new SinhalaWord("fother123456", "1", wordPointerList);
		SinhalaWordPointer wordPointer3 = new SinhalaWordPointer("abc", "def", SinhalaPointerTyps.DERIVATION);
		SinhalaWordPointer wordPointer4 = new SinhalaWordPointer("asd", "hjk", SinhalaPointerTyps.CAUSE);
		List<SinhalaWordPointer> wordPointerList1 = new ArrayList<SinhalaWordPointer>();
		wordPointerList1.add(wordPointer3);
		wordPointerList1.add(wordPointer4);
		SinhalaWord word2 = new SinhalaWord("පිය�?123", "2", wordPointerList1);
		List<SinhalaWord> wordList = new ArrayList<SinhalaWord>();
		wordList.add(word1);
		wordList.add(word2);
		List<SinhalaSencePointer> sencePointerList = new ArrayList<SinhalaSencePointer>();
		SinhalaSencePointer sencePointer1 = new SinhalaSencePointer("51c94199fd1d92fefeded261", SinhalaPointerTyps.HYPERNYM);
		SinhalaSencePointer sencePointer3 = new SinhalaSencePointer("51c941d7fd1d13899258fae2", SinhalaPointerTyps.HYPERNYM);
		
		SinhalaSencePointer sencePointer2 = new SinhalaSencePointer("789123", SinhalaPointerTyps.ATTRIBUTE);
		sencePointerList.add(sencePointer1);
		sencePointerList.add(sencePointer2);
		sencePointerList.add(sencePointer3);
		
		SinhalaNoun nounsynset = new SinhalaNoun("123456789ghi",wordList,sencePointerList,"ත�?ත්තට සම�?න");
		// save
		mongoOperation.save(nounsynset);
		
	
		System.out.println("saved");
		
	}
	
	public void findAll(){
		
		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		
		
		List<SinhalaSynset> collection = mongoOperation.findAll(SinhalaSynset.class);
        for (SinhalaSynset s : collection) {
        	
        	 System.out.println(s);
             
         }
	}
	
public void findById(String findId){
		
		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		
		
		Query searchSynsetQuery1 = new Query(Criteria.where("_id").is(findId));
		SinhalaSynset foundSynset = mongoOperation.findOne(searchSynsetQuery1, SinhalaSynset.class);
    	System.out.println(foundSynset);
	}

public SinhalaSynset findBylemma(String lemma){
	
	@SuppressWarnings("resource")
	ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
	
	
	Query searchSynsetQuery1 = new Query(Criteria.where("words.lemma").is(lemma));
	SinhalaSynset foundSynset = mongoOperation.findOne(searchSynsetQuery1, SinhalaSynset.class);
	System.out.println(foundSynset);
	return foundSynset;
}

public void findRelatedSynsetById(String findId,SinhalaPointerTyps relation){
	
	@SuppressWarnings("resource")
	ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
	
	
	Query searchSynsetQuery1 = new Query(Criteria.where("_id").is(findId));
	SinhalaSynset foundSynset = mongoOperation.findOne(searchSynsetQuery1, SinhalaSynset.class);
	List<SinhalaSencePointer> sensePointers =  foundSynset.getSencePointers();
	for(int i=0;i<sensePointers.size();i++){
		SinhalaPointerTyps pointerType=  sensePointers.get(i).getPointerType();
		if(pointerType.equals(relation)){
			findById(sensePointers.get(i).getSynsetId());
		}
	}
	System.out.println(foundSynset);
}
	
	
}
