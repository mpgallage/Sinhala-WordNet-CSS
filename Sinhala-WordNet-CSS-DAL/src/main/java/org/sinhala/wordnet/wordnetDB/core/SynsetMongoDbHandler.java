package org.sinhala.wordnet.wordnetDB.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.dictionary.Dictionary;

import org.eclipse.jdt.internal.compiler.lookup.MostSpecificExceptionMethodBinding;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import org.sinhala.wordnet.css.jwnl.WordNetDictionary;
import org.sinhala.wordnet.css.model.wordnet.AdjectiveSynset;
import org.sinhala.wordnet.css.model.wordnet.NounSynset;
import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetSynset;
import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetWord;
import org.sinhala.wordnet.css.model.wordnet.VerbSynset;
import org.sinhala.wordnet.wordnetDB.config.SpringMongoConfig;
import org.sinhala.wordnet.wordnetDB.config.SpringMongoConfig1;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaAdjective;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaDerivationType;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaGender;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaNoun;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaOrigin;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaPoinertTypeSemetric;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaPointerTyps;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaRoot;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaSencePointer;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaSynset;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaUsage;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaVerb;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaWord;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaWordPointer;
import org.sinhala.wordnet.wordnetDB.model.User;

import com.mongodb.Mongo;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

public class SynsetMongoDbHandler {

	// add synset function add synsets to mongo DB
	public void addSynset(SinhalaWordNetSynset synset) {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		
		SinhalaSynsetMongoSynsetConvertor ssmsc = new SinhalaSynsetMongoSynsetConvertor();

		MongoSinhalaSynset mongosynset = ssmsc.converttoMongoSynset(synset); // convert Sinhala Synset to MongoDB conpatible synset
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+5.30"));
		Date date = new Date();
		mongosynset.setDate(date);
		mongoOperation.save(mongosynset); // Save Synset in MongoDB
		System.out.println("saved");
		
		((AbstractApplicationContext) ctx).close();
	}
	
	
	public void addNewSynset(SinhalaWordNetSynset synset,Long perent) {
        
		POS pos = null;
		String pFile= null;
		if (synset instanceof NounSynset) {
			pos = POS.NOUN;
			pFile="n";
		}
		if (synset instanceof VerbSynset) {
			pos = POS.VERB;
			pFile="v";
		}
		if (synset instanceof AdjectiveSynset) {
			pos = POS.ADJECTIVE;
			pFile="adj";
		}
		
		
        ApplicationContext ctx = new AnnotationConfigApplicationContext(
                        SpringMongoConfig.class);
        
        MongoOperations mongoOperation = (MongoOperations) ctx
                        .getBean("mongoTemplate");
        
        SinhalaSynsetMongoSynsetConvertor ssmsc = new SinhalaSynsetMongoSynsetConvertor();

        MongoSinhalaSynset mongosynset = ssmsc.converttoMongoSynset(synset); // convert Sinhala Synset to MongoDB conpatible synset
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+5.30"));
        Date date = new Date();
        mongosynset.setDate(date);
        Long eWNIdMax = (long) 99999999;
        Long sMDBId = null;
        mongosynset.SetEWNId(null);
        Query query = new Query();
    	
    	query.with(new Sort(Sort.Direction.DESC, "eWNId"));
    	query.limit(1);
        MongoSinhalaNoun bigestSyn = mongoOperation.findOne(query, MongoSinhalaNoun.class);
        
        if(eWNIdMax < bigestSyn.getEWNId()){
        mongosynset.SetEWNId(bigestSyn.getEWNId()+1);
        
        }
        else{
        	mongosynset.SetEWNId(eWNIdMax+1);
        }
        mongosynset.SetSMDBId(mongosynset.getEWNId()-eWNIdMax);
        List<MongoSinhalaSencePointer> sPointerList = new ArrayList<MongoSinhalaSencePointer>();
        MongoSinhalaSencePointer sPointer = new MongoSinhalaSencePointer(pFile, perent, MongoSinhalaPointerTyps.HYPONYM);
        SynsetMongoDbHandler dbHandler = new SynsetMongoDbHandler();
        dbHandler.addrelations(perent, pos, mongosynset.getEWNId(), pFile, MongoSinhalaPointerTyps.HYPERNYM);
        
        sPointerList.add(sPointer);
        mongosynset.SetSencePointers(sPointerList);
        //System.out.println(mongosynset.toString());
        mongoOperation.save(mongosynset); // Save Synset in MongoDB
        System.out.println("saved");
        
        ((AbstractApplicationContext) ctx).close();
}
	
