package org.sinhala.wordnet.css.web.controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import net.didion.jwnl.data.POS;

/**
 * Search input pre- processor
 * This class holds the search input as a separate object and pre-process the input
 * to make it available for a direct search
 * @author Iresha
 *
 */
public class SearchWord {

	private String rawWord;
	private String cleanedWord;
	private POS Pos;
	private String POS;
	private String errorMessage = "";

	/**
	 * Constructor
	 */
	public SearchWord() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return String error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * 
	 * @param errorMessage : set error message
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * 
	 * @param cleanedWord: set cleaned word
	 */
	public void setCleanedWord(String cleanedWord) {
		this.cleanedWord = cleanedWord;
	}

	/**
	 * 
	 * @return rawWord : get row word for local access
	 */
	public String getRawWord() {
		return rawWord;
	}

	/**
	 * 
	 * @return POS : Java WordNet Library Part of speech of the search string (whether noun, adjective, adverb or verb)
	 */
	public String getPOS() {
		return POS;
	}

	/**
	 * 
	 * @param pOS
	 * Set the Java WordNet Library Part of Speech for the given string POS
	 */
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

	/**
	 * 
	 * @param rawWord
	 * Pre-process the search input to support Unicode
	 */
	public void setRawWord(String rawWord) {
		String value;
		if (rawWord == " " || rawWord == "" || rawWord == null) {
			value = " ";
		} else {

			byte[] ptext = null;
			try {
				ptext = rawWord.getBytes("ISO8859_1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (ptext.length<1) {
				value = " ";
			}else{
				/*
				 * Converts to Unicodes
				 */
			value = new String(ptext, Charset.forName("UTF-8"));
			}
		}
		System.out.println(value);
		this.setCleanedWord(value);
		this.rawWord = value;

	}

	/**
	 * 
	 * @return Pos
	 * returns the Java WordNet Library Part of Speech of the search string
	 */
	public POS getPos() {
		return Pos;
	}

	/**
	 * 
	 * @return cleanedWord
	 * Returns the pre-processed string for direct search
	 */
	public String getCleanedWord() {
		return cleanedWord;
	}

	/**
	 * 
	 * @return isSinhala
	 * Returns true if the search input is a Sinhala string false otherwise
	 */
	public boolean isSinhala() {
		boolean isSinhala = false;
		// System.out.println((int)this.cleanedWord.charAt(0));
		// System.out.println(this.cleanedWord.charAt(0));
		char firstChar = this.cleanedWord.charAt(0);

		if (firstChar <= '\u0DFF' && firstChar >= '\u0D80') {
			isSinhala = true;
		}

		return isSinhala;
	}

}
