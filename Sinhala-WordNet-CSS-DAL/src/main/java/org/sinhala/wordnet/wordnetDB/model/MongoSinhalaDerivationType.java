package org.sinhala.wordnet.wordnetDB.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "derivationType")
public class MongoSinhalaDerivationType implements MongoSinhalaSynset{

	@Id
	private String id;
	List<MongoSinhalaWord> words;
	String gloss;
	
	
	public MongoSinhalaDerivationType(List<MongoSinhalaWord> words,String gloss) {
		super();
		this.words = words;
		this.gloss = gloss;
		
	}

	@Override
	public String toString() {
		return "Synset [id=" + id + ", words="+words+", gloss="+gloss+"]";
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
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
	
	public String getWordsAsString() {
		// TODO Auto-generated method stub
		String out = "";
		for(MongoSinhalaWord w : this.getWords()){
			out += w.getLemma() + ", ";
		}
		
		return out;
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
	public String getUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserName(String userName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean getEvaluated() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void SetEvaluated() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDate(Date date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setComment(String comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRating(String rating) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SetEvaluatedBy(String evaluatedBy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getRating() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getComment() {
		// TODO Auto-generated method stub
		return null;
	}

}
