package org.sinhala.wordnet.css.model.wordnet;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.sinhala.wordnet.css.jwnl.WordNetDictionary;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.dictionary.Dictionary;

public class SinhalaWordNetSynset {

	protected String id;
	protected long offset;
	protected String definition;
	protected String example;
	protected String userName;
	protected SinhalaWordNetWord gender;
	protected List<SinhalaWordNetWord> words;
	
	
	public SinhalaWordNetSynset(String id, long offset, String definition,
			String example, SinhalaWordNetWord gender, List<SinhalaWordNetWord> words) {
		super();
		this.id = id;
		this.offset = offset;
		this.definition = definition;
		this.example = example;
		this.gender = gender;
		this.words = words;
	}
	
	public SinhalaWordNetSynset(String id, long offset, String definition,
			String example, List<SinhalaWordNetWord> words,SinhalaWordNetWord gender){
		super();
		this.id = id;
		this.offset = offset;
		this.definition = definition;
		this.example = example;
		this.words = words;
		this.gender = gender;
	}
	
	public SinhalaWordNetSynset(Synset synset){
		this.offset = synset.getOffset();
	}
	public SinhalaWordNetSynset(SinhalaWordNetSynset synset){
		this.offset = synset.getOffset();
	}
	
	public SinhalaWordNetSynset(){
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
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
			synset = dict.getSynsetAt(POS.NOUN, this.getOffset());
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

	public void setDefinition(String definition) {
		byte ptext[] =null;
		//byte ptext1[] =null;
		String value=null;
		//lemma.getBytes()
		try {
			//ptext = lemma.getBytes();
			ptext = definition.getBytes("ISO8859_1");
			value = new String(ptext, Charset.forName("UTF-8"));
			//value = new String(ptext, "UTF-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(value != null){
		this.definition = value;
		}
		else{
			this.definition =definition;
		}
		
	}
	public void setDefinitionFromMongo(String definition){
		this.definition =definition;
	}

	public String getExample() {
		if(this.example != null){
			return this.example;
		}
		
		else{
		Dictionary dict = WordNetDictionary.getInstance();
		Synset synset = null;
		
		try {
			synset = dict.getSynsetAt(POS.NOUN, this.getOffset());
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

	public void setExample(String example) {
		byte ptext[] =null;
		//byte ptext1[] =null;
		String value=null;
		
		try {
			
			ptext = example.getBytes("ISO8859_1");
			value = new String(ptext, Charset.forName("UTF-8"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(value != null){
		this.example = value;
		}
		else{
			this.example =example;
		}
	}

	public SinhalaWordNetWord getGender() {
		// get from database
		return gender;
	}

	public void setGender(SinhalaWordNetWord gender) {
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
		for(SinhalaWordNetWord w : this.getWords()){
			out += w.getLemma() + ", ";
		}
		
		out = out.trim();
		out = out.substring(0, out.length()-1);
		
		return out;
	}
	
	public ArrayList<String> getWordArrayList(){
		ArrayList<String> out = new ArrayList<String>();
		for(SinhalaWordNetWord w : this.getWords()){
			out.add(w.getLemma());
		}
		return out;
	}
}
