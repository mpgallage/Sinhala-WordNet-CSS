package org.sinhala.wordnet.wordnetDB.core;

import java.util.ArrayList;
import java.util.List;

import net.didion.jwnl.data.POS;

import org.sinhala.wordnet.css.model.wordnet.AdjectiveSynset;
import org.sinhala.wordnet.css.model.wordnet.NounSynset;
import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetSynset;
import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetWord;
import org.sinhala.wordnet.css.model.wordnet.VerbSynset;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaAdjective;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaNoun;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaPointerTyps;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaRoot;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaSencePointer;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaSynset;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaVerb;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaWord;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaWordPointer;

public class SinhalaSynsetMongoSynsetConvertor {

	
	// function to convert Synset into mongoDB format
	public MongoSinhalaSynset converttoMongoSynset(SinhalaWordNetSynset Synset) {

		SinhalaWordNetWord sinhalaWordNetword = null;
		
		List<MongoSinhalaWord> wordList = new ArrayList<MongoSinhalaWord>();
		List<MongoSinhalaSencePointer> sencePointerList = new ArrayList<MongoSinhalaSencePointer>();

		
		String userName = Synset.getUserName();
		String comment = Synset.getComment();
		String rating = Synset.getRating();
		String evaluated = Synset.getEvaluated();
		String evaluatedBy = Synset.getEvaluatedBy();
		long con = 123456;
		Long ewnid = Synset.getOffset();
		List<SinhalaWordNetWord> words = Synset.getWords();

		for (int i = 0; i < words.size(); i++) {
			List<MongoSinhalaWordPointer> wordPointerList = new ArrayList<MongoSinhalaWordPointer>();
			String lemma = words.get(i).getLemma();
			sinhalaWordNetword = null;
			// try{
			sinhalaWordNetword = words.get(i).getAntonym();													// get antonym

			if (sinhalaWordNetword != null) {																// antonym handle part

				MongoSinhalaWordPointer wordPointer1 = new MongoSinhalaWordPointer("n",
						con, sinhalaWordNetword.getLemma(),
						MongoSinhalaPointerTyps.ANTONYM);
				wordPointerList.add(wordPointer1);
				
			}
			
			sinhalaWordNetword = null;
			long tempSynId = 0;
			try {
				sinhalaWordNetword = words.get(i).getDerivationType();										// get derivationType
				
				if (sinhalaWordNetword != null) {															// derivationtype handle part
					String derivationType = sinhalaWordNetword.getLemma();
					if (derivationType.equalsIgnoreCase("තත්සම")) {
						tempSynId = 1;
					} else if (derivationType.equalsIgnoreCase("තත්භව")) {
						tempSynId = 2;
					}
					else if (derivationType.equalsIgnoreCase("නොදනී")) {
						tempSynId = 3;
					}

					MongoSinhalaWordPointer wordPointer2 = new MongoSinhalaWordPointer("d",
							tempSynId, "0",
							MongoSinhalaPointerTyps.DERIVATION_TYPE);
					wordPointerList.add(wordPointer2);
					
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			sinhalaWordNetword = null;
			tempSynId = 0;
			try {
				sinhalaWordNetword = words.get(i).getOrigin();												// get origin
				if (sinhalaWordNetword.getId() != null) {													// origin handler part

					String origin = sinhalaWordNetword.getLemma();
					
					if (origin.equalsIgnoreCase("නොදනී")) {
						tempSynId = 1;
					} else if (origin.equalsIgnoreCase("හින්දි")) {
						
						tempSynId = 2;
					} else if (origin.equalsIgnoreCase("දෙමළ")) {
						tempSynId = 3;
					} else if (origin.equalsIgnoreCase("ඉංග්‍රීසි")) {
						tempSynId = 4;
					} else if (origin.equalsIgnoreCase("පෘතුග්‍රීසි")) {
						tempSynId = 5;
					} else if (origin.equalsIgnoreCase("ලංදේසි")) {
						tempSynId = 6;
					} else if (origin.equalsIgnoreCase("පාලි")) {
						tempSynId = 7;
					} else if (origin.equalsIgnoreCase("සංස්කෘත")) {
						tempSynId = 8;
					}
					MongoSinhalaWordPointer wordPointer3 = new MongoSinhalaWordPointer("o",
							tempSynId, "0", MongoSinhalaPointerTyps.ORIGIN);
					wordPointerList.add(wordPointer3);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			sinhalaWordNetword = null;
			try {
				sinhalaWordNetword = words.get(i).getRoot();												// get root
				if (sinhalaWordNetword.getId() != null) {													// root handler part
						SynsetMongoDbHandler dbHandler = new SynsetMongoDbHandler();
						dbHandler.addRoot(sinhalaWordNetword.getLemma(),userName);
						MongoSinhalaWordPointer wordPointer4 = new MongoSinhalaWordPointer("r",
								dbHandler.findRootByLemma(
										sinhalaWordNetword.getLemma()).getId(),
								"0", MongoSinhalaPointerTyps.ROOT);
						wordPointerList.add(wordPointer4);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			sinhalaWordNetword = null;
			tempSynId = 0;
			try {
				sinhalaWordNetword = words.get(i).getUsage();
				if (sinhalaWordNetword.getId() != null) {
					String usage = sinhalaWordNetword.getLemma();
					if (usage.equalsIgnoreCase("ලිඛිත")) {
						tempSynId = 1;
					} else if (usage.equalsIgnoreCase("වාචික")) {
						tempSynId = 2;
					}
					else if (usage.equalsIgnoreCase("නොදනී")) {
						tempSynId = 3;
					}
					MongoSinhalaWordPointer wordPointer5 = new MongoSinhalaWordPointer("u",
							tempSynId, "0", MongoSinhalaPointerTyps.USAGE);
					wordPointerList.add(wordPointer5);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			sinhalaWordNetword = null;
			
			MongoSinhalaWord word1 = new MongoSinhalaWord(lemma, words.get(i)
					.getId(), wordPointerList);
			
			wordList.add(word1);
			

		}

		try {
			List<SinhalaWordNetSynset> holoSynsets = null; // nounSynset.getHolonyms();
			
			for (int i = 0; i < holoSynsets.size(); i++) {
				Long holoSynsetID = holoSynsets.get(i).getOffset();
				MongoSinhalaSencePointer sencePointer = new MongoSinhalaSencePointer("n",
						holoSynsetID, MongoSinhalaPointerTyps.PART_HOLONYM);
				sencePointerList.add(sencePointer);
			}
		} catch (Exception e) {
			System.out.println("ee" + e);

		}

		try {
			List<SinhalaWordNetSynset> hypernymSynsets = null; // nounSynset.getHypernyms();
			
			for (int i = 0; i < hypernymSynsets.size(); i++) {
				Long hypernymSynsetID = hypernymSynsets.get(i).getOffset();
				MongoSinhalaSencePointer sencePointer = new MongoSinhalaSencePointer("n",
						hypernymSynsetID, MongoSinhalaPointerTyps.HYPERNYM);
				sencePointerList.add(sencePointer);
			}
		} catch (Exception e) {
			System.out.println("ee" + e);

		}

		try {
			List<SinhalaWordNetSynset> hyponymSynsets = null;// nounSynset.getHyponyms();
			
			for (int i = 0; i < hyponymSynsets.size(); i++) {
				Long hyponymSynsetID = hyponymSynsets.get(i).getOffset();
				MongoSinhalaSencePointer sencePointer = new MongoSinhalaSencePointer("n",
						hyponymSynsetID, MongoSinhalaPointerTyps.HYPONYM);
				sencePointerList.add(sencePointer);
				
			}
		} catch (Exception e) {
			System.out.println("ee" + e);

		}

		try {
			List<SinhalaWordNetSynset> attributeSynsets = null; // nounSynset.getAttributes();
			
			for (int i = 0; i < attributeSynsets.size(); i++) {
				Long attributeSynsetID = attributeSynsets.get(i).getOffset();
				MongoSinhalaSencePointer sencePointer = new MongoSinhalaSencePointer("n",
						attributeSynsetID, MongoSinhalaPointerTyps.ATTRIBUTE);
				sencePointerList.add(sencePointer);
			}
		} catch (Exception e) {
			System.out.println("ee" + e);

		}

		try {
			List<SinhalaWordNetSynset> meronymSynsets = null;// /nounSynset.getMeronyms();
			
			for (int i = 0; i < meronymSynsets.size(); i++) {
				Long meronymSynsetID = meronymSynsets.get(i).getOffset();
				MongoSinhalaSencePointer sencePointer = new MongoSinhalaSencePointer("n",
						meronymSynsetID, MongoSinhalaPointerTyps.PART_MERONYM);
				sencePointerList.add(sencePointer);
			}

		} catch (Exception e) {
			System.out.println("ee" + e);

		}
		try {
			SinhalaWordNetWord gender = Synset.getGender();
			long tempSynId = 0;
			
			if (gender != null) {
				String genderLemm = gender.getLemma();
				if (genderLemm.equalsIgnoreCase("පුරුෂ")) {
					tempSynId = 1;
				} else if (genderLemm.equalsIgnoreCase("ස්ත්‍රී")) {
					tempSynId = 2;
				} else if (genderLemm.equalsIgnoreCase("නොසලකා හරින්න")) {
					tempSynId = 3;
				}
				MongoSinhalaSencePointer sencePointer = new MongoSinhalaSencePointer("g",
						tempSynId, MongoSinhalaPointerTyps.GENDER);
				sencePointerList.add(sencePointer);
			}

		} catch (Exception e) {
			System.out.println("ee" + e);

		}
		MongoSinhalaSynset mongoSynset = null;
		
		if(Synset instanceof NounSynset){																	// if noun synset
		
			mongoSynset = new MongoSinhalaNoun(ewnid,
				wordList, sencePointerList, Synset.getDefinition() + "|"
						+ Synset.getExample(),userName);
		mongoSynset.setComment(comment);
		mongoSynset.setRating(rating);
		if(evaluated.equals("true")){
			mongoSynset.SetEvaluated();
		}
		mongoSynset.SetEvaluatedBy(evaluatedBy);
		}
		else if(Synset instanceof VerbSynset){																// if verb synset
			mongoSynset = new MongoSinhalaVerb(ewnid,
					wordList, sencePointerList, Synset.getDefinition() + "|"
							+ Synset.getExample(),userName);
			mongoSynset.setComment(comment);
			mongoSynset.setRating(rating);
			if(evaluated.equals("true")){
				mongoSynset.SetEvaluated();
			}
			mongoSynset.SetEvaluatedBy(evaluatedBy);
			
		}
		else if(Synset instanceof AdjectiveSynset){															// if adjective synset
			mongoSynset = new MongoSinhalaAdjective(ewnid,
					wordList, sencePointerList, Synset.getDefinition() + "|"
							+ Synset.getExample(),userName);
			mongoSynset.setComment(comment);
			mongoSynset.setRating(rating);
			if(evaluated.equals("true")){
				mongoSynset.SetEvaluated();
			}
			mongoSynset.SetEvaluatedBy(evaluatedBy);
		}
		
		return mongoSynset;

	}

	

	public NounSynset OverWriteByMongo(NounSynset nounSynset,String mongoId) {
		SynsetMongoDbHandler mongoDBhandler = new SynsetMongoDbHandler();
		MongoSinhalaNoun mongoNoun = null;
		if(mongoId!=null && mongoId!=""){
			mongoNoun = (MongoSinhalaNoun) mongoDBhandler.findBySynsetMongoId(mongoId,POS.NOUN);
		}
		else{
			mongoNoun = (MongoSinhalaNoun) mongoDBhandler.findBySynsetId(nounSynset
					.getOffset(),POS.NOUN);
			
		}

		if (mongoNoun != null) {
			List<MongoSinhalaWord> mongoWords = mongoNoun.getWords();
			// List<SinhalaWordNetWord> uiWords = nounSynset.getWords();
			// uiWords.clear();
			List<SinhalaWordNetWord> updatedUiWords = new ArrayList<SinhalaWordNetWord>();

			for (int i = 0; i < mongoWords.size(); i++) {
				SinhalaWordNetWord tempWord = new SinhalaWordNetWord();
				// mongoWords.get(i).getLemma()
				tempWord.setLemmaFromMongo(mongoWords.get(i).getLemma());
				List<MongoSinhalaWordPointer> wordPointers = mongoWords.get(i)
						.getWordPointerList();
				for (int j = 0; j < wordPointers.size(); j++) {
					// pointerWord= null;

					if (wordPointers.get(j).getPointerType()
							.equals(MongoSinhalaPointerTyps.ORIGIN)) {
						SinhalaWordNetWord pointerWordOri = new SinhalaWordNetWord();
						if (wordPointers.get(j).getSynsetId() == 1) {
							pointerWordOri.setLemmaFromMongo("නොදනී");
							// tempWord.getOrigin().setLemmaFromMongo("නොදනී");
						} else if (wordPointers.get(j).getSynsetId() == 2) {
							// System.out.println("in side if");
							pointerWordOri.setLemmaFromMongo("හින්දි");
							// tempWord.getOrigin().setLemmaFromMongo("හින්දි");
						} else if (wordPointers.get(j).getSynsetId() == 3) {
							pointerWordOri.setLemmaFromMongo("දෙමළ");
							// tempWord.getOrigin().setLemmaFromMongo("දෙමළ");
						} else if (wordPointers.get(j).getSynsetId() == 4) {
							pointerWordOri.setLemmaFromMongo("ඉංග්‍රීසි");
							// tempWord.getOrigin().setLemmaFromMongo("ඉංග්‍රීසි");
						} else if (wordPointers.get(j).getSynsetId() == 5) {
							pointerWordOri.setLemmaFromMongo("පෘතුග්‍රීසි");
							// tempWord.getOrigin().setLemmaFromMongo("පෘතුග්‍රීසි");
						} else if (wordPointers.get(j).getSynsetId() == 6) {
							pointerWordOri.setLemmaFromMongo("ලංදේසි");
							// tempWord.getOrigin().setLemmaFromMongo("ලංදේසි");
						}else if (wordPointers.get(j).getSynsetId() == 7) {
						pointerWordOri.setLemmaFromMongo("පාලි");
						// tempWord.getOrigin().setLemmaFromMongo("පෘතුග්‍රීසි");
						 } else if (wordPointers.get(j).getSynsetId() == 8) {
						pointerWordOri.setLemmaFromMongo("සංස්කෘත");
						// tempWord.getOrigin().setLemmaFromMongo("ලංදේසි");
						 }
						tempWord.setOrigin(pointerWordOri);
					} else if (wordPointers.get(j).getPointerType()
							.equals(MongoSinhalaPointerTyps.USAGE)) {
						SinhalaWordNetWord pointerWordUse = new SinhalaWordNetWord();

						if (wordPointers.get(j).getSynsetId() == 1) {
							pointerWordUse.setLemmaFromMongo("ලිඛිත");
							// tempWord.getUsage().setLemmaFromMongo("ලිඛිත");
						} else if (wordPointers.get(j).getSynsetId() == 2) {
							// System.out.println("in side if");
							pointerWordUse.setLemmaFromMongo("වාචික");
							// tempWord.getUsage().setLemmaFromMongo("වාචික");
						}
						else if (wordPointers.get(j).getSynsetId() == 3) {
							// System.out.println("in side if");
							pointerWordUse.setLemmaFromMongo("නොදනී");
							// tempWord.getUsage().setLemmaFromMongo("වාචික");
						}
						tempWord.setUsage(pointerWordUse);
					} else if (wordPointers.get(j).getPointerType()
							.equals(MongoSinhalaPointerTyps.DERIVATION_TYPE)) {
						SinhalaWordNetWord pointerWordDeri = new SinhalaWordNetWord();

						if (wordPointers.get(j).getSynsetId() == 1) {
							pointerWordDeri.setLemmaFromMongo("තත්සම");
							// tempWord.getOrigin().setLemmaFromMongo("තත්සම");
						} else if (wordPointers.get(j).getSynsetId() == 2) {
							// System.out.println("in side if");
							pointerWordDeri.setLemmaFromMongo("තත්භව");
							// tempWord.getOrigin().setLemmaFromMongo("තත්භව");
						}
						else if (wordPointers.get(j).getSynsetId() == 3) {
							// System.out.println("in side if");
							pointerWordDeri.setLemmaFromMongo("නොදනී");
							// tempWord.getOrigin().setLemmaFromMongo("තත්භව");
						}
						tempWord.setDerivationType(pointerWordDeri);
					}

					else if (wordPointers.get(j).getPointerType()
							.equals(MongoSinhalaPointerTyps.ROOT)) {
						SinhalaWordNetWord pointerWordRoot = new SinhalaWordNetWord();

						SynsetMongoDbHandler dbHandler = new SynsetMongoDbHandler();
						MongoSinhalaRoot root = dbHandler.findRootByID(String
								.valueOf(wordPointers.get(j).getSynsetIDasString()));
						if (root != null) {
							pointerWordRoot.setLemmaFromMongo(root.getWords().get(0)
									.getLemma());
							pointerWordRoot.setId(root.getWords().get(0)
									.getId());

							tempWord.setRoot(pointerWordRoot);
						}
					}
				}
				// System.out.println(tempWord.getLemma()+"in mongo convertor"+mongoWords.get(i).getLemma());
				updatedUiWords.add(tempWord);
			}
			List<MongoSinhalaSencePointer> sencePointerList = new ArrayList<MongoSinhalaSencePointer>();
			MongoSinhalaSencePointer genderPointer = new MongoSinhalaSencePointer();
			SinhalaWordNetWord genderWord = new SinhalaWordNetWord();
			try {
				sencePointerList = mongoNoun.getSencePointers();
				for (int k = 0; k < sencePointerList.size(); k++) {
					if (sencePointerList.get(k).getPointerType()
							.equals(MongoSinhalaPointerTyps.GENDER)) {

						if (sencePointerList.get(k).getSynsetId() == 1) {
							genderWord.setLemmaFromMongo("පුරුෂ");
							// tempWord.getOrigin().setLemmaFromMongo("තත්සම");
						} else if (sencePointerList.get(k).getSynsetId() == 2) {
							// System.out.println("in side if");
							genderWord.setLemmaFromMongo("ස්ත්‍රී");
							// tempWord.getOrigin().setLemmaFromMongo("තත්භව");
						} else if (sencePointerList.get(k).getSynsetId() == 3) {
							// System.out.println("in side if");
							genderWord.setLemmaFromMongo("නොසලකා හරින්න");
							// tempWord.getOrigin().setLemmaFromMongo("තත්භව");
						}

					}
				}

			} catch (Exception e) {
				System.out.println("exeption in gender pointer convetor");
			}
			// uiWords = updatedUiWords;

			String gloss = mongoNoun.getGloss();
			String[] parts = new String[2];
			parts[0] = "";
			parts[1] = "";
			parts = gloss.split("\\|");
			// System.out.println(gloss+"===="+parts[0]+"---glossss------"+parts[1]);
			NounSynset tempNoun;
			if (parts.length > 1) {

				tempNoun = new NounSynset(mongoNoun.getId(),
						nounSynset.getOffset(), parts[0], parts[1],
						updatedUiWords, genderWord);
			} else if (parts.length > 0) {
				tempNoun = new NounSynset(mongoNoun.getId(),
						nounSynset.getOffset(), parts[0], "", updatedUiWords,
						genderWord);
			} else {
				tempNoun = new NounSynset(mongoNoun.getId(),
						nounSynset.getOffset(), "", "", updatedUiWords,
						genderWord);
			}
			tempNoun.setCommentByMongo(mongoNoun.getComment());
			tempNoun.setRating(mongoNoun.getRating());
			//System.out.println(mongoNoun.getEWNId()+"id"+mongoNoun.getRating()+"rating"+tempNoun.getRating());
			tempNoun.setUserName(mongoNoun.getUserName());
			return tempNoun;

		} else {
			// NounSynset newnoun = new NounSynset();
			return nounSynset;
		}
	}
	
	public VerbSynset OverWriteByMongo(VerbSynset verbSynset,String mongoId) {
		SynsetMongoDbHandler mongoDBhandler = new SynsetMongoDbHandler();
		MongoSinhalaVerb mongoVerb=null;
		if(mongoId!=null && mongoId!=""){
			mongoVerb = (MongoSinhalaVerb) mongoDBhandler.findBySynsetMongoId(mongoId,POS.VERB);
		}
		else{
			mongoVerb = (MongoSinhalaVerb) mongoDBhandler.findBySynsetId(verbSynset
					.getOffset(),POS.VERB);
			}
		if (mongoVerb != null) {
			List<MongoSinhalaWord> mongoWords = mongoVerb.getWords();
			// List<SinhalaWordNetWord> uiWords = nounSynset.getWords();
			// uiWords.clear();
			List<SinhalaWordNetWord> updatedUiWords = new ArrayList<SinhalaWordNetWord>();

			for (int i = 0; i < mongoWords.size(); i++) {
				SinhalaWordNetWord tempWord = new SinhalaWordNetWord();
				// mongoWords.get(i).getLemma()
				tempWord.setLemmaFromMongo(mongoWords.get(i).getLemma());
				List<MongoSinhalaWordPointer> wordPointers = mongoWords.get(i)
						.getWordPointerList();
				for (int j = 0; j < wordPointers.size(); j++) {
					// pointerWord= null;

					if (wordPointers.get(j).getPointerType()
							.equals(MongoSinhalaPointerTyps.ORIGIN)) {
						SinhalaWordNetWord pointerWordOri = new SinhalaWordNetWord();
						if (wordPointers.get(j).getSynsetId() == 1) {
							pointerWordOri.setLemmaFromMongo("නොදනී");
							// tempWord.getOrigin().setLemmaFromMongo("නොදනී");
						} else if (wordPointers.get(j).getSynsetId() == 2) {
							// System.out.println("in side if");
							pointerWordOri.setLemmaFromMongo("හින්දි");
							// tempWord.getOrigin().setLemmaFromMongo("හින්දි");
						} else if (wordPointers.get(j).getSynsetId() == 3) {
							pointerWordOri.setLemmaFromMongo("දෙමළ");
							// tempWord.getOrigin().setLemmaFromMongo("දෙමළ");
						} else if (wordPointers.get(j).getSynsetId() == 4) {
							pointerWordOri.setLemmaFromMongo("ඉංග්‍රීසි");
							// tempWord.getOrigin().setLemmaFromMongo("ඉංග්‍රීසි");
						} else if (wordPointers.get(j).getSynsetId() == 5) {
							pointerWordOri.setLemmaFromMongo("පෘතුග්‍රීසි");
							// tempWord.getOrigin().setLemmaFromMongo("පෘතුග්‍රීසි");
						} else if (wordPointers.get(j).getSynsetId() == 6) {
							pointerWordOri.setLemmaFromMongo("ලංදේසි");
							// tempWord.getOrigin().setLemmaFromMongo("ලංදේසි");
						}else if (wordPointers.get(j).getSynsetId() == 5) {
						pointerWordOri.setLemmaFromMongo("පාලි");
						// tempWord.getOrigin().setLemmaFromMongo("පෘතුග්‍රීසි");
						 } else if (wordPointers.get(j).getSynsetId() == 6) {
						pointerWordOri.setLemmaFromMongo("සංස්කෘත");
						// tempWord.getOrigin().setLemmaFromMongo("ලංදේසි");
						 }
						tempWord.setOrigin(pointerWordOri);
					} else if (wordPointers.get(j).getPointerType()
							.equals(MongoSinhalaPointerTyps.USAGE)) {
						SinhalaWordNetWord pointerWordUse = new SinhalaWordNetWord();

						if (wordPointers.get(j).getSynsetId() == 1) {
							pointerWordUse.setLemmaFromMongo("ලිඛිත");
							// tempWord.getUsage().setLemmaFromMongo("ලිඛිත");
						} else if (wordPointers.get(j).getSynsetId() == 2) {
							// System.out.println("in side if");
							pointerWordUse.setLemmaFromMongo("වාචික");
							// tempWord.getUsage().setLemmaFromMongo("වාචික");
						}
						else if (wordPointers.get(j).getSynsetId() == 3) {
							// System.out.println("in side if");
							pointerWordUse.setLemmaFromMongo("නොදනී");
							// tempWord.getUsage().setLemmaFromMongo("වාචික");
						}
						tempWord.setUsage(pointerWordUse);
					} else if (wordPointers.get(j).getPointerType()
							.equals(MongoSinhalaPointerTyps.DERIVATION_TYPE)) {
						SinhalaWordNetWord pointerWordDeri = new SinhalaWordNetWord();

						if (wordPointers.get(j).getSynsetId() == 1) {
							pointerWordDeri.setLemmaFromMongo("තත්සම");
							// tempWord.getOrigin().setLemmaFromMongo("තත්සම");
						} else if (wordPointers.get(j).getSynsetId() == 2) {
							// System.out.println("in side if");
							pointerWordDeri.setLemmaFromMongo("තත්භව");
							// tempWord.getOrigin().setLemmaFromMongo("තත්භව");
						}
						else if (wordPointers.get(j).getSynsetId() == 3) {
							// System.out.println("in side if");
							pointerWordDeri.setLemmaFromMongo("නොදනී");
							// tempWord.getOrigin().setLemmaFromMongo("තත්භව");
						}
						tempWord.setDerivationType(pointerWordDeri);
					}

					else if (wordPointers.get(j).getPointerType()
							.equals(MongoSinhalaPointerTyps.ROOT)) {
						SinhalaWordNetWord pointerWordRoot = new SinhalaWordNetWord();

						SynsetMongoDbHandler dbHandler = new SynsetMongoDbHandler();
						MongoSinhalaRoot root = dbHandler.findRootByID(String
								.valueOf(wordPointers.get(j).getSynsetIDasString()));
						if (root != null) {
							pointerWordRoot.setLemmaFromMongo(root.getWords().get(0)
									.getLemma());
							pointerWordRoot.setId(root.getWords().get(0)
									.getId());

							tempWord.setRoot(pointerWordRoot);
						}
					}
				}
				// System.out.println(tempWord.getLemma()+"in mongo convertor"+mongoWords.get(i).getLemma());
				updatedUiWords.add(tempWord);
			}
			List<MongoSinhalaSencePointer> sencePointerList = new ArrayList<MongoSinhalaSencePointer>();
			MongoSinhalaSencePointer genderPointer = new MongoSinhalaSencePointer();
			SinhalaWordNetWord genderWord = new SinhalaWordNetWord();
			try {
				sencePointerList = mongoVerb.getSencePointers();
				for (int k = 0; k < sencePointerList.size(); k++) {
					if (sencePointerList.get(k).getPointerType()
							.equals(MongoSinhalaPointerTyps.GENDER)) {

						if (sencePointerList.get(k).getSynsetId() == 1) {
							genderWord.setLemmaFromMongo("පුරුෂ");
							// tempWord.getOrigin().setLemmaFromMongo("තත්සම");
						} else if (sencePointerList.get(k).getSynsetId() == 2) {
							// System.out.println("in side if");
							genderWord.setLemmaFromMongo("ස්ත්‍රී");
							// tempWord.getOrigin().setLemmaFromMongo("තත්භව");
						} else if (sencePointerList.get(k).getSynsetId() == 3) {
							// System.out.println("in side if");
							genderWord.setLemmaFromMongo("නොසලකා හරින්න");
							// tempWord.getOrigin().setLemmaFromMongo("තත්භව");
						}

					}
				}

			} catch (Exception e) {
				System.out.println("exeption in gender pointer convetor");
			}
			// uiWords = updatedUiWords;

			String gloss = mongoVerb.getGloss();
			String[] parts = new String[2];
			parts[0] = "";
			parts[1] = "";
			parts = gloss.split("\\|");
			// System.out.println(gloss+"===="+parts[0]+"---glossss------"+parts[1]);
			VerbSynset tempVerb;
			if (parts.length > 1) {

				tempVerb = new VerbSynset(mongoVerb.getId(),
						verbSynset.getOffset(), parts[0], parts[1],
						updatedUiWords, genderWord);
			} else if (parts.length > 0) {
				tempVerb = new VerbSynset(mongoVerb.getId(),
						verbSynset.getOffset(), parts[0], "", updatedUiWords,
						genderWord);
			} else {
				tempVerb = new VerbSynset(mongoVerb.getId(),
						verbSynset.getOffset(), "", "", updatedUiWords,
						genderWord);
			}
			
			tempVerb.setCommentByMongo(mongoVerb.getComment());
			tempVerb.setRating(mongoVerb.getRating());
			tempVerb.setUserName(mongoVerb.getUserName());
			return tempVerb;

		} else {
			// NounSynset newnoun = new NounSynset();
			return verbSynset;
		}
	}
	
	
	public AdjectiveSynset OverWriteByMongo(AdjectiveSynset adjSynset,String mongoId) {
		SynsetMongoDbHandler mongoDBhandler = new SynsetMongoDbHandler();
		MongoSinhalaAdjective mongoAdj = null;
		if(mongoId!=null && mongoId!=""){
			mongoAdj = (MongoSinhalaAdjective) mongoDBhandler.findBySynsetMongoId(mongoId,POS.ADJECTIVE);
		}
		else{
			mongoAdj = (MongoSinhalaAdjective) mongoDBhandler.findBySynsetId(adjSynset
					.getOffset(),POS.ADJECTIVE);
			}
		if (mongoAdj != null) {
			List<MongoSinhalaWord> mongoWords = mongoAdj.getWords();
			// List<SinhalaWordNetWord> uiWords = nounSynset.getWords();
			// uiWords.clear();
			List<SinhalaWordNetWord> updatedUiWords = new ArrayList<SinhalaWordNetWord>();

			for (int i = 0; i < mongoWords.size(); i++) {
				SinhalaWordNetWord tempWord = new SinhalaWordNetWord();
				// mongoWords.get(i).getLemma()
				tempWord.setLemmaFromMongo(mongoWords.get(i).getLemma());
				List<MongoSinhalaWordPointer> wordPointers = mongoWords.get(i)
						.getWordPointerList();
				for (int j = 0; j < wordPointers.size(); j++) {
					// pointerWord= null;

					if (wordPointers.get(j).getPointerType()
							.equals(MongoSinhalaPointerTyps.ORIGIN)) {
						SinhalaWordNetWord pointerWordOri = new SinhalaWordNetWord();
						if (wordPointers.get(j).getSynsetId() == 1) {
							pointerWordOri.setLemmaFromMongo("නොදනී");
							// tempWord.getOrigin().setLemmaFromMongo("නොදනී");
						} else if (wordPointers.get(j).getSynsetId() == 2) {
							// System.out.println("in side if");
							pointerWordOri.setLemmaFromMongo("හින්දි");
							// tempWord.getOrigin().setLemmaFromMongo("හින්දි");
						} else if (wordPointers.get(j).getSynsetId() == 3) {
							pointerWordOri.setLemmaFromMongo("දෙමළ");
							// tempWord.getOrigin().setLemmaFromMongo("දෙමළ");
						} else if (wordPointers.get(j).getSynsetId() == 4) {
							pointerWordOri.setLemmaFromMongo("ඉංග්‍රීසි");
							// tempWord.getOrigin().setLemmaFromMongo("ඉංග්‍රීසි");
						} else if (wordPointers.get(j).getSynsetId() == 5) {
							pointerWordOri.setLemmaFromMongo("පෘතුග්‍රීසි");
							// tempWord.getOrigin().setLemmaFromMongo("පෘතුග්‍රීසි");
						} else if (wordPointers.get(j).getSynsetId() == 6) {
							pointerWordOri.setLemmaFromMongo("ලංදේසි");
							// tempWord.getOrigin().setLemmaFromMongo("ලංදේසි");
						}else if (wordPointers.get(j).getSynsetId() == 5) {
						pointerWordOri.setLemmaFromMongo("පාලි");
						// tempWord.getOrigin().setLemmaFromMongo("පෘතුග්‍රීසි");
						 } else if (wordPointers.get(j).getSynsetId() == 6) {
						pointerWordOri.setLemmaFromMongo("සංස්කෘත");
						// tempWord.getOrigin().setLemmaFromMongo("ලංදේසි");
						 }
						tempWord.setOrigin(pointerWordOri);
					} else if (wordPointers.get(j).getPointerType()
							.equals(MongoSinhalaPointerTyps.USAGE)) {
						SinhalaWordNetWord pointerWordUse = new SinhalaWordNetWord();

						if (wordPointers.get(j).getSynsetId() == 1) {
							pointerWordUse.setLemmaFromMongo("ලිඛිත");
							// tempWord.getUsage().setLemmaFromMongo("ලිඛිත");
						} else if (wordPointers.get(j).getSynsetId() == 2) {
							// System.out.println("in side if");
							pointerWordUse.setLemmaFromMongo("වාචික");
							// tempWord.getUsage().setLemmaFromMongo("වාචික");
						}
						else if (wordPointers.get(j).getSynsetId() == 3) {
							// System.out.println("in side if");
							pointerWordUse.setLemmaFromMongo("නොදනී");
							// tempWord.getUsage().setLemmaFromMongo("වාචික");
						}
						tempWord.setUsage(pointerWordUse);
					} else if (wordPointers.get(j).getPointerType()
							.equals(MongoSinhalaPointerTyps.DERIVATION_TYPE)) {
						SinhalaWordNetWord pointerWordDeri = new SinhalaWordNetWord();

						if (wordPointers.get(j).getSynsetId() == 1) {
							pointerWordDeri.setLemmaFromMongo("තත්සම");
							// tempWord.getOrigin().setLemmaFromMongo("තත්සම");
						} else if (wordPointers.get(j).getSynsetId() == 2) {
							// System.out.println("in side if");
							pointerWordDeri.setLemmaFromMongo("තත්භව");
							// tempWord.getOrigin().setLemmaFromMongo("තත්භව");
						}
						else if (wordPointers.get(j).getSynsetId() == 3) {
							// System.out.println("in side if");
							pointerWordDeri.setLemmaFromMongo("නොදනී");
							// tempWord.getOrigin().setLemmaFromMongo("තත්භව");
						}
						tempWord.setDerivationType(pointerWordDeri);
					}

					else if (wordPointers.get(j).getPointerType()
							.equals(MongoSinhalaPointerTyps.ROOT)) {
						SinhalaWordNetWord pointerWordRoot = new SinhalaWordNetWord();

						SynsetMongoDbHandler dbHandler = new SynsetMongoDbHandler();
						MongoSinhalaRoot root = dbHandler.findRootByID(String
								.valueOf(wordPointers.get(j).getSynsetIDasString()));
						if (root != null) {
							pointerWordRoot.setLemmaFromMongo(root.getWords().get(0)
									.getLemma());
							pointerWordRoot.setId(root.getWords().get(0)
									.getId());

							tempWord.setRoot(pointerWordRoot);
						}
					}
				}
				// System.out.println(tempWord.getLemma()+"in mongo convertor"+mongoWords.get(i).getLemma());
				updatedUiWords.add(tempWord);
			}
			List<MongoSinhalaSencePointer> sencePointerList = new ArrayList<MongoSinhalaSencePointer>();
			MongoSinhalaSencePointer genderPointer = new MongoSinhalaSencePointer();
			SinhalaWordNetWord genderWord = new SinhalaWordNetWord();
			try {
				sencePointerList = mongoAdj.getSencePointers();
				for (int k = 0; k < sencePointerList.size(); k++) {
					if (sencePointerList.get(k).getPointerType()
							.equals(MongoSinhalaPointerTyps.GENDER)) {

						if (sencePointerList.get(k).getSynsetId() == 1) {
							genderWord.setLemmaFromMongo("පුරුෂ");
							// tempWord.getOrigin().setLemmaFromMongo("තත්සම");
						} else if (sencePointerList.get(k).getSynsetId() == 2) {
							// System.out.println("in side if");
							genderWord.setLemmaFromMongo("ස්ත්‍රී");
							// tempWord.getOrigin().setLemmaFromMongo("තත්භව");
						} else if (sencePointerList.get(k).getSynsetId() == 3) {
							// System.out.println("in side if");
							genderWord.setLemmaFromMongo("නොසලකා හරින්න");
							// tempWord.getOrigin().setLemmaFromMongo("තත්භව");
						}

					}
				}

			} catch (Exception e) {
				System.out.println("exeption in gender pointer convetor");
			}
			// uiWords = updatedUiWords;

			String gloss = mongoAdj.getGloss();
			String[] parts = new String[2];
			parts[0] = "";
			parts[1] = "";
			parts = gloss.split("\\|");
			// System.out.println(gloss+"===="+parts[0]+"---glossss------"+parts[1]);
			AdjectiveSynset tempAdj;
			if (parts.length > 1) {

				tempAdj = new AdjectiveSynset(mongoAdj.getId(),
						adjSynset.getOffset(), parts[0], parts[1],
						updatedUiWords, genderWord);
			} else if (parts.length > 0) {
				tempAdj = new AdjectiveSynset(mongoAdj.getId(),
						adjSynset.getOffset(), parts[0], "", updatedUiWords,
						genderWord);
			} else {
				tempAdj = new AdjectiveSynset(mongoAdj.getId(),
						adjSynset.getOffset(), "", "", updatedUiWords,
						genderWord);
			}
			tempAdj.setCommentByMongo(mongoAdj.getComment());
			tempAdj.setRating(mongoAdj.getRating());
			tempAdj.setUserName(mongoAdj.getUserName());
			
			return tempAdj;

		} else {
			// NounSynset newnoun = new NounSynset();
			return adjSynset;
		}
	}

}
