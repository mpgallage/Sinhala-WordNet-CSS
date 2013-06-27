package com.wordnetDB.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.wordnetDB.core.SynsetMongoDbHandler;


@Document(collection = "noun")
public class SinhalaVerb implements SinhalaSynset{

	@Id
	private String id;
	String eWNId;
	String SMDBId;
	List<SinhalaWord> words;
	List<SinhalaSencePointer> sencePointers;
	String gloss;
	
	
	public SinhalaVerb(String eWNId,List<SinhalaWord> words,List<SinhalaSencePointer> sencePointers,String gloss) {
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
	public String getEWNId() {
		// TODO Auto-generated method stub
		return eWNId;
	}

	@Override
	public String getSMDBId() {
		// TODO Auto-generated method stub
		return SMDBId;
	}

	@Override
	public List<SinhalaWord> getWords() {
		// TODO Auto-generated method stub
		return words;
	}

	@Override
	public List<SinhalaSencePointer> getSencePointers() {
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
	public void SetEWNId(String eWNId) {
		// TODO Auto-generated method stub
		this.eWNId = eWNId;
	}

	@Override
	public void SetSMDBId(String SMDBId) {
		// TODO Auto-generated method stub
		this.SMDBId = SMDBId;
	}

	@Override
	public void SetWords(List<SinhalaWord> words) {
		// TODO Auto-generated method stub
		this.words = words;
		
	}

	@Override
	public void SetSencePointers(List<SinhalaSencePointer> sencePointers) {
		// TODO Auto-generated method stub
		this.sencePointers = sencePointers;
	}

	@Override
	public void SetGloss(String gloss) {
		// TODO Auto-generated method stub
		this.gloss = gloss;
	}

	@Override
	public void getRelatedSynsets(SinhalaPointerTyps relation) {
		// TODO Auto-generated method stub
		SynsetMongoDbHandler synsetdb = new SynsetMongoDbHandler();
		synsetdb.findRelatedSynsetById(this.id, relation);
	}
}
