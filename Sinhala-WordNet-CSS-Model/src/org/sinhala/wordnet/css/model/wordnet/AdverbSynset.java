package org.sinhala.wordnet.css.model.wordnet;

import java.util.ArrayList;
import java.util.List;

import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;

public class AdverbSynset extends SinhalaWordNetSynset{
	
	public AdverbSynset(String id, long offset, String definition,
			String example, String gender, List<SinhalaWordNetWord> words){
		super(id, offset, definition, example, gender, words);
	}

	public AdverbSynset(Synset synset){
		super(synset);
		
		// assign words
		this.words = new ArrayList<SinhalaWordNetWord>();
		Word[] originalWords = synset.getWords();
		for(Word w : originalWords){
			this.words.add(new AdverbWord(w));
		}
	}
	
	public AdverbSynset(){
		super();
	}
	
}
