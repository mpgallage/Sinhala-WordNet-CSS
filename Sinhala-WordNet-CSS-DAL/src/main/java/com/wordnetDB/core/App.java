package com.wordnetDB.core;





public class App {

	public static void main(String[] args) {
		
		
		
		SynsetMongoDbHandler synsetdb = new SynsetMongoDbHandler();
		//SinhalaSynset sinhalasynset = synsetdb.findBylemma("ත�?ත්ත�?123456");
		//sinhalasynset.getRelatedSynsets("hypernym");
		synsetdb.add();
		//synsetdb.findAll();
		//synsetdb.findRelatedSynsetById("51c942fefd1dcb1c0b37469d", "hypernym");
		/*

		// For XML
		//ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");

		// For Annotation
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		
		//User user = new User("mkyong", "password123");
		WordPointer wordPointer1 = new WordPointer("123", "456", "meronym");
		WordPointer wordPointer2 = new WordPointer("789", "741", "hplonomy");
		List<WordPointer> wordPointerList = new ArrayList<WordPointer>();
		wordPointerList.add(wordPointer1);
		wordPointerList.add(wordPointer2);
		Word word1 = new Word("ත�?ත්ත�?", "1", wordPointerList);
		WordPointer wordPointer3 = new WordPointer("abc", "def", "seealso");
		WordPointer wordPointer4 = new WordPointer("asd", "hjk", "derivedfrom");
		List<WordPointer> wordPointerList1 = new ArrayList<WordPointer>();
		wordPointerList1.add(wordPointer3);
		wordPointerList1.add(wordPointer4);
		Word word2 = new Word("පිය�?", "2", wordPointerList1);
		List<Word> wordList = new ArrayList<Word>();
		wordList.add(word1);
		wordList.add(word2);
		List<SencePointer> sencePointerList = new ArrayList<SencePointer>();
		SencePointer sencePointer1 = new SencePointer("123456", "hypernym");
		SencePointer sencePointer2 = new SencePointer("789123", "attribute");
		sencePointerList.add(sencePointer1);
		sencePointerList.add(sencePointer2);
		
		Synset synset = new Synset("123456789",wordList,sencePointerList,"ත�?ත්තට සම�?න වචන");
		// save
		//mongoOperation.save(user);
		//mongoOperation.save(synset);
		// now user object got the created id.
		//System.out.println("1. user : " + user);
		System.out.println("1. synset : " + synset);
		// query to search user
		//Query searchUserQuery = new Query(Criteria.where("username").is("mkyong"));
		Query searchSynsetQuery = new Query(Criteria.where("words.wordPointerList.pointerType").is("meronym"));
		
		
		// find the saved user again.
		//User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
		Synset savedSynset = mongoOperation.findOne(searchSynsetQuery, Synset.class);
		
		//System.out.println("2. find - savedUser : " + savedUser);
		System.out.println("2. find - savedsynset : " + savedSynset);

		// update password
		/*mongoOperation.updateFirst(searchUserQuery, Update.update("password", "new password"),
				User.class);

		// find the updated user object
		User updatedUser = mongoOperation.findOne(
				new Query(Criteria.where("username").is("mkyong")), User.class);

		System.out.println("3. updatedUser : " + updatedUser);

		// delete
		mongoOperation.remove(searchUserQuery, User.class);

		// List, it should be empty now.
		List<User> listUser = mongoOperation.findAll(User.class);
		System.out.println("4. Number of user = " + listUser.size());*/

	}

}