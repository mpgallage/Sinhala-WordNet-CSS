package org.sinhala.wordnet.wordnetDB.model;

public class MongoSinhalaWordPointer {
	
	MongoSinhalaPointerTyps pointerType;
	Long synsetId;
	String wordId;
	
	public Long SynsetId() {
		return synsetId;
	}
	public String WordId() {
		return wordId;
	}
	public MongoSinhalaPointerTyps PointerType() {
		return pointerType;
	}
	
	public void setSynsetId(Long synsetId) {
		this.synsetId = synsetId;
	}
	public void setWordId(String wordId) {
		this.wordId = wordId;
	}
	public void setPointerType(MongoSinhalaPointerTyps pointerType) {
		this.pointerType = pointerType;
	}
	
	public MongoSinhalaWordPointer(Long synsetId, String wordId, MongoSinhalaPointerTyps pointerType) {
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
