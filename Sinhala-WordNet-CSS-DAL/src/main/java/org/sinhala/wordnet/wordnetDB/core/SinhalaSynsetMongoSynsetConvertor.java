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
		//List<MongoSinhalaWordPointer> wordPointerList = new ArrayList<MongoSinhalaWordPointer>();
		List<MongoSinhalaWord> wordList = new ArrayList<MongoSinhalaWord>();
		List<MongoSinhalaSencePointer> sencePointerList = new ArrayList<MongoSinhalaSencePointer>();
		
		//hjhjb
		long con = 123456;
		Long ewnid = nounSynset.getOffset();
		List<SinhalaWordNetWord>  words  =  nounSynset.getWords();
		
		
		for(int i=0;i<words.size();i++){
			List<MongoSinhalaWordPointer> wordPointerList = new ArrayList<MongoSinhalaWordPointer>();
			String lemma= words.get(i).getLemma();
			sinhalaWordNetword=null;
			//try{
			sinhalaWordNetword = words.get(i).getAntonym();
			System.out.println("------"+sinhalaWordNetword);
			if(sinhalaWordNetword.getLemma()!=""){
				
				MongoSinhalaWordPointer wordPointer1 = new MongoSinhalaWordPointer(con,sinhalaWordNetword.getLemma(), MongoSinhalaPointerTyps.ANTONYM);
				wordPointerList.add(wordPointer1);
				System.out.println(sinhalaWordNetword+"anto"+wordPointer1);
			}
			//}
			//catch(Exception e){
			//	System.out.println(e );
			//}
			sinhalaWordNetword=null;
			try{
			 sinhalaWordNetword = words.get(i).getDerivationType();//words.get(i).getDerivationType();
			 System.out.println(sinhalaWordNetword+"devi");
			if(sinhalaWordNetword!=null){
				MongoSinhalaWordPointer wordPointer2 = new MongoSinhalaWordPointer(con, sinhalaWordNetword.getId(), MongoSinhalaPointerTyps.DERIVATION_TYPE);
				wordPointerList.add(wordPointer2);
				System.out.println("devi"+wordPointer2);
			}
			}
			catch(Exception e){
				System.out.println(e);
			}
			sinhalaWordNetword=null;
			try{
			 sinhalaWordNetword = words.get(i).getOrigin();
			if(sinhalaWordNetword.getId()!=null){
				MongoSinhalaWordPointer wordPointer3 = new MongoSinhalaWordPointer(con, sinhalaWordNetword.getId(), MongoSinhalaPointerTyps.ORIGIN);
				wordPointerList.add(wordPointer3);
			}
			}
			catch(Exception e){
				System.out.println(e);
			}
			sinhalaWordNetword=null;
			try{
			 sinhalaWordNetword = words.get(i).getRoot();
			if(sinhalaWordNetword.getId()!=null){
				MongoSinhalaWordPointer wordPointer4 = new MongoSinhalaWordPointer(con, sinhalaWordNetword.getId(), MongoSinhalaPointerTyps.ROOT);
				wordPointerList.add(wordPointer4);
			}
			}
			catch(Exception e){
				System.out.println(e);
			}
			sinhalaWordNetword=null;
			try{
			 sinhalaWordNetword = words.get(i).getUsage();
			if(sinhalaWordNetword.getId()!=null){
				MongoSinhalaWordPointer wordPointer5 = new MongoSinhalaWordPointer(con, sinhalaWordNetword.getId(), MongoSinhalaPointerTyps.USAGE);
				wordPointerList.add(wordPointer5);
			}
			}
			catch(Exception e){
				System.out.println(e);
			}
			sinhalaWordNetword=null;
			System.out.println("word ponter list"+1+"-"+wordPointerList);
			MongoSinhalaWord word1 = new MongoSinhalaWord(lemma, words.get(i).getId(), wordPointerList);
			System.out.println("word"+1+"-"+word1);
			wordList.add(word1);
			//wordPointerList=null;
			
			
		}
		
		try{
		List<SinhalaWordNetSynset> holoSynsets = null; //nounSynset.getHolonyms();
		System.out.println("holo"+holoSynsets);
		for(int i=0;i<holoSynsets.size();i++){
			Long holoSynsetID = holoSynsets.get(i).getOffset();
			MongoSinhalaSencePointer sencePointer = new MongoSinhalaSencePointer(holoSynsetID, MongoSinhalaPointerTyps.PART_HOLONYM);
			sencePointerList.add(sencePointer);
		}
		}
		catch(Exception e){
			System.out.println("ee"+e);
			
		}
		
		try{
		List<SinhalaWordNetSynset> hypernymSynsets = null; //nounSynset.getHypernyms();
		System.out.println("hyper"+hypernymSynsets);
		for(int i=0;i<hypernymSynsets.size();i++){
			Long hypernymSynsetID = hypernymSynsets.get(i).getOffset();
			MongoSinhalaSencePointer sencePointer = new MongoSinhalaSencePointer(hypernymSynsetID, MongoSinhalaPointerTyps.HYPERNYM);
			sencePointerList.add(sencePointer);
		}
		}
		catch(Exception e){
			System.out.println("ee"+e);
			
		}
		
		try{
		List<SinhalaWordNetSynset> hyponymSynsets = null;//nounSynset.getHyponyms();
		System.out.println(hyponymSynsets.size()+"hypon"+hyponymSynsets);
		for(int i=0;i<hyponymSynsets.size();i++){
			Long hyponymSynsetID = hyponymSynsets.get(i).getOffset();
			MongoSinhalaSencePointer sencePointer = new MongoSinhalaSencePointer(hyponymSynsetID, MongoSinhalaPointerTyps.HYPONYM);
			sencePointerList.add(sencePointer);
			System.out.println("hypon added"+sencePointer);
		}
		}
		catch(Exception e){
			System.out.println("ee"+e);
			
		}
		
		try{
		List<SinhalaWordNetSynset> attributeSynsets =null; //nounSynset.getAttributes();
		System.out.println("attri"+attributeSynsets);
		for(int i=0;i<attributeSynsets.size();i++){
			Long attributeSynsetID = attributeSynsets.get(i).getOffset();
			MongoSinhalaSencePointer sencePointer = new MongoSinhalaSencePointer(attributeSynsetID, MongoSinhalaPointerTyps.ATTRIBUTE);
			sencePointerList.add(sencePointer);
		}
		}
		catch(Exception e){
			System.out.println("ee"+e);
			
		}
		
		try{
		List<SinhalaWordNetSynset> meronymSynsets = null;///nounSynset.getMeronyms();
		System.out.println("mero"+meronymSynsets);
		for(int i=0;i<meronymSynsets.size();i++){
			Long meronymSynsetID = meronymSynsets.get(i).getOffset();
			MongoSinhalaSencePointer sencePointer = new MongoSinhalaSencePointer(meronymSynsetID, MongoSinhalaPointerTyps.PART_MERONYM);
			sencePointerList.add(sencePointer);
		}
		
		}
		catch(Exception e){
			System.out.println("ee"+e);
			
		}
		MongoSinhalaNoun mongoNounsynset = new MongoSinhalaNoun(ewnid,wordList,sencePointerList,nounSynset.getDefinition()+"|"+nounSynset.getExample());
		System.out.println("ssssssssss"+mongoNounsynset);
		return mongoNounsynset;
		
	}
	
	public NounSynset OverWriteByMongo(NounSynset nounSynset){
		SynsetMongoDbHandler mongoDBhandler = new SynsetMongoDbHandler();
		MongoSinhalaNoun mongoNoun = mongoDBhandler.findBySynsetId(nounSynset.getOffset());
		
		System.out.println(mongoNoun+"------"+nounSynset.getOffset());
		if(mongoNoun != null){
		List<MongoSinhalaWord> mongoWords = mongoNoun.getWords();
		//List<SinhalaWordNetWord> uiWords = nounSynset.getWords();
		//uiWords.clear();
		List<SinhalaWordNetWord> updatedUiWords = new ArrayList<SinhalaWordNetWord>();
		for(int i=0;i<mongoWords.size();i++){
			SinhalaWordNetWord tempWord = new SinhalaWordNetWord();
			//mongoWords.get(i).getLemma()
			tempWord.setLemmaFromMongo(mongoWords.get(i).getLemma());
			System.out.println(tempWord.getLemma()+"in mongo convertor"+mongoWords.get(i).getLemma());
			updatedUiWords.add(tempWord);
		}
		//uiWords = updatedUiWords;
		
		String gloss = mongoNoun.getGloss();
		String[] parts = new String[2];
		parts[0] = "";
		parts[1] = "";
		parts =  gloss.split("\\|");
		//System.out.println(gloss+"===="+parts[0]+"---glossss------"+parts[1]);
		NounSynset tempNoun;
		if(parts.length > 1){
		
		tempNoun = new NounSynset(mongoNoun.getId(),nounSynset.getOffset(),parts[0],parts[1],updatedUiWords);
		}
		else if(parts.length > 0){
			tempNoun = new NounSynset(mongoNoun.getId(),nounSynset.getOffset(),parts[0],"",updatedUiWords);
		}
		else{
			tempNoun = new NounSynset(mongoNoun.getId(),nounSynset.getOffset(),"","",updatedUiWords);
		}
		return tempNoun;
			
		}
		else{
			//NounSynset newnoun = new NounSynset();
			return nounSynset;
		}
	}
	

}
