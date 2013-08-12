package org.sinhala.wordnet.wordnetDB.model;

public class MongoSinhalaSencePointer {
	
	MongoSinhalaPointerTyps pointerType;
	Long synsetId;
	
	public Long getSynsetId() {
		return synsetId;
	}
	
	public MongoSinhalaPointerTyps getPointerType() {
		return pointerType;
	}
	
	public void setSynsetId(Long synsetId) {
		this.synsetId = synsetId;
	}
	
	public void setPointerType(MongoSinhalaPointerTyps pointerType) {
		this.pointerType = pointerType;
	}
	
	public MongoSinhalaSencePointer(Long holoSynsetID, MongoSinhalaPointerTyps pointerType) {
		super();
		this.synsetId = holoSynsetID;
		
		this.pointerType = pointerType;
	}
	public MongoSinhalaSencePointer(){
		super();
	}
	
	@Override
	public String toString() {
		return "pointer [SynsetId=" + synsetId + ", PointerType=" + pointerType + "]";
	}


}
