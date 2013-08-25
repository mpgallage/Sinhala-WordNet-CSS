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
		//System.out.println("word constructor"+this.toString());

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
		//System.out.println("default constructer"+this.toString());
		

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
				//System.out.println("in if get"+this.lemma);
			return this.lemma;
			}
			else{
				//System.out.println("else in get"+this.toString());
				return this.word.getLemma();
			}
		} catch (NullPointerException e) {
			//System.out.println("null e"+e);
			
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
		//System.out.println("word lema ff - "+lemma+"-word-"+this.toString());
		if(value != null){
		this.lemma = value;
		}
		else{
			this.lemma =lemma;
		}
		//System.out.println(lemma+"-setted lemma 11"+this.lemma);
	}
	
	public void setLemmaFromMongo(String lemma){
		this.lemma =lemma;
	}

	public SinhalaWordNetWord getAntonym() {
		if(this.antonym != null){
			//System.out.println("this anto");
			return this.antonym;
		}
		else{
		Word antonym = null;
		try {
			Pointer[] p = this.word.getPointers(PointerType.ANTONYM);
			PointerTarget target = p[0].getTarget();
			antonym = (Word) target;
			
		} catch (JWNLException e) {
			System.out.println("JWNLException"+this.lemma);
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			//System.out.println("ArrayIndexOutOfBoundsException"+this.lemma);
			// TODO: handle exception
			// e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("NullPointerException" +this.lemma);
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
		//System.out.println("set anto");
		this.antonym = antonym;
		antoIsSet = true;
	}

	public SinhalaWordNetWord getRoot() {
		//System.out.println("get root");
		if(this.root == null){
			SinhalaWordNetWord tempRoot = new SinhalaWordNetWord();
			tempRoot.setLemma("");
			this.root = tempRoot;
			//System.out.println("return temp nouns");
			return tempRoot;
			
		}
		else{
		return root;
		}
	}

	public void setRoot(SinhalaWordNetWord root) {
		//System.out.println("set root"+root.toString());
		this.root = root;
	}

	public SinhalaWordNetWord getOrigin() {
		//System.out.println("get ori");
		if(this.origin == null){
			SinhalaWordNetWord tempRoot = new SinhalaWordNetWord();
			tempRoot.setLemma("");
			this.origin = tempRoot;
			//System.out.println("return temp nouns");
			return tempRoot;
			
		}
		else{
		return origin;
		}
	}

	public void setOrigin(SinhalaWordNetWord origin) {
		//System.out.println("set origin");
		this.origin = origin;
	}

	public SinhalaWordNetWord getUsage() {
		//System.out.println("get use");
		return usage;
	}

	public void setUsage(SinhalaWordNetWord usage) {
		//System.out.println("set usage");
		this.usage = usage;
	}

	public SinhalaWordNetWord getDerivationType() {
		//System.out.println("get deri");
		return derivationType;
	}

	public void setDerivationType(SinhalaWordNetWord derivationType) {
		//System.out.println("set deri");
		this.derivationType = derivationType;
	}

	public Long getSynsetOffset() {
		//System.out.println("word in off"+this.word.toString());
		return this.word.getSynset().getOffset();
	}

	public void setSynsetOffset(long synsetOffset) {
		//System.out.println("set off");
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
