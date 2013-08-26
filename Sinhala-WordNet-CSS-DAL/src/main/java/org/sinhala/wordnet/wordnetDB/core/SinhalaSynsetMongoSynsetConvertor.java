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
			
			if(sinhalaWordNetword != null){
				
				MongoSinhalaWordPointer wordPointer1 = new MongoSinhalaWordPointer(con,sinhalaWordNetword.getLemma(), MongoSinhalaPointerTyps.ANTONYM);
				wordPointerList.add(wordPointer1);
				//System.out.println(sinhalaWordNetword+"anto"+wordPointer1);
			}
			//}
			//catch(Exception e){
			//	System.out.println(e );
			//}
			sinhalaWordNetword=null;
			long tempSynId = 0;
			try{
			 sinhalaWordNetword = words.get(i).getDerivationType();//words.get(i).getDerivationType();
			 //System.out.println(sinhalaWordNetword+"devi");
			if(sinhalaWordNetword!=null){
				String derivationType = sinhalaWordNetword.getLemma();
				if(derivationType.equalsIgnoreCase("තත්සම")){
					tempSynId = 1;
				}
				else if(derivationType.equalsIgnoreCase("තත්භව")){
					tempSynId = 2;
				}
				
				MongoSinhalaWordPointer wordPointer2 = new MongoSinhalaWordPointer(tempSynId, "0", MongoSinhalaPointerTyps.DERIVATION_TYPE);
				wordPointerList.add(wordPointer2);
				//System.out.println("devi"+wordPointer2);
			}
			}
			catch(Exception e){
				System.out.println(e);
			}
			sinhalaWordNetword=null;
			tempSynId = 0;
			try{
			 sinhalaWordNetword = words.get(i).getOrigin();
			if(sinhalaWordNetword.getId()!=null){
				
				String origin = sinhalaWordNetword.getLemma();
				//System.out.println("හින්දි"+origin);
				if(origin.equalsIgnoreCase("නොදනී")){
					tempSynId = 1;
				}
				else if(origin.equalsIgnoreCase("හින්දි")){
					//System.out.println("got to hindi");
					tempSynId = 2;
				}
				else if(origin.equalsIgnoreCase("දෙමළ")){
					tempSynId = 3;
				}
				else if(origin.equalsIgnoreCase("ඉංග්‍රීසි")){
					tempSynId = 4;
				}
				else if(origin.equalsIgnoreCase("පෘතුග්‍රීසි")){
					tempSynId = 5;
				}
				else if(origin.equalsIgnoreCase("ලංදේසි")){
					tempSynId = 6;
				}
				MongoSinhalaWordPointer wordPointer3 = new MongoSinhalaWordPointer(tempSynId, "0", MongoSinhalaPointerTyps.ORIGIN);
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
			tempSynId = 0;
			try{
			 sinhalaWordNetword = words.get(i).getUsage();
			if(sinhalaWordNetword.getId()!=null){
				String usage = sinhalaWordNetword.getLemma();
				if(usage.equalsIgnoreCase("ලිඛිත")){
					tempSynId = 1;
				}
				else if(usage.equalsIgnoreCase("වාචික")){
					tempSynId = 2;
				}
				MongoSinhalaWordPointer wordPointer5 = new MongoSinhalaWordPointer(tempSynId, "0", MongoSinhalaPointerTyps.USAGE);
				wordPointerList.add(wordPointer5);
			}
			}
			catch(Exception e){
				System.out.println(e);
			}
			sinhalaWordNetword=null;
			//System.out.println("word ponter list"+1+"-"+wordPointerList);
			MongoSinhalaWord word1 = new MongoSinhalaWord(lemma, words.get(i).getId(), wordPointerList);
			//System.out.println("word"+1+"-"+word1);
			wordList.add(word1);
			//wordPointerList=null;
			
			
		}
		
		try{
		List<SinhalaWordNetSynset> holoSynsets = null; //nounSynset.getHolonyms();
		//System.out.println("holo"+holoSynsets);
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
		//System.out.println("hyper"+hypernymSynsets);
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
		//System.out.println(hyponymSynsets.size()+"hypon"+hyponymSynsets);
		for(int i=0;i<hyponymSynsets.size();i++){
			Long hyponymSynsetID = hyponymSynsets.get(i).getOffset();
			MongoSinhalaSencePointer sencePointer = new MongoSinhalaSencePointer(hyponymSynsetID, MongoSinhalaPointerTyps.HYPONYM);
			sencePointerList.add(sencePointer);
			//System.out.println("hypon added"+sencePointer);
		}
		}
		catch(Exception e){
			System.out.println("ee"+e);
			
		}
		
		try{
		List<SinhalaWordNetSynset> attributeSynsets =null; //nounSynset.getAttributes();
		//System.out.println("attri"+attributeSynsets);
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
		//System.out.println("mero"+meronymSynsets);
		for(int i=0;i<meronymSynsets.size();i++){
			Long meronymSynsetID = meronymSynsets.get(i).getOffset();
			MongoSinhalaSencePointer sencePointer = new MongoSinhalaSencePointer(meronymSynsetID, MongoSinhalaPointerTyps.PART_MERONYM);
			sencePointerList.add(sencePointer);
		}
		
		}
		catch(Exception e){
			System.out.println("ee"+e);
			
		}
		try{
			SinhalaWordNetWord gender = nounSynset.getGender();
			long tempSynId = 0;
			//System.out.println("mero"+meronymSynsets);
			if(gender != null){
				String genderLemm = gender.getLemma();
				if(genderLemm.equalsIgnoreCase("පුරුෂ")){
					tempSynId = 1;
				}
				else if(genderLemm.equalsIgnoreCase("ස්ත්‍රී")){
					tempSynId = 2;
				}
				else if(genderLemm.equalsIgnoreCase("නොසලකා හරින්න")){
					tempSynId = 3;
				}
				MongoSinhalaSencePointer sencePointer = new MongoSinhalaSencePointer(tempSynId, MongoSinhalaPointerTyps.GENDER);
				sencePointerList.add(sencePointer);
			}
			
			}
			catch(Exception e){
				System.out.println("ee"+e);
				
			}
		MongoSinhalaNoun mongoNounsynset = new MongoSinhalaNoun(ewnid,wordList,sencePointerList,nounSynset.getDefinition()+"|"+nounSynset.getExample());
		//System.out.println("ssssssssss"+mongoNounsynset);
		return mongoNounsynset;
		
	}
	
	public NounSynset OverWriteByMongo(NounSynset nounSynset){
		SynsetMongoDbHandler mongoDBhandler = new SynsetMongoDbHandler();
		MongoSinhalaNoun mongoNoun = mongoDBhandler.findBySynsetId(nounSynset.getOffset());
		
		
		if(mongoNoun != null){
		List<MongoSinhalaWord> mongoWords = mongoNoun.getWords();
		//List<SinhalaWordNetWord> uiWords = nounSynset.getWords();
		//uiWords.clear();
		List<SinhalaWordNetWord> updatedUiWords = new ArrayList<SinhalaWordNetWord>();
		
		for(int i=0;i<mongoWords.size();i++){
			SinhalaWordNetWord tempWord = new SinhalaWordNetWord();
			//mongoWords.get(i).getLemma()
			tempWord.setLemmaFromMongo(mongoWords.get(i).getLemma());
			List<MongoSinhalaWordPointer> wordPointers = mongoWords.get(i).getWordPointerList();
			for(int j = 0;j<wordPointers.size();j++){
				//pointerWord= null;
				
				if(wordPointers.get(j).getPointerType().equals(MongoSinhalaPointerTyps.ORIGIN)){
					SinhalaWordNetWord pointerWordOri = new SinhalaWordNetWord();
					if(wordPointers.get(j).getSynsetId()== 1){
						pointerWordOri.setLemmaFromMongo("නොදනී");
					//tempWord.getOrigin().setLemmaFromMongo("නොදනී");
							}
					else if(wordPointers.get(j).getSynsetId()== 2){
						//System.out.println("in side if");
						pointerWordOri.setLemmaFromMongo("හින්දි");
						//tempWord.getOrigin().setLemmaFromMongo("හින්දි");
								}
					else if(wordPointers.get(j).getSynsetId()== 3){
						pointerWordOri.setLemmaFromMongo("දෙමළ");
						//tempWord.getOrigin().setLemmaFromMongo("දෙමළ");
								}
					else if(wordPointers.get(j).getSynsetId()== 4){
						pointerWordOri.setLemmaFromMongo("ඉංග්‍රීසි");
						//tempWord.getOrigin().setLemmaFromMongo("ඉංග්‍රීසි");
								}
					else if(wordPointers.get(j).getSynsetId()== 5){
						pointerWordOri.setLemmaFromMongo("පෘතුග්‍රීසි");
						//tempWord.getOrigin().setLemmaFromMongo("පෘතුග්‍රීසි");
								}
					else if(wordPointers.get(j).getSynsetId()== 6){
						pointerWordOri.setLemmaFromMongo("ලංදේසි");
						//tempWord.getOrigin().setLemmaFromMongo("ලංදේසි");
								}
					tempWord.setOrigin(pointerWordOri);
				}
				else if(wordPointers.get(j).getPointerType().equals(MongoSinhalaPointerTyps.USAGE)){
					SinhalaWordNetWord pointerWordUse = new SinhalaWordNetWord();
					
					if(wordPointers.get(j).getSynsetId()== 1){
						pointerWordUse.setLemmaFromMongo("ලිඛිත");
					//tempWord.getUsage().setLemmaFromMongo("ලිඛිත");
							}
					else if(wordPointers.get(j).getSynsetId()== 2){
						//System.out.println("in side if");
						pointerWordUse.setLemmaFromMongo("වාචික");
						//tempWord.getUsage().setLemmaFromMongo("වාචික");
								}
					tempWord.setUsage(pointerWordUse);
				}
				else if(wordPointers.get(j).getPointerType().equals(MongoSinhalaPointerTyps.DERIVATION_TYPE)){
					SinhalaWordNetWord pointerWordDeri = new SinhalaWordNetWord();
					
					if(wordPointers.get(j).getSynsetId()== 1){
						pointerWordDeri.setLemmaFromMongo("තත්සම");
					//tempWord.getOrigin().setLemmaFromMongo("තත්සම");
							}
					else if(wordPointers.get(j).getSynsetId()== 2){
						//System.out.println("in side if");
						pointerWordDeri.setLemmaFromMongo("තත්භව");
						//tempWord.getOrigin().setLemmaFromMongo("තත්භව");
								}
					tempWord.setDerivationType(pointerWordDeri);
				}
			}
			//System.out.println(tempWord.getLemma()+"in mongo convertor"+mongoWords.get(i).getLemma());
			updatedUiWords.add(tempWord);
		}
		List<MongoSinhalaSencePointer> sencePointerList = new ArrayList<MongoSinhalaSencePointer>();
		MongoSinhalaSencePointer genderPointer = new MongoSinhalaSencePointer();
		SinhalaWordNetWord genderWord = new SinhalaWordNetWord();
		try{
			sencePointerList = mongoNoun.getSencePointers();
			for(int k=0 ; k<sencePointerList.size();k++){
				if(sencePointerList.get(k).getPointerType().equals(MongoSinhalaPointerTyps.GENDER)){
				
					if(sencePointerList.get(k).getSynsetId()== 1){
					genderWord.setLemmaFromMongo("පුරුෂ");
				//tempWord.getOrigin().setLemmaFromMongo("තත්සම");
						}
					else if(sencePointerList.get(k).getSynsetId()== 2){
					//System.out.println("in side if");
					genderWord.setLemmaFromMongo("ස්ත්‍රී");
					//tempWord.getOrigin().setLemmaFromMongo("තත්භව");
							}
					else if(sencePointerList.get(k).getSynsetId()== 3){
					//System.out.println("in side if");
					genderWord.setLemmaFromMongo("නොසලකා හරින්න");
					//tempWord.getOrigin().setLemmaFromMongo("තත්භව");
							}
					
				}
			}
			
		}
		catch(Exception e){
			System.out.println("exeption in gender pointer convetor");
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
		
		tempNoun = new NounSynset(mongoNoun.getId(),nounSynset.getOffset(),parts[0],parts[1],updatedUiWords,genderWord);
		}
		else if(parts.length > 0){
			tempNoun = new NounSynset(mongoNoun.getId(),nounSynset.getOffset(),parts[0],"",updatedUiWords,genderWord);
		}
		else{
			tempNoun = new NounSynset(mongoNoun.getId(),nounSynset.getOffset(),"","",updatedUiWords,genderWord);
		}
		return tempNoun;
			
		}
		else{
			//NounSynset newnoun = new NounSynset();
			return nounSynset;
		}
	}
	

}
