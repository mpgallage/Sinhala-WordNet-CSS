package org.sinhala.wordnet.css.model.wordnet;

import java.util.ArrayList;
import java.util.List;

import org.sinhala.wordnet.css.jwnl.WordNetDictionary;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;
import net.didion.jwnl.dictionary.Dictionary;

public class AdjectiveSynset extends SinhalaWordNetSynset{

	List<SinhalaWordNetSynset> similar;
	List<SinhalaWordNetSynset> attributes;
	List<SinhalaWordNetSynset> alsoSee;
	
	public AdjectiveSynset(String id, long offset, String definition,
			String example, SinhalaWordNetWord gender, List<SinhalaWordNetWord> words,
			List<SinhalaWordNetSynset> similar,
			List<SinhalaWordNetSynset> attributes,
			List<SinhalaWordNetSynset> alsoSee) {
		
		super(id, offset, definition, example, gender, words);
	}
	
	public AdjectiveSynset(Synset synset){
		super(synset);
	}
	
	public AdjectiveSynset(){
		super();
	}
	
	
	
	public AdjectiveSynset(String id, long offset, String definition,
			String example, List<SinhalaWordNetWord> words,SinhalaWordNetWord gender){
		super(id, offset, definition, example, words, gender);
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

	public List<SinhalaWordNetWord> getWords() {
		if(this.words!=null){
			
			return this.words;
		
		}
		else{
			Dictionary dict = WordNetDictionary.getInstance();
			Synset synset = null;
			try {
				synset = dict.getSynsetAt(POS.ADJECTIVE, this.getOffset());
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
			
			this.words = words;
			return words;
		}
	}
	
	public String getDefinition() {
		if(this.definition != null){
			return this.definition;
		}
		else{
		Dictionary dict = WordNetDictionary.getInstance();
		Synset synset = null;
		try {
			//synset = dict.getSynsetAt(arg0, arg1)
			synset = dict.getSynsetAt(POS.ADJECTIVE, this.getOffset());
		} catch (NumberFormatException e) {
			
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (JWNLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		String out = "";
		
		try{
			out = synset.getGloss().split(";")[0];
		} catch(ArrayIndexOutOfBoundsException e){
			//e.printStackTrace();
		}
		
		return out;
		}
	}
	
	
	public String getExample() {
		if(this.example != null){
			return this.example;
		}
		
		else{
		Dictionary dict = WordNetDictionary.getInstance();
		Synset synset = null;
		
		try {
			synset = dict.getSynsetAt(POS.ADJECTIVE, this.getOffset());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (JWNLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		String out = "";
		
		try{
			out = synset.getGloss().split(";")[1];
		} catch(ArrayIndexOutOfBoundsException e){
			//e.printStackTrace();
		}
		
		return out;
		}
	}
}
