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

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
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
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaAdjective;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaDerivationType;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaGender;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaNoun;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaOrigin;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaPointerTyps;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaRoot;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaSencePointer;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaSynset;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaUsage;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaVerb;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaWord;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaWordPointer;
import org.sinhala.wordnet.wordnetDB.model.User;

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
	}

	// finding a synset by its MongoDB ID
	public MongoSinhalaSynset findBySynsetMongoId(String findId, POS pos) {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		Query searchSynsetQuery1 = new Query(Criteria.where("_id").is(findId)); // MongoDB filter
		MongoSinhalaSynset foundSynset = null;
		if (pos.equals(POS.NOUN)) { // if we need noun synset
			List<MongoSinhalaNoun> collection = mongoOperation.find(
					searchSynsetQuery1, MongoSinhalaNoun.class);
			if (collection.size() > 0) {
				foundSynset = collection.get(collection.size() - 1);
			}
		} else if (pos.equals(POS.VERB)) { // if we need verb
			List<MongoSinhalaVerb> collection = mongoOperation.find(
					searchSynsetQuery1, MongoSinhalaVerb.class);
			if (collection.size() > 0) {
				foundSynset = collection.get(collection.size() - 1);
			}
		} else if (pos.equals(POS.ADJECTIVE)) { // if we need Adjective
			List<MongoSinhalaAdjective> collection = mongoOperation.find(
					searchSynsetQuery1, MongoSinhalaAdjective.class);
			if (collection.size() > 0) {
				foundSynset = collection.get(collection.size() - 1);
			}
		}
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
		MongoSinhalaSynset foundSynset = null;
		if (pos.equals(POS.NOUN)) { // if we need noun

			List<MongoSinhalaNoun> collection = mongoOperation.find(
					searchSynsetQuery1, MongoSinhalaNoun.class);
			if (collection.size() > 0) {
				foundSynset = collection.get(collection.size() - 1);
			}
		} else if (pos.equals(POS.VERB)) { // if we need verb

			List<MongoSinhalaVerb> collection = mongoOperation.find(
					searchSynsetQuery1, MongoSinhalaVerb.class);
			if (collection.size() > 0) {
				foundSynset = collection.get(collection.size() - 1);
			}
		} else if (pos.equals(POS.ADJECTIVE)) { // if we need adjective

			List<MongoSinhalaAdjective> collection = mongoOperation.find(
					searchSynsetQuery1, MongoSinhalaAdjective.class);
			if (collection.size() > 0) {
				foundSynset = collection.get(collection.size() - 1);
			}
		}
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
		MongoSinhalaRoot foundSynset = null;
		List<MongoSinhalaRoot> collection = mongoOperation.find(
				searchSynsetQuery1, MongoSinhalaRoot.class);
		if (collection.size() > 0) {
			foundSynset = collection.get(collection.size() - 1);
		}
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
		if (type.equals("evaluated")) {
			searchSynsetQuery1 = new Query(Criteria.where("evaluated").is(true));
		} else if (type.equals("notevaluated")) {
			searchSynsetQuery1 = new Query(Criteria.where("evaluated").ne(true));
		} else if (type.equals("all")) {
			searchSynsetQuery1 = new Query();
		}
		List<MongoSinhalaSynset> collection = new ArrayList<MongoSinhalaSynset>();

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
		return collection;
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

			return hm;
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
	}
	
}
