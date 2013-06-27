package com.wordnetDB.model;

public class SinhalaWordPointer {
	
	SinhalaPointerTyps pointerType;
	String synsetId;
	String wordId;
	
	public String SynsetId() {
		return synsetId;
	}
	public String WordId() {
		return wordId;
	}
	public SinhalaPointerTyps PointerType() {
		return pointerType;
	}
	
	public void setSynsetId(String synsetId) {
		this.synsetId = synsetId;
	}
	public void setWordId(String wordId) {
		this.wordId = wordId;
	}
	public void setPointerType(SinhalaPointerTyps pointerType) {
		this.pointerType = pointerType;
	}
	
	public SinhalaWordPointer(String synsetId, String wordId, SinhalaPointerTyps pointerType) {
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
