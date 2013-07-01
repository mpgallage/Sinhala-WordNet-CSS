package org.sinhala.wordnet.css.model.wordnet;

import java.util.ArrayList;
import java.util.List;

import org.sinhala.wordnet.css.jwnl.WordNetDictionary;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;
import net.didion.jwnl.dictionary.Dictionary;

public class AdverbSynset extends SinhalaWordNetSynset{
	
	public AdverbSynset(String id, long offset, String definition,
			String example, SinhalaWordNetWord gender, List<SinhalaWordNetWord> words){
		super(id, offset, definition, example, gender, words);
	}

	public AdverbSynset(Synset synset){
		super(synset);
	}
	

	public List<SinhalaWordNetWord> getWords() {
		
		Dictionary dict = WordNetDictionary.getInstance();
		Synset synset = null;
		try {
			synset = dict.getSynsetAt(POS.NOUN, Long.parseLong(id));
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
			words.add(new AdverbWord(w));
		}
		return words;
	}

}
