package org.sinhala.wordnet.css.web.controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import net.didion.jwnl.data.POS;

public class SearchWord {

	private String rawWord;
	private String cleanedWord;
	private POS Pos;
	private String POS;

	public SearchWord() {
		// TODO Auto-generated constructor stub
	}

	
	public void setCleanedWord(String cleanedWord) {
		this.cleanedWord = cleanedWord;
	}


	public String getRawWord() {
		return rawWord;
	}

	public String getPOS() {
		return POS;
	}

	public void setPOS(String pOS) {
		
		if (pOS.equalsIgnoreCase("noun")) {
			this.Pos = net.didion.jwnl.data.POS.NOUN;
		} else if (pOS.equalsIgnoreCase("verb")) {
			this.Pos = net.didion.jwnl.data.POS.VERB;
		} else if (pOS.equalsIgnoreCase("adj")) {
			this.Pos = net.didion.jwnl.data.POS.ADJECTIVE;
		} else if (pOS.equalsIgnoreCase("adv")) {
			this.Pos = net.didion.jwnl.data.POS.ADVERB;
		}
		
		POS = pOS;
	}

	public void setRawWord(String rawWord) {
		
		byte[] ptext = null;
		try {
			ptext = rawWord.getBytes("ISO8859_1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String value = new String(ptext, Charset.forName("UTF-8"));
		System.out.println(value);
		this.setCleanedWord(value);
		this.rawWord = value;
		
	}

	
	
	public POS getPos() {
		return Pos;
	}


	public String getCleanedWord() {
		return cleanedWord;
	}

	public boolean isSinhala() {
		boolean isSinhala = false;
//		System.out.println((int)this.cleanedWord.charAt(0));
//		System.out.println(this.cleanedWord.charAt(0));
		char firstChar = this.cleanedWord.charAt(0);

		if (firstChar <= '\u0DFF' && firstChar >= '\u0D80') {
			isSinhala = true;
		}

		return isSinhala;
	}

}
