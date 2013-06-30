package org.sinhala.wordnet.wordnetDB.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "word")
public class MongoSinhalaWord {

	@Id
	private String id;
	String lemma;
	String wordId;
	MongoSinhalaWordPointer wordPointer;
	List<MongoSinhalaWordPointer> wordPointerList;
	
	
	
	public String getId() {
		return id;
	}
	public String getLemma() {
		return lemma;
	}
	public String getWordId() {
		return wordId;
	}
	public List<MongoSinhalaWordPointer> getWordPointerList() {
		return wordPointerList;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void SetLemma(String lemma) {
		this.lemma = lemma;
	}
	public void SetWordId(String wordId) {
		this.wordId = wordId;
	}
	public void SetWordPointerList(List<MongoSinhalaWordPointer> wordPointerList) {
		this.wordPointerList = wordPointerList;
	}
	
	
	public MongoSinhalaWord(String lemma, String wordId,List<MongoSinhalaWordPointer> wordPointerList) {
		super();
		this.lemma = lemma;
		this.wordId = wordId;
		this.wordPointerList = wordPointerList;
	}

	@Override
	public String toString() {
		return "Word [id=" + id + ", lemma=" + lemma + ", wordId=" + wordId + ", wordPointerList="+wordPointerList+"]";
	}

}
