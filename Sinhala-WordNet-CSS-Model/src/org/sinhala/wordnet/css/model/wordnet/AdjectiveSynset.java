package org.sinhala.wordnet.css.model.wordnet;

import java.util.ArrayList;
import java.util.List;

import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;

public class AdjectiveSynset extends SinhalaWordNetSynset{

	List<SinhalaWordNetSynset> similar;
	List<SinhalaWordNetSynset> attributes;
	List<SinhalaWordNetSynset> alsoSee;
	
	public AdjectiveSynset(String id, long offset, String definition,
			String example, String gender, List<SinhalaWordNetWord> words,
			List<SinhalaWordNetSynset> similar,
			List<SinhalaWordNetSynset> attributes,
			List<SinhalaWordNetSynset> alsoSee) {
		
		super(id, offset, definition, example, gender, words);
		this.similar = similar;
		this.attributes = attributes;
		this.alsoSee = alsoSee;
	}
	
	public AdjectiveSynset(Synset synset){
		super(synset);
		
		// assign words
		this.words = new ArrayList<SinhalaWordNetWord>();
		Word[] originalWords = synset.getWords();
		for(Word w : originalWords){
			this.words.add(new AdjectiveWord(w));
		}
		
		//
	}
	
	public AdjectiveSynset(){
		super();
	}

	public List<SinhalaWordNetSynset> getSimilar() {
		return similar;
	}

	public void setSimilar(List<SinhalaWordNetSynset> similar) {
		this.similar = similar;
	}

	public List<SinhalaWordNetSynset> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<SinhalaWordNetSynset> attributes) {
		this.attributes = attributes;
	}

	public List<SinhalaWordNetSynset> getAlsoSee() {
		return alsoSee;
	}

	public void setAlsoSee(List<SinhalaWordNetSynset> alsoSee) {
		this.alsoSee = alsoSee;
	}

	
}
