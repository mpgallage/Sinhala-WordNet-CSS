package org.sinhala.wordnet.wordnetDB.model;

import java.util.Date;
import java.util.List;

import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetWord;
import org.sinhala.wordnet.wordnetDB.core.SynsetMongoDbHandler;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "noun")
public class MongoSinhalaNoun implements MongoSinhalaSynset {
	
	@Id
	private String id;
	String userName;
	Long eWNId;
	String SMDBId;
	List<MongoSinhalaWord> words;
	List<MongoSinhalaSencePointer> sencePointers;
	String gloss;
	String comment;
	String rating;
	Boolean evaluated = false;
	String evaluatedBy;
	Date date;
	
	
	public MongoSinhalaNoun(Long eWNId,List<MongoSinhalaWord> words,List<MongoSinhalaSencePointer> sencePointers,String gloss,String userName) {
		super();
		this.eWNId = eWNId;
		this.userName = userName;
		this.words = words;
		this.sencePointers = sencePointers;
		this.gloss = gloss;
		
	}

	@Override
	public String toString() {
		return "Synset [id=" + id + ", EWNId=" + eWNId +", userName=" + userName + ", SMDBId=" + SMDBId + ", words="+words+", sencePointerList="+sencePointers+", gloss="+gloss+"]";
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public Long getEWNId() {
		// TODO Auto-generated method stub
		return eWNId;
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public String getSMDBId() {
		// TODO Auto-generated method stub
		return SMDBId;
	}

	@Override
	public List<MongoSinhalaWord> getWords() {
		// TODO Auto-generated method stub
		return words;
	}

	@Override
	public List<MongoSinhalaSencePointer> getSencePointers() {
		// TODO Auto-generated method stub
		return sencePointers;
	}

	@Override
	public String getGloss() {
		// TODO Auto-generated method stub
		return gloss;
	}
	public Date getDate() {
		// TODO Auto-generated method stub
		return date;
	}
	
	public String getWordsAsString() {
		// TODO Auto-generated method stub
		String out = "";
		for(MongoSinhalaWord w : this.getWords()){
			out += w.getLemma() + ", ";
		}
		
		return out;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}
	public void setComment(String comment) {
		// TODO Auto-generated method stub
		this.comment = comment;
	}
	public void setRating(String rating) {
		// TODO Auto-generated method stub
		this.rating = rating;
	}
	public void setDate(Date date) {
		// TODO Auto-generated method stub
		this.date = date;
	}

	@Override
	public void SetEWNId(Long eWNId) {
		// TODO Auto-generated method stub
		this.eWNId = eWNId;
	}

	@Override
	public void setUserName(String userName) {
		// TODO Auto-generated method stub
		this.userName = userName;
	}

	@Override
	public void SetSMDBId(String SMDBId) {
		// TODO Auto-generated method stub
		this.SMDBId = SMDBId;
	}

	@Override
	public void SetWords(List<MongoSinhalaWord> words) {
		// TODO Auto-generated method stub
		this.words = words;
		
	}

	@Override
	public void SetSencePointers(List<MongoSinhalaSencePointer> sencePointers) {
		// TODO Auto-generated method stub
		this.sencePointers = sencePointers;
	}

	@Override
	public void SetGloss(String gloss) {
		// TODO Auto-generated method stub
		this.gloss = gloss;
	}

	@Override
	public void getRelatedSynsets(MongoSinhalaPointerTyps relation) {
		// TODO Auto-generated method stub
		SynsetMongoDbHandler synsetdb = new SynsetMongoDbHandler();
		synsetdb.findRelatedSynsetById(this.id, relation);
	}

	@Override
	public Boolean getEvaluated() {
		// TODO Auto-generated method stub
		return evaluated;
	}
	public String getEvaluatedBY() {
		// TODO Auto-generated method stub
		return evaluatedBy;
	}
	public String getComment() {
		// TODO Auto-generated method stub
		return comment;
	}
	public String getRating() {
		// TODO Auto-generated method stub
		return rating;
	}

	@Override
	public void SetEvaluated() {
		// TODO Auto-generated method stub
		evaluated = true;
	}
	public void SetEvaluatedBy(String evaluatedBY) {
		// TODO Auto-generated method stub
		this.evaluatedBy = evaluatedBY;
	}

}
