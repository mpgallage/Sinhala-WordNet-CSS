package org.sinhala.wordnet.wordnetDB.model;

public class MongoSinhalaPoinertTypeSemetric {
	public MongoSinhalaPointerTyps getSymetric(MongoSinhalaPointerTyps pType){
		
		if(pType.equals(MongoSinhalaPointerTyps.HYPERNYM) ){
			return MongoSinhalaPointerTyps.HYPONYM;
		}
		else{
			return null;
		}
	}
}
