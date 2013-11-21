package org.sinhala.wordnet.wordnetDB.model;

public class MongoSinhalaSencePointer {
	
	MongoSinhalaPointerTyps pointerType;
	Long synsetId;
	String pointedFile;
	
	
	public String getPointedFile() {
		return pointedFile;
	}
	public Long getSynsetId() {
		return synsetId;
	}
	
	public MongoSinhalaPointerTyps getPointerType() {
		return pointerType;
	}
	
	public void setSynsetId(Long synsetId) {
		this.synsetId = synsetId;
	}
	public void setPointedFile(String pointedFile) {
		this.pointedFile = pointedFile;
	}
	
	public void setPointerType(MongoSinhalaPointerTyps pointerType) {
		this.pointerType = pointerType;
	}
	
	public MongoSinhalaSencePointer(String pointedFile , Long holoSynsetID, MongoSinhalaPointerTyps pointerType) {
		super();
		this.synsetId = holoSynsetID;
		this.pointerType = pointerType;
		this.pointedFile = pointedFile;
	}
	public MongoSinhalaSencePointer(){
		super();
	}
	
	@Override
	public String toString() {
		return "pointer [SynsetId=" + synsetId + ", PointerType=" + pointerType + "]";
	}


}
