package org.sinhala.wordnet.wordnetDB.model;

public class MongoSinhalaWordPointer {
	
	MongoSinhalaPointerTyps pointerType;
	String synsetId;
	String wordId;
	
	public String SynsetId() {
		return synsetId;
	}
	public String WordId() {
		return wordId;
	}
	public MongoSinhalaPointerTyps PointerType() {
		return pointerType;
	}
	
	public void setSynsetId(String synsetId) {
		this.synsetId = synsetId;
	}
	public void setWordId(String wordId) {
		this.wordId = wordId;
	}
	public void setPointerType(MongoSinhalaPointerTyps pointerType) {
		this.pointerType = pointerType;
	}
	
	public MongoSinhalaWordPointer(String synsetId, String wordId, MongoSinhalaPointerTyps pointerType) {
		super();
		this.synsetId = synsetId;
		this.wordId = wordId;
		this.pointerType = pointerType;
	}
	
	@Override
	public String toString() {
		return "pointer [SynsetId=" + synsetId + ", WordId=" + wordId + ", PointerType=" + pointerType + "]";
	}


}
