package org.sinhala.wordnet.wordnetDB.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;



import org.sinhala.wordnet.css.model.wordnet.NounSynset;
import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetSynset;
import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetWord;
import org.sinhala.wordnet.wordnetDB.config.SpringMongoConfig;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaNoun;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaPointerTyps;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaSencePointer;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaSynset;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaWord;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaWordPointer;

public class SynsetMongoDbHandler {
	
	
	
	
	public void addNounSynset(NounSynset nounSynset){
		
		
		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		SinhalaSynsetMongoSynsetConvertor ssmsc = new SinhalaSynsetMongoSynsetConvertor();
		MongoSinhalaNoun mongoNounsynset = ssmsc.converttoMongoNoun(nounSynset);
		
		
		
		
		/*MongoSinhalaWordPointer wordPointer1 = new MongoSinhalaWordPointer("123", "456", MongoSinhalaPointerTyps.MEMBER_HOLONYM);
		MongoSinhalaWordPointer wordPointer2 = new MongoSinhalaWordPointer("789", "741", MongoSinhalaPointerTyps.ANTONYM);
		List<MongoSinhalaWordPointer> wordPointerList = new ArrayList<MongoSinhalaWordPointer>();
		wordPointerList.add(wordPointer1);
		wordPointerList.add(wordPointer2);
		MongoSinhalaWord word1 = new MongoSinhalaWord("fother123456", "1", wordPointerList);
		MongoSinhalaWordPointer wordPointer3 = new MongoSinhalaWordPointer("abc", "def", MongoSinhalaPointerTyps.DERIVATION);
		MongoSinhalaWordPointer wordPointer4 = new MongoSinhalaWordPointer("asd", "hjk", MongoSinhalaPointerTyps.CAUSE);
		List<MongoSinhalaWordPointer> wordPointerList1 = new ArrayList<MongoSinhalaWordPointer>();
		wordPointerList1.add(wordPointer3);
		wordPointerList1.add(wordPointer4);
		MongoSinhalaWord word2 = new MongoSinhalaWord("පිය�?123", "2", wordPointerList1);
		List<MongoSinhalaWord> wordList = new ArrayList<MongoSinhalaWord>();
		wordList.add(word1);
		wordList.add(word2);
		List<MongoSinhalaSencePointer> sencePointerList = new ArrayList<MongoSinhalaSencePointer>();
		MongoSinhalaSencePointer sencePointer1 = new MongoSinhalaSencePointer("51c94199fd1d92fefeded261", MongoSinhalaPointerTyps.HYPERNYM);
		MongoSinhalaSencePointer sencePointer3 = new MongoSinhalaSencePointer("51c941d7fd1d13899258fae2", MongoSinhalaPointerTyps.HYPERNYM);
		
		MongoSinhalaSencePointer sencePointer2 = new MongoSinhalaSencePointer("789123", MongoSinhalaPointerTyps.ATTRIBUTE);
		sencePointerList.add(sencePointer1);
		sencePointerList.add(sencePointer2);
		sencePointerList.add(sencePointer3);
		
		*/// save
		mongoOperation.save(mongoNounsynset);
		
	
		System.out.println("saved");
		
	}
	
	public void findAll(){
		
		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		
		
		List<MongoSinhalaSynset> collection = mongoOperation.findAll(MongoSinhalaSynset.class);
        for (MongoSinhalaSynset s : collection) {
        	
        	 System.out.println(s);
             
         }
	}
	
public void findById(Long findId){
		
		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		
		
		Query searchSynsetQuery1 = new Query(Criteria.where("_id").is(findId));
		MongoSinhalaSynset foundSynset = mongoOperation.findOne(searchSynsetQuery1, MongoSinhalaSynset.class);
    	System.out.println(foundSynset);
	}

public MongoSinhalaSynset findBylemma(String lemma){
	
	@SuppressWarnings("resource")
	ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
	
	
	Query searchSynsetQuery1 = new Query(Criteria.where("words.lemma").is(lemma));
	MongoSinhalaSynset foundSynset = mongoOperation.findOne(searchSynsetQuery1, MongoSinhalaSynset.class);
	System.out.println(foundSynset);
	return foundSynset;
}

public void findRelatedSynsetById(String findId,MongoSinhalaPointerTyps relation){
	
	@SuppressWarnings("resource")
	ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
	
	
	Query searchSynsetQuery1 = new Query(Criteria.where("_id").is(findId));
	MongoSinhalaSynset foundSynset = mongoOperation.findOne(searchSynsetQuery1, MongoSinhalaSynset.class);
	List<MongoSinhalaSencePointer> sensePointers =  foundSynset.getSencePointers();
	for(int i=0;i<sensePointers.size();i++){
		MongoSinhalaPointerTyps pointerType=  sensePointers.get(i).getPointerType();
		if(pointerType.equals(relation)){
			findById(sensePointers.get(i).getSynsetId());
		}
	}
	System.out.println(foundSynset);
}
	
	
}
