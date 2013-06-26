package org.sinhala.wordnet.css.model.wordnet;

import java.util.ArrayList;
import java.util.List;

import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;

public class NounSynset extends SinhalaWordNetSynset{

	List<SinhalaWordNetSynset> hypernyms;
	List<SinhalaWordNetSynset> hyponyms;
	List<SinhalaWordNetSynset> meronyms;
	List<SinhalaWordNetSynset> holonyms;
	List<SinhalaWordNetSynset> attributes;
	
	public NounSynset(String id, long offset, String definition,
			String example, String gender, List<SinhalaWordNetWord> words, List<SinhalaWordNetSynset> hypernyms, List<SinhalaWordNetSynset> hyponyms, List<SinhalaWordNetSynset> meronyms, List<SinhalaWordNetSynset> holonyms, List<SinhalaWordNetSynset> attributes){
		super(id, offset, definition, example, gender, words);
		this.hypernyms = hypernyms;
		this.hyponyms = hyponyms;
		this.meronyms = meronyms;
		this.holonyms = holonyms;
		this.attributes = attributes;
	}
	
	public NounSynset(Synset synset){
		super(synset);
		
		// assign words
		this.words = new ArrayList<SinhalaWordNetWord>();
		Word[] originalWords = synset.getWords();
		for(Word w : originalWords){
			this.words.add(new NounWord(w));
		}
		
	}

	public NounSynset(){
		
	}
	
	public List<SinhalaWordNetSynset> getHypernyms() {
		return hypernyms;
	}

	public void setHypernyms(List<SinhalaWordNetSynset> hypernyms) {
		this.hypernyms = hypernyms;
	}

	public List<SinhalaWordNetSynset> getHyponyms() {
		return hyponyms;
	}

	public void setHyponyms(List<SinhalaWordNetSynset> hyponyms) {
		this.hyponyms = hyponyms;
	}

	public List<SinhalaWordNetSynset> getMeronyms() {
		return meronyms;
	}

	public void setMeronyms(List<SinhalaWordNetSynset> meronyms) {
		this.meronyms = meronyms;
	}

	public List<SinhalaWordNetSynset> getHolonyms() {
		return holonyms;
	}

	public void setHolonyms(List<SinhalaWordNetSynset> holonyms) {
		this.holonyms = holonyms;
	}

	public List<SinhalaWordNetSynset> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<SinhalaWordNetSynset> attributes) {
		this.attributes = attributes;
	}

}