	public void addrelations(Long id,POS pos,Long rid,String pFile,MongoSinhalaPointerTyps pType){
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
                SpringMongoConfig.class);

		MongoOperations mongoOperation = (MongoOperations) ctx
                .getBean("mongoTemplate");
	SynsetMongoDbHandler dbHandler = new SynsetMongoDbHandler();
	MongoSinhalaSynset synset=	dbHandler.findBySynsetId(id, pos);
	System.out.println(synset);
	List<MongoSinhalaSencePointer> pList = synset.getSencePointers();
	MongoSinhalaSencePointer sPointer = new MongoSinhalaSencePointer(pFile, rid, pType);
	pList.add(sPointer);
	synset.SetSencePointers(pList);
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+5.30"));
    Date date = new Date();
    synset.setDate(date);
    synset.setId(null);
	mongoOperation.save(synset);
	((AbstractApplicationContext) ctx).close();
	
	
	}

	// Root is a specific type of synset so add root will add a synset to root collection
	public void addRoot(String lemma, String userName) {

		// @SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		SinhalaSynsetMongoSynsetConvertor ssmsc = new SinhalaSynsetMongoSynsetConvertor();
		List<MongoSinhalaWord> wordList = new ArrayList<MongoSinhalaWord>();
		wordList.add(new MongoSinhalaWord(lemma, "0", null)); // setting words
		MongoSinhalaRoot root = new MongoSinhalaRoot(wordList, "", userName); // set root Synset
		MongoSinhalaRoot anyRoot = findRootByLemma(lemma);
		if (anyRoot == null) { // if same root not avalable
			mongoOperation.save(root);
			
		}
		((AbstractApplicationContext) ctx).close();
	}

	public List<MongoSinhalaSynset> getRelatedOnes(Long id,MongoSinhalaPointerTyps pType,POS pos){
		//System.out.println("got to func");
		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		Query searchSynsetQuery1 = new Query();
		SynsetMongoDbHandler dbHandler = new SynsetMongoDbHandler();
		MongoSinhalaSynset latestSynset =  dbHandler.findBySynsetId(id, pos);
		//System.out.println("befor" +latestSynset);
		List<MongoSinhalaSynset> foundSynset = new ArrayList<MongoSinhalaSynset>();
		if(latestSynset!= null){
		List<MongoSinhalaSencePointer> pList = latestSynset.getSencePointers();
		
		for(MongoSinhalaSencePointer p:pList){
			//System.out.println(pType+"p"+p.toString()+"id"+id);
			if(p.getPointerType().equals(pType) && p.getSynsetId() > 99999999 ){
				//.out.println("in");
				POS rPos=null;
				if(p.getPointedFile().equals("n")){
					rPos=POS.NOUN;
				}
				foundSynset.add(dbHandler.findBySynsetId(p.getSynsetId(), rPos));
			}
		}
		}
	/*	
		searchSynsetQuery1.addCriteria(Criteria.where("sencePointers.synsetId").is(id).and("sencePointers.pointerType").is(pType).and("sencePointers.pointedFile").is("n").and("SMDBId").ne(null));
		List<MongoSinhalaNoun> nounList = mongoOperation.find(
				searchSynsetQuery1, MongoSinhalaNoun.class);
		boolean check = false;
		List<MongoSinhalaNoun> filteredNounList = new ArrayList<MongoSinhalaNoun>();
		for(MongoSinhalaNoun s: nounList){
			System.out.println("in"+s);
			List<MongoSinhalaSencePointer> sPointer = s.getSencePointers();
			for(int i=0;i<sPointer.size();i++){
				if(sPointer.get(i).getSynsetId().equals(id) && sPointer.get(i).getPointerType().equals(pType) && sPointer.get(i).getPointedFile().equals("n") ){
					check = true;
					break;
				}
			}
			filteredNounList.add(s);
			check = false;
		}*/
		/*for(MongoSinhalaSynset s:foundSynset){
			System.out.println(s);
		}*/
		((AbstractApplicationContext) ctx).close();
		return foundSynset;
	}
	
	// finding a synset by its MongoDB ID
	public MongoSinhalaSynset findBySynsetMongoId(String findId, POS pos) {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		Query searchSynsetQuery1 = new Query(Criteria.where("_id").is(findId)); // MongoDB filter
		searchSynsetQuery1.with(new Sort(Sort.Direction.DESC, "date"));
		searchSynsetQuery1.limit(1);
		MongoSinhalaSynset foundSynset = null;
		if (pos.equals(POS.NOUN)) { // if we need noun synset
			 foundSynset = mongoOperation.findOne(
					searchSynsetQuery1, MongoSinhalaNoun.class);
			
		} else if (pos.equals(POS.VERB)) { // if we need verb
			foundSynset = mongoOperation.findOne(
					searchSynsetQuery1, MongoSinhalaVerb.class);
			
		} else if (pos.equals(POS.ADJECTIVE)) { // if we need Adjective
			foundSynset = mongoOperation.findOne(
					searchSynsetQuery1, MongoSinhalaAdjective.class);
			
		}
		((AbstractApplicationContext) ctx).close();
		return foundSynset;
	}

	// finding a synset by its ID
	public MongoSinhalaSynset findBySynsetId(Long findId, POS pos) {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		Query searchSynsetQuery1 = new Query(Criteria.where("eWNId").is(findId));
		searchSynsetQuery1.with(new Sort(Sort.Direction.DESC, "date"));
		searchSynsetQuery1.limit(1);
		MongoSinhalaSynset foundSynset = null;
		if (pos.equals(POS.NOUN)) { // if we need noun

			foundSynset = mongoOperation.findOne(
					searchSynsetQuery1, MongoSinhalaNoun.class);
			
		} else if (pos.equals(POS.VERB)) { // if we need verb

			foundSynset = mongoOperation.findOne(
					searchSynsetQuery1, MongoSinhalaVerb.class);
			
		} else if (pos.equals(POS.ADJECTIVE)) { // if we need adjective

			foundSynset = mongoOperation.findOne(
					searchSynsetQuery1, MongoSinhalaAdjective.class);
			
		}
		((AbstractApplicationContext) ctx).close();
		return foundSynset;
	}
	

	// finding root by a lemma
	public MongoSinhalaRoot findRootByLemma(String lemma) {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		MongoSinhalaRoot foundSynset = null;
		List<MongoSinhalaRoot> collection = mongoOperation
				.findAll(MongoSinhalaRoot.class);
		List<MongoSinhalaRoot> returnList = new ArrayList<MongoSinhalaRoot>();

		try {
			for (MongoSinhalaRoot root : collection) {
				if (root.getWords().get(0).getLemma().equals(lemma)) {
					returnList.add(root);
				}
			}
		} catch (NullPointerException e) {
			// just to ignore errors
		}

		if (returnList.size() > 0) {
			foundSynset = returnList.get(returnList.size() - 1);
		}
		((AbstractApplicationContext) ctx).close();
		return foundSynset;
	}

	// finding root by its ID
	public MongoSinhalaRoot findRootByID(String id) {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		Query searchSynsetQuery1 = new Query(Criteria.where("_id").is(id));
		searchSynsetQuery1.with(new Sort(Sort.Direction.DESC, "date"));
		searchSynsetQuery1.limit(1);
		MongoSinhalaRoot foundSynset = null;
		foundSynset = mongoOperation.findOne(
				searchSynsetQuery1, MongoSinhalaRoot.class);
		
		((AbstractApplicationContext) ctx).close();
		return foundSynset;
	}

	// finding all root to auto completing roots
	public List<MongoSinhalaRoot> findAllRoots() {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		List<MongoSinhalaRoot> collection = mongoOperation
				.findAll(MongoSinhalaRoot.class);
		((AbstractApplicationContext) ctx).close();
		return collection;
	}

	// find synsets by pos and a request type to show in evaluator page
	@SuppressWarnings("null")
	public List<MongoSinhalaSynset> findSynsets(POS pos, String type) {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		Query searchSynsetQuery1 = null;
		List<MongoSinhalaSynset> collection = new ArrayList<MongoSinhalaSynset>();
		List<MongoSinhalaSynset> finalCollection = new ArrayList<MongoSinhalaSynset>();
		HashMap<Long,MongoSinhalaSynset> nevList = new HashMap<Long,MongoSinhalaSynset>();
		HashMap<Long,MongoSinhalaSynset> evLatest = new HashMap<Long,MongoSinhalaSynset>();
		HashMap<Long,MongoSinhalaSynset> allLatest = new HashMap<Long,MongoSinhalaSynset>();
		
		
		//if (type.equals("evaluated")) {
			searchSynsetQuery1 = new Query(Criteria.where("evaluated").is(true));
			searchSynsetQuery1.with(new Sort(Sort.Direction.ASC, "date"));
			
			if (pos == POS.NOUN) {
				List<MongoSinhalaNoun> nounList = mongoOperation.find(
						searchSynsetQuery1, MongoSinhalaNoun.class);
				for (MongoSinhalaNoun s : nounList) {
					
					evLatest.put(s.getEWNId(), s);

				}
			} else if (pos == POS.VERB) {
				List<MongoSinhalaVerb> verbList = mongoOperation.find(
						searchSynsetQuery1, MongoSinhalaVerb.class);
				for (MongoSinhalaVerb s : verbList) {
					evLatest.put(s.getEWNId(), s);
					
				}

			} else if (pos == POS.ADJECTIVE) {
				List<MongoSinhalaAdjective> adjList = mongoOperation.find(
						searchSynsetQuery1, MongoSinhalaAdjective.class);
				for (MongoSinhalaAdjective s : adjList) {
					evLatest.put(s.getEWNId(), s);
				}
			}
			
		//}else if (type.equals("notevaluated")) {
			searchSynsetQuery1 = new Query();
			searchSynsetQuery1.with(new Sort(Sort.Direction.DESC, "date"));
			
			if (pos == POS.NOUN) {
				List<MongoSinhalaNoun> nounList = mongoOperation.find(
						searchSynsetQuery1, MongoSinhalaNoun.class);
				for (MongoSinhalaNoun s : nounList) {

					MongoSinhalaSynset tempsyn = evLatest.get(s.getEWNId());
					if(tempsyn != null && s.getDate() != null){
						
						if(tempsyn.getDate().before(s.getDate())){
							collection.add(s);
						}
					}
					else{
					nevList.put(s.getEWNId(), s);
					collection.add(s);
					}
					
					

				}
			} else if (pos == POS.VERB) {
				List<MongoSinhalaVerb> verbList = mongoOperation.find(
						searchSynsetQuery1, MongoSinhalaVerb.class);
				for (MongoSinhalaVerb s : verbList) {
					MongoSinhalaSynset tempsyn = evLatest.get(s.getEWNId());
					if(tempsyn != null){
						if(tempsyn.getDate().before(s.getDate())){
							collection.add(s);
						}
					}
					else{
					nevList.put(s.getEWNId(), s);
					collection.add(s);
					}

				}

			} else if (pos == POS.ADJECTIVE) {
				List<MongoSinhalaAdjective> adjList = mongoOperation.find(
						searchSynsetQuery1, MongoSinhalaAdjective.class);
				for (MongoSinhalaAdjective s : adjList) {

					MongoSinhalaSynset tempsyn = evLatest.get(s.getEWNId());
					if(tempsyn != null){
						if(tempsyn.getDate().before(s.getDate())){
							collection.add(s);
						}
					}
					else{
					nevList.put(s.getEWNId(), s);
					collection.add(s);
					}

				}
			}
	/*	// }else if (type.equals("all")) {
			searchSynsetQuery1 = new Query();
			searchSynsetQuery1.with(new Sort(Sort.Direction.DESC, "date"));
			
			if (pos == POS.NOUN) {
				List<MongoSinhalaNoun> nounList = mongoOperation.find(
						searchSynsetQuery1, MongoSinhalaNoun.class);
				for (MongoSinhalaNoun s : nounList) {

					collection.add(s);

				}
			} else if (pos == POS.VERB) {
				List<MongoSinhalaVerb> verbList = mongoOperation.find(
						searchSynsetQuery1, MongoSinhalaVerb.class);
				for (MongoSinhalaVerb s : verbList) {

					collection.add(s);

				}

			} else if (pos == POS.ADJECTIVE) {
				List<MongoSinhalaAdjective> adjList = mongoOperation.find(
						searchSynsetQuery1, MongoSinhalaAdjective.class);
				for (MongoSinhalaAdjective s : adjList) {

					collection.add(s);

				}
			}
		//}*/
		if(type.equals("evaluated")){
			Iterator it = evLatest.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        finalCollection.add((MongoSinhalaSynset) pairs.getValue());
		    }
		}
		
		else if(type.equals("notevaluated")){
			  finalCollection = collection;
		    
		}
		
		if(type.equals("all")){
			Iterator it = evLatest.entrySet().iterator();
			finalCollection = collection;
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        finalCollection.add((MongoSinhalaSynset) pairs.getValue());
		    }
		    
		}
		
		((AbstractApplicationContext) ctx).close();
		return finalCollection;
	}

	// finding synsets by lemma and pos to impliment search through synsets in croudsourcing system
	public HashMap<Long, Long> findSynsetIDByLemma(String word, POS pos) {

		Collection<Long> ewnidList = null;
		HashMap<Long, Long> hm = new HashMap<Long, Long>();

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		Query searchSynsetQuery1 = new Query(Criteria.where("words.lemma")
				.regex(word));
		if (pos.equals(POS.NOUN)) {
			List<MongoSinhalaNoun> nounCollection = null;

			nounCollection = mongoOperation.find(searchSynsetQuery1,
					MongoSinhalaNoun.class);
			

			for (MongoSinhalaNoun s : nounCollection) {
				
				Long id = s.getEWNId();
				hm.put(id, id);
				
			}

			

		}
		if (pos.equals(POS.VERB)) {
			List<MongoSinhalaVerb> verbCollection = null;

			verbCollection = mongoOperation.find(searchSynsetQuery1,
					MongoSinhalaVerb.class);

			for (MongoSinhalaVerb s : verbCollection) {
				hm.put(s.getEWNId(), s.getEWNId());
			}

			ewnidList = hm.values();
		}
		if (pos.equals(POS.ADJECTIVE)) {
			List<MongoSinhalaAdjective> adjCollection = null;

			adjCollection = mongoOperation.find(searchSynsetQuery1,
					MongoSinhalaAdjective.class);

			for (MongoSinhalaAdjective s : adjCollection) {
				hm.put(s.getEWNId(), s.getEWNId());
			}

			ewnidList = hm.values();

		}
		((AbstractApplicationContext) ctx).close();
		return hm;
	}

	// finding synsets by lemma and pos to impliment search through synsets in croudsourcing system
		public HashMap<Long, MongoSinhalaSynset> findSynsetsByLemma(String word, POS pos) {

			Collection<Long> ewnidList = null;
			HashMap<Long, MongoSinhalaSynset> hm = new HashMap<Long, MongoSinhalaSynset>();

			@SuppressWarnings("resource")
			ApplicationContext ctx = new AnnotationConfigApplicationContext(
					SpringMongoConfig.class);
			MongoOperations mongoOperation = (MongoOperations) ctx
					.getBean("mongoTemplate");
			Query searchSynsetQuery1 = new Query(Criteria.where("words.lemma")
					.regex(word));
			if (pos.equals(POS.NOUN)) {
				List<MongoSinhalaNoun> nounCollection = null;

				nounCollection = mongoOperation.find(searchSynsetQuery1,
						MongoSinhalaNoun.class);
				
			

				for (MongoSinhalaSynset s : nounCollection) {
					Long id = s.getEWNId();
					hm.put(id, s);
					
				}

				

			}
			if (pos.equals(POS.VERB)) {
				List<MongoSinhalaVerb> verbCollection = null;

				verbCollection = mongoOperation.find(searchSynsetQuery1,
						MongoSinhalaVerb.class);

				for (MongoSinhalaSynset s : verbCollection) {
					hm.put(s.getEWNId(), s);
				}

				
			}
			if (pos.equals(POS.ADJECTIVE)) {
				List<MongoSinhalaAdjective> adjCollection = null;

				adjCollection = mongoOperation.find(searchSynsetQuery1,
						MongoSinhalaAdjective.class);

				for (MongoSinhalaSynset s : adjCollection) {
					hm.put(s.getEWNId(), s);
				}

				

			}
			((AbstractApplicationContext) ctx).close();
			return hm;
		}
		public void deleteRelation(Long sID,POS sPos,Long rID,String rPos, MongoSinhalaPointerTyps rPointerType ){
			ApplicationContext ctx = new AnnotationConfigApplicationContext(
					SpringMongoConfig.class);
			MongoOperations mongoOperation = (MongoOperations) ctx
					.getBean("mongoTemplate");
			
			SynsetMongoDbHandler dbHandler =  new SynsetMongoDbHandler();
			MongoSinhalaSynset latestSynset = dbHandler.findBySynsetId(sID, sPos);
			List<MongoSinhalaSencePointer> sPointerList = latestSynset.getSencePointers();
			List<MongoSinhalaSencePointer> newsPointerList = new ArrayList<MongoSinhalaSencePointer>();
			for(int i=0;i<sPointerList.size();i++){
				//System.out.println(sPointerList.get(i).getSynsetId()+"="+rID+"="+sPointerList.get(i).getPointerType()+"="+rPointerType+"="+sPointerList.get(i).getPointedFile()+"="+rPos);
				if(sPointerList.get(i).getSynsetId().equals(rID) && sPointerList.get(i).getPointerType().equals(rPointerType)  && sPointerList.get(i).getPointedFile().equals(rPos)){
					
				}
				else{
					newsPointerList.add(sPointerList.get(i));
				}
			}
			latestSynset.SetSencePointers(newsPointerList);
			latestSynset.setId(null);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+5.30"));
		    Date date = new Date();
		    latestSynset.setDate(date);
			mongoOperation.save(latestSynset);
			((AbstractApplicationContext) ctx).close();
			
		}
		
	public void addSencePointers(Long id,POS pos,MongoSinhalaPointerTyps pType,List<Long> ids,List<String> poses){
		
		//System.out.println("id "+id+" pos "+pos+" ptype "+pType+" pointers "+ids.toString()+" pos "+poses.toString());
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		boolean check = false;
		SynsetMongoDbHandler dbHandler =  new SynsetMongoDbHandler();
		POS symPos = null;
		POS deletedPos = null;
		MongoSinhalaPointerTyps detetePointer = null;
		MongoSinhalaSynset latestSynset = dbHandler.findBySynsetId(id, pos);
		
		MongoSinhalaPoinertTypeSemetric checkSemetric = new MongoSinhalaPoinertTypeSemetric();
		List<MongoSinhalaSencePointer> sPointerList = latestSynset.getSencePointers();
		List<MongoSinhalaSencePointer> newsPointerList = new ArrayList<MongoSinhalaSencePointer>();
		String pFile=null;
		boolean isDelete = false;
		
		String symPointedFile = null;
		if(pos.equals(POS.NOUN)){
			symPointedFile = "n";
		}
		else if(pos.equals(POS.VERB)){
			symPointedFile = "v";
		}
		else if(pos.equals(POS.ADJECTIVE)){
			symPointedFile = "adj";
		}
		else if(pos.equals(POS.ADVERB)){
			symPointedFile = "adv";
		}
		
		
		
		for(int i = 0 ;i<sPointerList.size();i++){
			
			if(sPointerList.get(i).getPointerType() != pType){
				//System.out.println("test");
				newsPointerList.add(sPointerList.get(i));
			}
			else{
				isDelete = true;
			for(int n=0;n<ids.size();n++){
				if(poses.get(n).toString().equals("noun")){
					pFile="n";
					
				}
				else if(poses.get(n).toString().equals("verb")){
					pFile="v";
					
				}
				else if(poses.get(n).toString().equals("adj")){
					pFile="adj";
					
				}
				else if(poses.get(n).toString().equals("adv")){
					pFile="adv";
					
				}
			if(sPointerList.get(i).getSynsetId().equals(ids.get(n)) && sPointerList.get(i).getPointedFile().equals(pFile) ){
				
				isDelete = false;
			}
				
			}
			if(sPointerList.get(i).getPointedFile().equals("n")){
				deletedPos = POS.NOUN;
				
			}
			else if(sPointerList.get(i).getPointedFile().equals("v")){
				deletedPos = POS.VERB;
			}
			else if(sPointerList.get(i).getPointedFile().equals("adj")){
				deletedPos = POS.ADJECTIVE;
			}
			else if(sPointerList.get(i).getPointedFile().equals("adv")){
				deletedPos = POS.ADVERB;
			}
			detetePointer = checkSemetric.getSymetric(sPointerList.get(i).getPointerType());
			if(isDelete){
			dbHandler.deleteRelation(sPointerList.get(i).getSynsetId(),deletedPos,id,symPointedFile,detetePointer);
			}
			}
		}
		
		for(int i=0;i<ids.size();i++){
		MongoSinhalaSencePointer sPointer = new MongoSinhalaSencePointer();
		sPointer.setPointerType(pType);
		
		
		
		sPointer.setSynsetId(ids.get(i));
		
		if(poses.get(i).toString().equals("noun")){
			sPointer.setPointedFile("n");
			symPos = POS.NOUN;
		}
		else if(poses.get(i).toString().equals("verb")){
			sPointer.setPointedFile("v");
			symPos = POS.VERB;
		}
		else if(poses.get(i).toString().equals("adj")){
			sPointer.setPointedFile("adj");
			symPos = POS.ADJECTIVE;
		}
		else if(poses.get(i).toString().equals("adv")){
			sPointer.setPointedFile("adv");
			symPos = POS.ADVERB;
		}
		newsPointerList.add(sPointer);
		
		
		Long rSynsetId = ids.get(i);
		MongoSinhalaPointerTyps symPointerType = checkSemetric.getSymetric(pType);
		if(symPointerType!= null){
		MongoSinhalaSynset symSynset = dbHandler.findBySynsetId(rSynsetId, symPos);
		MongoSinhalaSencePointer symSencePointer = new MongoSinhalaSencePointer();
		List<MongoSinhalaSencePointer> symPointerList = new ArrayList<MongoSinhalaSencePointer>();
		symSencePointer.setPointerType(symPointerType);
		symSencePointer.setSynsetId(id);

		symSencePointer.setPointedFile(symPointedFile);
		symPointerList = symSynset.getSencePointers();
		check = false;
		if(symPointerList!= null){
		for(int k=0;k<symPointerList.size();k++){
			if(symPointerList.get(k).getSynsetId().equals(id)  && symPointerList.get(k).getPointedFile().equals(symPointedFile) && symPointerList.get(k).getPointerType().equals(symPointerType)){
				check = true;
			}
		}
		}
		if(!check){
			symPointerList.add(symSencePointer);
		}
		
		symSynset.SetSencePointers(symPointerList);
		symSynset.setId(null);
		mongoOperation.save(symSynset);
		}
		}
		latestSynset.SetSencePointers(newsPointerList);
		latestSynset.setId(null);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+5.30"));
	    Date date = new Date();
	    latestSynset.setDate(date);
		mongoOperation.save(latestSynset);
		
		
		((AbstractApplicationContext) ctx).close();
		
		
	}
	
	public void addGenders(){
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		List<MongoSinhalaWordPointer> wordPointerList = new ArrayList<MongoSinhalaWordPointer>();
		MongoSinhalaWord word = new MongoSinhalaWord("නොසලකා හරින්න", "0", wordPointerList);
		List<MongoSinhalaWord> words = new ArrayList<MongoSinhalaWord>();
		words.add(word);
		MongoSinhalaGender gender = new MongoSinhalaGender(words, "neglect");
		mongoOperation.save(gender);
		((AbstractApplicationContext) ctx).close();
	}
	public void addDerivationTypes(){
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		
		List<MongoSinhalaWordPointer> wordPointerList = new ArrayList<MongoSinhalaWordPointer>();
		MongoSinhalaWord word = new MongoSinhalaWord("තත්සම", "0", wordPointerList);
		List<MongoSinhalaWord> words = new ArrayList<MongoSinhalaWord>();
		words.add(word);
		MongoSinhalaDerivationType deri = new MongoSinhalaDerivationType(words, "");
		mongoOperation.save(deri);
		word = new MongoSinhalaWord("තත්භව", "0", wordPointerList);
		words = new ArrayList<MongoSinhalaWord>();
		words.add(word);
		deri = new MongoSinhalaDerivationType(words, "");
		mongoOperation.save(deri);
		word = new MongoSinhalaWord("නොදනී", "0", wordPointerList);
		words = new ArrayList<MongoSinhalaWord>();
		words.add(word);
		deri = new MongoSinhalaDerivationType(words, "");
		mongoOperation.save(deri);
		((AbstractApplicationContext) ctx).close();
	}
	public void addOrigin(){
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		List<MongoSinhalaWordPointer> wordPointerList = new ArrayList<MongoSinhalaWordPointer>();
		MongoSinhalaWord word = new MongoSinhalaWord("නොදනී", "0", wordPointerList);
		List<MongoSinhalaWord> words = new ArrayList<MongoSinhalaWord>();
		words.add(word);
		MongoSinhalaOrigin origin = new MongoSinhalaOrigin(words, "");
		mongoOperation.save(origin);
		word = new MongoSinhalaWord("හින්දි", "0", wordPointerList);
		words = new ArrayList<MongoSinhalaWord>();
		words.add(word);
		origin = new MongoSinhalaOrigin(words, "");
		mongoOperation.save(origin);
		word = new MongoSinhalaWord("දෙමළ", "0", wordPointerList);
		words = new ArrayList<MongoSinhalaWord>();
		words.add(word);
		origin = new MongoSinhalaOrigin(words, "");
		mongoOperation.save(origin);
		word = new MongoSinhalaWord("ඉංග්‍රීසි", "0", wordPointerList);
		words = new ArrayList<MongoSinhalaWord>();
		words.add(word);
		origin = new MongoSinhalaOrigin(words, "");
		mongoOperation.save(origin);
		word = new MongoSinhalaWord("පෘතුග්‍රීසි", "0", wordPointerList);
		words = new ArrayList<MongoSinhalaWord>();
		words.add(word);
		origin = new MongoSinhalaOrigin(words, "");
		mongoOperation.save(origin);
		word = new MongoSinhalaWord("ලංදේසි", "0", wordPointerList);
		words = new ArrayList<MongoSinhalaWord>();
		words.add(word);
		origin = new MongoSinhalaOrigin(words, "");
		mongoOperation.save(origin);
		word = new MongoSinhalaWord("පාලි", "0", wordPointerList);
		words = new ArrayList<MongoSinhalaWord>();
		words.add(word);
		origin = new MongoSinhalaOrigin(words, "");
		mongoOperation.save(origin);
		word = new MongoSinhalaWord("සංස්කෘත", "0", wordPointerList);
		words = new ArrayList<MongoSinhalaWord>();
		words.add(word);
		origin = new MongoSinhalaOrigin(words, "");
		mongoOperation.save(origin);
		((AbstractApplicationContext) ctx).close();
	}
	
}
