package org.sinhala.wordnet.css.model.wordnet;

import java.util.ArrayList;
import java.util.List;

import org.sinhala.wordnet.css.jwnl.WordNetDictionary;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;
import net.didion.jwnl.dictionary.Dictionary;

public class VerbSynset extends SinhalaWordNetSynset {

	List<SinhalaWordNetSynset> hypernyms;
	List<SinhalaWordNetSynset> troponyms;
	List<SinhalaWordNetSynset> entailments;
	List<SinhalaWordNetSynset> cause;

	public VerbSynset(String id, long offset, String definition,
			String example, SinhalaWordNetWord gender, List<SinhalaWordNetWord> words,
			List<SinhalaWordNetSynset> hypernyms,
			List<SinhalaWordNetSynset> troponyms,
			List<SinhalaWordNetSynset> entailments,
			List<SinhalaWordNetSynset> cause) {
			
		super(id, offset, definition, example, gender, words);
		this.hypernyms = hypernyms;
		this.troponyms = troponyms;
		this.entailments = entailments;
		this.cause = cause;
	}
	
	public VerbSynset(Synset synset){
		super(synset);
	}

	public List<SinhalaWordNetSynset> getHypernyms() {
		return hypernyms;
	}

	public void setHypernyms(List<SinhalaWordNetSynset> hypernyms) {
		this.hypernyms = hypernyms;
	}

	public List<SinhalaWordNetSynset> getTroponyms() {
		return troponyms;
	}

	public void setTroponyms(List<SinhalaWordNetSynset> troponyms) {
		this.troponyms = troponyms;
	}

	public List<SinhalaWordNetSynset> getEntailments() {
		return entailments;
	}

	public void setEntailments(List<SinhalaWordNetSynset> entailments) {
		this.entailments = entailments;
	}

	public List<SinhalaWordNetSynset> getCause() {
		return cause;
	}

	public void setCause(List<SinhalaWordNetSynset> cause) {
		this.cause = cause;
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
			words.add(new VerbWord(w));
		}
		return words;
	}
}
