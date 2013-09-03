package org.sinhala.wordnet.wordnetDB.model;

import java.util.List;



//@Document(collection = "synset")

public interface MongoSinhalaSynset{
	
	
	
	public String getId();
	public Long getEWNId();
	public String getUserName();
	public String getSMDBId();
	public List<MongoSinhalaWord> getWords();
	public List<MongoSinhalaSencePointer> getSencePointers();
	public String getGloss();
	
	public void setId(String id);
	public void SetEWNId(Long eWNId);
	public void setUserName(String userName);
	public void SetSMDBId(String SMDBId);
	public void SetWords(List<MongoSinhalaWord> words);
	public void SetSencePointers(List<MongoSinhalaSencePointer> sencePointers);
	public void SetGloss(String gloss);
	public void getRelatedSynsets(MongoSinhalaPointerTyps relation);
	
	
	
	

	
	public String toString();

}
