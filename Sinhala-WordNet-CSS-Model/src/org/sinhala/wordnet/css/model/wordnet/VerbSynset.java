package org.sinhala.wordnet.css.model.wordnet;

import java.util.ArrayList;
import java.util.List;

import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;

public class VerbSynset extends SinhalaWordNetSynset {

	List<SinhalaWordNetSynset> hypernyms;
	List<SinhalaWordNetSynset> troponyms;
	List<SinhalaWordNetSynset> entailments;
	List<SinhalaWordNetSynset> cause;

	public VerbSynset(String id, long offset, String definition,
			String example, String gender, List<SinhalaWordNetWord> words,
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
		
		// assign words
		this.words = new ArrayList<SinhalaWordNetWord>();
		Word[] originalWords = synset.getWords();
		for(Word w : originalWords){
			this.words.add(new VerbWord(w));
		}
	}
	
	public VerbSynset(){
		super();
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

}
