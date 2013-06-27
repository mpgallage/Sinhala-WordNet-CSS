package com.wordnetDB.model;

public class SinhalaSencePointer {
	
	SinhalaPointerTyps pointerType;
	String synsetId;
	
	public String getSynsetId() {
		return synsetId;
	}
	
	public SinhalaPointerTyps getPointerType() {
		return pointerType;
	}
	
	public void setSynsetId(String synsetId) {
		this.synsetId = synsetId;
	}
	
	public void setPointerType(SinhalaPointerTyps pointerType) {
		this.pointerType = pointerType;
	}
	
	public SinhalaSencePointer(String synsetId, SinhalaPointerTyps pointerType) {
		super();
		this.synsetId = synsetId;
		
		this.pointerType = pointerType;
	}
	
	@Override
	public String toString() {
		return "pointer [SynsetId=" + synsetId + ", PointerType=" + pointerType + "]";
	}


}
