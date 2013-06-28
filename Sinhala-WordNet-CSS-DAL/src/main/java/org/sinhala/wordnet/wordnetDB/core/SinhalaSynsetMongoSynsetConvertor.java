package org.sinhala.wordnet.wordnetDB.core;

import java.util.ArrayList;
import java.util.List;

import org.sinhala.wordnet.css.model.wordnet.NounSynset;
import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetSynset;
import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetWord;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaNoun;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaPointerTyps;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaSencePointer;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaWord;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaWordPointer;

public class SinhalaSynsetMongoSynsetConvertor {
	
	public MongoSinhalaNoun converttoMongoNoun(NounSynset nounSynset){
		
		SinhalaWordNetWord sinhalaWordNetword = null;
		List<MongoSinhalaWordPointer> wordPointerList = new ArrayList<MongoSinhalaWordPointer>();
		List<MongoSinhalaWord> wordList = new ArrayList<MongoSinhalaWord>();
		List<MongoSinhalaSencePointer> sencePointerList = new ArrayList<MongoSinhalaSencePointer>();
		
		
		
		Long ewnid = nounSynset.getOffset();
		List<SinhalaWordNetWord>  words  =  nounSynset.getWords();
		
		
		for(int i=0;i<words.size();i++){
			String lemma= words.get(i).getLemma();
			sinhalaWordNetword = words.get(i).getAntonym();
			if(sinhalaWordNetword.getId()!=null){
				MongoSinhalaWordPointer wordPointer1 = new MongoSinhalaWordPointer(sinhalaWordNetword.getId(), sinhalaWordNetword.getId(), MongoSinhalaPointerTyps.ANTONYM);
				wordPointerList.add(wordPointer1);
			}
			sinhalaWordNetword=null;
			 sinhalaWordNetword = words.get(i).getDerivationType();
			if(sinhalaWordNetword.getId()!=null){
				MongoSinhalaWordPointer wordPointer1 = new MongoSinhalaWordPointer(sinhalaWordNetword.getId(), sinhalaWordNetword.getId(), MongoSinhalaPointerTyps.DERIVATION_TYPE);
				wordPointerList.add(wordPointer1);
			}
			
			sinhalaWordNetword=null;
			 sinhalaWordNetword = words.get(i).getOrigin();
			if(sinhalaWordNetword.getId()!=null){
				MongoSinhalaWordPointer wordPointer1 = new MongoSinhalaWordPointer(sinhalaWordNetword.getId(), sinhalaWordNetword.getId(), MongoSinhalaPointerTyps.ORIGIN);
				wordPointerList.add(wordPointer1);
			}
			sinhalaWordNetword=null;
			 sinhalaWordNetword = words.get(i).getRoot();
			if(sinhalaWordNetword.getId()!=null){
				MongoSinhalaWordPointer wordPointer1 = new MongoSinhalaWordPointer(sinhalaWordNetword.getId(), sinhalaWordNetword.getId(), MongoSinhalaPointerTyps.ROOT);
				wordPointerList.add(wordPointer1);
			}
			
			sinhalaWordNetword=null;
			 sinhalaWordNetword = words.get(i).getUsage();
			if(sinhalaWordNetword.getId()!=null){
				MongoSinhalaWordPointer wordPointer1 = new MongoSinhalaWordPointer(sinhalaWordNetword.getId(), sinhalaWordNetword.getId(), MongoSinhalaPointerTyps.USAGE);
				wordPointerList.add(wordPointer1);
			}
			sinhalaWordNetword=null;
			MongoSinhalaWord word1 = new MongoSinhalaWord(lemma, words.get(i).getId(), wordPointerList);
			wordList.add(word1);
			
		}
		
		List<SinhalaWordNetSynset> holoSynsets =  nounSynset.getHolonyms();
		
		for(int i=0;i<holoSynsets.size();i++){
			Long holoSynsetID = holoSynsets.get(i).getOffset();
			MongoSinhalaSencePointer sencePointer = new MongoSinhalaSencePointer(holoSynsetID, MongoSinhalaPointerTyps.PART_HOLONYM);
			sencePointerList.add(sencePointer);
		}
		
		List<SinhalaWordNetSynset> hypernymSynsets =  nounSynset.getHypernyms();
		
		for(int i=0;i<hypernymSynsets.size();i++){
			Long hypernymSynsetID = hypernymSynsets.get(i).getOffset();
			MongoSinhalaSencePointer sencePointer = new MongoSinhalaSencePointer(hypernymSynsetID, MongoSinhalaPointerTyps.HYPERNYM);
			sencePointerList.add(sencePointer);
		}
		
		List<SinhalaWordNetSynset> hyponymSynsets =  nounSynset.getHyponyms();
		
		for(int i=0;i<hyponymSynsets.size();i++){
			Long hyponymSynsetID = hyponymSynsets.get(i).getOffset();
			MongoSinhalaSencePointer sencePointer = new MongoSinhalaSencePointer(hyponymSynsetID, MongoSinhalaPointerTyps.HYPONYM);
			sencePointerList.add(sencePointer);
		}
		
		List<SinhalaWordNetSynset> attributeSynsets =  nounSynset.getAttributes();
		
		for(int i=0;i<attributeSynsets.size();i++){
			Long attributeSynsetID = attributeSynsets.get(i).getOffset();
			MongoSinhalaSencePointer sencePointer = new MongoSinhalaSencePointer(attributeSynsetID, MongoSinhalaPointerTyps.ATTRIBUTE);
			sencePointerList.add(sencePointer);
		}
		
		List<SinhalaWordNetSynset> meronymSynsets =  nounSynset.getMeronyms();
		
		for(int i=0;i<meronymSynsets.size();i++){
			Long meronymSynsetID = meronymSynsets.get(i).getOffset();
			MongoSinhalaSencePointer sencePointer = new MongoSinhalaSencePointer(meronymSynsetID, MongoSinhalaPointerTyps.PART_MERONYM);
			sencePointerList.add(sencePointer);
		}
		
		
		MongoSinhalaNoun mongoNounsynset = new MongoSinhalaNoun(ewnid,wordList,sencePointerList,nounSynset.getDefinition()+"|"+nounSynset.getExample());
		return mongoNounsynset;
		
	}

}
