package org.sinhala.wordnet.wordnetDB.model;

import java.util.Date;
import java.util.List;



//@Document(collection = "synset")

public interface MongoSinhalaSynset{
	
	
	
	public String getId();
	public Long getEWNId();
	public String getUserName();
	public Long getSMDBId();
	public List<MongoSinhalaWord> getWords();
	public List<MongoSinhalaSencePointer> getSencePointers();
	public String getGloss();
	public Boolean getEvaluated();
	public Date getDate();
	public String getWordsAsString();
	
	public void setId(String id);
	public void SetEWNId(Long eWNId);
	public void setUserName(String userName);
	public void SetSMDBId(Long SMDBId);
	public void SetWords(List<MongoSinhalaWord> words);
	public void SetSencePointers(List<MongoSinhalaSencePointer> sencePointers);
	public void SetGloss(String gloss);
	public void SetEvaluated();
	public void setDate(Date date);
	
	
	
	

	
	public String toString();
	public void setComment(String comment);
	public void setRating(String rating);
	public void SetEvaluatedBy(String evaluatedBy);
	public String getRating();
	public String getComment();

}
