package com.wordnetDB.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "word")
public class SinhalaWord {

	@Id
	private String id;
	String lemma;
	String wordId;
	SinhalaWordPointer wordPointer;
	List<SinhalaWordPointer> wordPointerList;
	
	
	
	public String getId() {
		return id;
	}
	public String getLemma() {
		return lemma;
	}
	public String getWordId() {
		return wordId;
	}
	public List<SinhalaWordPointer> getWordPointerList() {
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
	public void SetWordPointerList(List<SinhalaWordPointer> wordPointerList) {
		this.wordPointerList = wordPointerList;
	}
	
	
	public SinhalaWord(String lemma, String wordId,List<SinhalaWordPointer> wordPointerList) {
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
