package org.sinhala.wordnet.wordnetDB.model;

public class MongoSinhalaWordPointer {
	
	MongoSinhalaPointerTyps pointerType;
	String synsetIDasString;
	Long synsetId;
	String wordId;
	String pointedFile;
	
	
	public String getPointedFile() {
		return pointedFile;
	}
	public String getSynsetIDasString() {
		return synsetIDasString;
	}
	public void setSynsetIDasString(String synsetIDasString) {
		this.synsetIDasString = synsetIDasString;
	}
	public Long getSynsetId() {
		return synsetId;
	}
	public String getWordId() {
		return wordId;
	}
	public MongoSinhalaPointerTyps getPointerType() {
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
	
	public void setPointedFole(String pointedFile) {
		this.pointedFile = pointedFile;
	}
	
	public MongoSinhalaWordPointer(String pointedFile,Long synsetId, String wordId, MongoSinhalaPointerTyps pointerType) {
		super();
		this.synsetId = synsetId;
		this.wordId = wordId;
		this.pointerType = pointerType;
		this.pointedFile = pointedFile;
	}
	
	public MongoSinhalaWordPointer() {
		super();
	}
	
	public MongoSinhalaWordPointer(String pointedFile,String synsetIDasString, String wordId, MongoSinhalaPointerTyps pointerType) {
		super();
		this.synsetIDasString = synsetIDasString;
		this.wordId = wordId;
		this.pointerType = pointerType;
		this.pointedFile = pointedFile;
	}
	
	@Override
	public String toString() {
		return "pointer [PointedFile =  "+pointedFile+"SynsetId=" + synsetId + ", WordId=" + wordId + ", PointerType=" + pointerType + "]";
	}


}
