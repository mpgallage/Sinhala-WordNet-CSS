package org.sinhala.wordnet.wordnetDB.model;

import java.util.Date;
import java.util.List;

import org.sinhala.wordnet.wordnetDB.core.SynsetMongoDbHandler;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "root")
public class MongoSinhalaRoot implements MongoSinhalaSynset {
	
	@Id
	private String id;
	List<MongoSinhalaWord> words;
	String gloss;
	String userName;
	Boolean evaluated = false;
	Date date;
	
	public MongoSinhalaRoot(List<MongoSinhalaWord> words,String gloss,String userName) {
		super();
		this.words = words;
		this.gloss = gloss;
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Synset [id=" + id + ", userName="+userName+", words="+words+", gloss="+gloss+"]";
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return userName;
	}
	@Override
	public List<MongoSinhalaWord> getWords() {
		// TODO Auto-generated method stub
		return words;
	}

	@Override
	public String getGloss() {
		// TODO Auto-generated method stub
		return gloss;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}


	@Override
	public void SetWords(List<MongoSinhalaWord> words) {
		// TODO Auto-generated method stub
		this.words = words;
		
	}

	@Override
	public void SetGloss(String gloss) {
		// TODO Auto-generated method stub
		this.gloss = gloss;
	}

	@Override
	public void getRelatedSynsets(MongoSinhalaPointerTyps relation) {
		// TODO Auto-generated method stub
		SynsetMongoDbHandler synsetdb = new SynsetMongoDbHandler();
		synsetdb.findRelatedSynsetById(this.id, relation);
	}

	@Override
	public List<MongoSinhalaSencePointer> getSencePointers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void SetSencePointers(List<MongoSinhalaSencePointer> sencePointers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getEWNId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSMDBId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void SetEWNId(Long eWNId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SetSMDBId(String SMDBId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean getEvaluated() {
		// TODO Auto-generated method stub
		return evaluated;
	}

	@Override
	public void SetEvaluated() {
		// TODO Auto-generated method stub
		evaluated = true; 
		
	}

	@Override
	public Date getDate() {
		// TODO Auto-generated method stub
		return date;
	}

	@Override
	public void setDate(Date date) {
		// TODO Auto-generated method stub
		this.date = date;
		
	}

}
