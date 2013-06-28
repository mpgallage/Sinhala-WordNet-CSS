package org.sinhala.wordnet.css.model.wordnet;

import net.didion.jwnl.data.Word;

public class NounWord extends SinhalaWordNetWord{
	
	public NounWord(String id, String lemma,
			SinhalaWordNetWord antonym, SinhalaWordNetWord root,
			SinhalaWordNetWord origin, SinhalaWordNetWord usage,
			SinhalaWordNetWord derivationType){
		
		super(id, lemma, antonym, root, origin, usage, derivationType);
	}
	
	public NounWord(Word word){
		super(word);
		//do extra variable initialization
	}
	
	public NounWord(){
		super();
	}

}
