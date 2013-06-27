package com.wordnetDB.model;

import java.util.List;



//@Document(collection = "synset")

public interface SinhalaSynset{
	
	
	
	public String getId();
	public String getEWNId();
	public String getSMDBId();
	public List<SinhalaWord> getWords();
	public List<SinhalaSencePointer> getSencePointers();
	public String getGloss();
	
	public void setId(String id);
	public void SetEWNId(String eWNId);
	public void SetSMDBId(String SMDBId);
	public void SetWords(List<SinhalaWord> words);
	public void SetSencePointers(List<SinhalaSencePointer> sencePointers);
	public void SetGloss(String gloss);
	public void getRelatedSynsets(SinhalaPointerTyps relation);
	
	
	
	

	
	public String toString();

}
