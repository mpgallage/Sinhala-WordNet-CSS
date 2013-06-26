package org.sinhala.wordnet.css.model.wordnet;

import java.util.List;

import net.didion.jwnl.data.Synset;

public class SinhalaWordNetSynset {

	protected String id;
	protected long offset;
	protected String definition;
	protected String example;
	protected String gender;
	protected List<SinhalaWordNetWord> words;
	
	public SinhalaWordNetSynset(String id, long offset, String definition,
			String example, String gender, List<SinhalaWordNetWord> words) {
		super();
		this.id = id;
		this.offset = offset;
		this.definition = definition;
		this.example = example;
		this.gender = gender;
		this.words = words;
	}
	
	public SinhalaWordNetSynset(Synset synset){
		this.offset = synset.getOffset();
		try{
			this.definition = synset.getGloss().split(";")[0];
		} catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		}
		try{
			this.example = synset.getGloss().split(";")[1];
		} catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		}
	}
	
	public SinhalaWordNetSynset(){

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<SinhalaWordNetWord> getWords() {
		return words;
	}

	public void setWords(List<SinhalaWordNetWord> words) {
		this.words = words;
	}
	
	public String getWordsAsString(){
		String out = "";
		for(SinhalaWordNetWord w : this.words){
			out += w.getLemma() + ", ";
		}
		
		out = out.trim();
		out = out.substring(0, out.length()-1);
		
		return out;
	}
}
