package org.sinhala.wordnet.wordnetDB.model;

public class MongoSinhalaPoinertTypeSemetric {
	public MongoSinhalaPointerTyps getSymetric(MongoSinhalaPointerTyps pType){
		
		if(pType.equals(MongoSinhalaPointerTyps.HYPERNYM) ){
			return MongoSinhalaPointerTyps.HYPONYM;
		}
		else if(pType.equals(MongoSinhalaPointerTyps.HYPONYM)){
			return MongoSinhalaPointerTyps.HYPERNYM;
		}
		else if(pType.equals(MongoSinhalaPointerTyps.MEMBER_MERONYM)){
			return MongoSinhalaPointerTyps.MEMBER_HOLONYM;
		}
		else if(pType.equals(MongoSinhalaPointerTyps.MEMBER_HOLONYM)){
			return MongoSinhalaPointerTyps.MEMBER_MERONYM;
		}
		else if(pType.equals(MongoSinhalaPointerTyps.SUBSTANCE_MERONYM)){
			return MongoSinhalaPointerTyps.SUBSTANCE_HOLONYM;
		}
		else if(pType.equals(MongoSinhalaPointerTyps.SUBSTANCE_HOLONYM)){
			return MongoSinhalaPointerTyps.SUBSTANCE_MERONYM;
		}
		else if(pType.equals(MongoSinhalaPointerTyps.PART_MERONYM)){
			return MongoSinhalaPointerTyps.PART_HOLONYM;
		}
		else if(pType.equals(MongoSinhalaPointerTyps.PART_HOLONYM)){
			return MongoSinhalaPointerTyps.PART_MERONYM;
		}
		else if(pType.equals(MongoSinhalaPointerTyps.SIMILAR_TO)){
			return MongoSinhalaPointerTyps.SIMILAR_TO;
		}
		else if(pType.equals(MongoSinhalaPointerTyps.ATTRIBUTE)){
			return MongoSinhalaPointerTyps.ATTRIBUTE;
		}
		else if(pType.equals(MongoSinhalaPointerTyps.VERB_GROUP)){
			return MongoSinhalaPointerTyps.VERB_GROUP;
		}
		else if(pType.equals(MongoSinhalaPointerTyps.CATEGORY)){
			return MongoSinhalaPointerTyps.CATEGORY_MEMBER;
		}
		else if(pType.equals(MongoSinhalaPointerTyps.CATEGORY_MEMBER)){
			return MongoSinhalaPointerTyps.CATEGORY;
		}
		else if(pType.equals(MongoSinhalaPointerTyps.REGION)){
			return MongoSinhalaPointerTyps.REGION_MEMBER;
		}
		else if(pType.equals(MongoSinhalaPointerTyps.REGION_MEMBER)){
			return MongoSinhalaPointerTyps.REGION;
		}
		else if(pType.equals(MongoSinhalaPointerTyps.USAGE)){
			return MongoSinhalaPointerTyps.USAGE_MEMBER;
		}
		else if(pType.equals(MongoSinhalaPointerTyps.USAGE_MEMBER)){
			return MongoSinhalaPointerTyps.USAGE;
		}
		else if(pType.equals(MongoSinhalaPointerTyps.DERIVATION)){
			return MongoSinhalaPointerTyps.DERIVATION;
		}
		else if(pType.equals(MongoSinhalaPointerTyps.INSTANCE_HYPERNYM)){
			return MongoSinhalaPointerTyps.INSTANCES_HYPONYM;
		}
		else if(pType.equals(MongoSinhalaPointerTyps.INSTANCES_HYPONYM)){
			return MongoSinhalaPointerTyps.INSTANCE_HYPERNYM;
		}
		else{
			return null;
		}
	}
}
