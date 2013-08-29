package org.sinhala.wordnet.css.model.wordnet;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.Pointer;
import net.didion.jwnl.data.PointerTarget;
import net.didion.jwnl.data.PointerType;
import net.didion.jwnl.data.Word;

public class SinhalaWordNetWord {

	protected Word word;

	protected String id;
	protected long synsetOffset;
	protected String lemma;
	protected SinhalaWordNetWord antonym;
	protected SinhalaWordNetWord root;
	protected SinhalaWordNetWord origin;
	protected SinhalaWordNetWord usage;
	protected SinhalaWordNetWord derivationType;
	
	public static boolean antoIsSet =false;

	public SinhalaWordNetWord(Word word) {
		this.word = word;
		

	}

	public SinhalaWordNetWord(String id, String lemma,
			SinhalaWordNetWord antonym, SinhalaWordNetWord root,
			SinhalaWordNetWord origin, SinhalaWordNetWord usage,
			SinhalaWordNetWord derivationType) {
		super();
		this.id = id;
		
		this.lemma = lemma;
		this.antonym = antonym;
		this.root = root;
		this.origin = origin;
		this.usage = usage;
		this.derivationType = derivationType;
		//System.out.println("tatol constructor");
	}

	public SinhalaWordNetWord() {
		

	}

	public String getId() {
		try {
			return String.valueOf(this.word.getIndex());
		} catch (NullPointerException e) {
			
			return "0";
		}
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLemma() {
		
		try {
			if(this.lemma != null){
				
			return this.lemma;
			}
			else{
				
				return this.word.getLemma();
			}
		} catch (NullPointerException e) {
			
			
			return "";
		}
	}

	public void setLemma(String lemma) {
		
	
		
		byte ptext[] =null;
		//byte ptext1[] =null;
		String value=null;
		//lemma.getBytes()
		try {
			//ptext = lemma.getBytes();
			ptext = lemma.getBytes("ISO8859_1");
			value = new String(ptext, Charset.forName("UTF-8"));
			//value = new String(ptext, "UTF-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(value != null){
		this.lemma = value;
		}
		else{
			this.lemma =lemma;
		}
		
	}
	
	public void setLemmaFromMongo(String lemma){
		this.lemma =lemma;
	}

	public SinhalaWordNetWord getAntonym() {
		if(this.antonym != null){
			
			return this.antonym;
		}
		else{
		Word antonym = null;
		try {
			Pointer[] p = this.word.getPointers(PointerType.ANTONYM);
			PointerTarget target = p[0].getTarget();
			antonym = (Word) target;
			
		} catch (JWNLException e) {
			
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			
			// TODO: handle exception
			// e.printStackTrace();
		} catch (NullPointerException e) {
			
			// TODO: handle exception
			//e.printStackTrace();
		}
		//System.out.println("new anto");
		SinhalaWordNetWord temp = new SinhalaWordNetWord(antonym);
		this.antonym = temp;
		return temp;
		}
	}
	
	public SinhalaWordNetWord getMyAnto(){
		return this.antonym;
	}

	public void setAntonym(SinhalaWordNetWord antonym) {
		
		this.antonym = antonym;
		antoIsSet = true;
	}

	public SinhalaWordNetWord getRoot() {
		
		if(this.root == null){
			SinhalaWordNetWord tempRoot = new SinhalaWordNetWord();
			tempRoot.setLemma("");
			this.root = tempRoot;
			return tempRoot;
			
		}
		else{
		return root;
		}
	}

	public void setRoot(SinhalaWordNetWord root) {
		
		this.root = root;
	}

	public SinhalaWordNetWord getOrigin() {
		
		if(this.origin == null){
			SinhalaWordNetWord tempRoot = new SinhalaWordNetWord();
			tempRoot.setLemma("");
			this.origin = tempRoot;
			return tempRoot;
			
		}
		else{
		return origin;
		}
	}

	public void setOrigin(SinhalaWordNetWord origin) {
		
		this.origin = origin;
	}

	public SinhalaWordNetWord getUsage() {
		
		return usage;
	}

	public void setUsage(SinhalaWordNetWord usage) {
		
		this.usage = usage;
	}

	public SinhalaWordNetWord getDerivationType() {
		
		return derivationType;
	}

	public void setDerivationType(SinhalaWordNetWord derivationType) {
		
		this.derivationType = derivationType;
	}

	public Long getSynsetOffset() {
		
		try{
			return this.word.getSynset().getOffset();
		} catch(NullPointerException e ){
			return 0l;
		}
	}

	public void setSynsetOffset(long synsetOffset) {
		
		this.synsetOffset = synsetOffset;
	}
	
public boolean isLemmaNull() {
		
		try {
			if(this.lemma != null){
				return false;
			}
			else{
				return true;
			}
		} catch (NullPointerException e) {
			return true;
		}
	}

}
