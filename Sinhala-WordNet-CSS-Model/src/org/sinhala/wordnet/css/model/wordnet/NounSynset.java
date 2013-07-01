package org.sinhala.wordnet.css.model.wordnet;

import java.util.ArrayList;
import java.util.List;

import org.sinhala.wordnet.css.jwnl.WordNetDictionary;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;
import net.didion.jwnl.dictionary.Dictionary;

public class NounSynset extends SinhalaWordNetSynset{

	List<SinhalaWordNetSynset> hypernyms;
	List<SinhalaWordNetSynset> hyponyms;
	List<SinhalaWordNetSynset> meronyms;
	List<SinhalaWordNetSynset> holonyms;
	List<SinhalaWordNetSynset> attributes;
	
	public NounSynset(String id, long offset, String definition,
			String example, SinhalaWordNetWord gender, List<SinhalaWordNetWord> words, List<SinhalaWordNetSynset> hypernyms, List<SinhalaWordNetSynset> hyponyms, List<SinhalaWordNetSynset> meronyms, List<SinhalaWordNetSynset> holonyms, List<SinhalaWordNetSynset> attributes){
		super(id, offset, definition, example, gender, words);
		this.hypernyms = hypernyms;
		this.hyponyms = hyponyms;
		this.meronyms = meronyms;
		this.holonyms = holonyms;
		this.attributes = attributes;
	}
	
	public NounSynset(Synset synset){
		super(synset);
	}
	
	public NounSynset(){
		super();
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
	
	public List<SinhalaWordNetWord> getWords() {
		
		Dictionary dict = WordNetDictionary.getInstance();
		Synset synset = null;
		try {
			synset = dict.getSynsetAt(POS.NOUN, this.getOffset());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JWNLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<SinhalaWordNetWord> words = new ArrayList<SinhalaWordNetWord>();
		Word[] originalWords = synset.getWords();
		for(Word w : originalWords){
			words.add(new NounWord(w));
		}
		return words;
	}

}
