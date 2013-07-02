package org.sinhala.wordnet.wordnetDB.model;

import java.util.List;

import org.sinhala.wordnet.wordnetDB.core.SynsetMongoDbHandler;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "verb")
public class MongoSinhalaVerb implements MongoSinhalaSynset{

	@Id
	private String id;
	Long eWNId;
	String SMDBId;
	List<MongoSinhalaWord> words;
	List<MongoSinhalaSencePointer> sencePointers;
	String gloss;
	
	
	public MongoSinhalaVerb(Long eWNId,List<MongoSinhalaWord> words,List<MongoSinhalaSencePointer> sencePointers,String gloss) {
		super();
		this.eWNId = eWNId;
		this.words = words;
		this.sencePointers = sencePointers;
		this.gloss = gloss;
		
	}

	@Override
	public String toString() {
		return "Synset [id=" + id + ", EWNId=" + eWNId + ", SMDBId=" + SMDBId + ", words="+words+", sencePointerList="+sencePointers+", gloss="+gloss+"]";
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public Long getEWNId() {
		// TODO Auto-generated method stub
		return eWNId;
	}

	@Override
	public String getSMDBId() {
		// TODO Auto-generated method stub
		return SMDBId;
	}

	@Override
	public List<MongoSinhalaWord> getWords() {
		// TODO Auto-generated method stub
		return words;
	}

	@Override
	public List<MongoSinhalaSencePointer> getSencePointers() {
		// TODO Auto-generated method stub
		return sencePointers;
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
	public void SetEWNId(Long eWNId) {
		// TODO Auto-generated method stub
		this.eWNId = eWNId;
	}

	@Override
	public void SetSMDBId(String SMDBId) {
		// TODO Auto-generated method stub
		this.SMDBId = SMDBId;
	}

	@Override
	public void SetWords(List<MongoSinhalaWord> words) {
		// TODO Auto-generated method stub
		this.words = words;
		
	}

	@Override
	public void SetSencePointers(List<MongoSinhalaSencePointer> sencePointers) {
		// TODO Auto-generated method stub
		this.sencePointers = sencePointers;
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

	
}
