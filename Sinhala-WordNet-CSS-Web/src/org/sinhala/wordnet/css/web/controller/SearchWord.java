package org.sinhala.wordnet.css.web.controller;

import net.didion.jwnl.data.POS;

public class SearchWord {

	private String rawWord;
	private String cleanedWord;
	private POS Pos;

	public SearchWord() {
		// TODO Auto-generated constructor stub
	}

	public SearchWord(String raWord, String Pos) {
		this.rawWord = raWord;

		if (Pos.equalsIgnoreCase("noun")) {
			this.Pos = POS.NOUN;
		} else if (Pos.equalsIgnoreCase("verb")) {
			this.Pos = POS.VERB;
		} else if (Pos.equalsIgnoreCase("adj")) {
			this.Pos = POS.ADJECTIVE;
		} else if (Pos.equalsIgnoreCase("adv")) {
			this.Pos = POS.ADVERB;
		}

		raWord = raWord.replaceAll("_", " ");
		raWord = raWord.trim();
		this.cleanedWord = raWord;
	}

	public String getRawWord() {
		return rawWord;
	}

	public void setRawWord(String rawWord) {
		this.rawWord = rawWord;
	}

	public POS getPOS() {
		return Pos;
	}

	public String getCleanedWord() {
		return cleanedWord;
	}

	public boolean isSinhala() {
		boolean isSinhala = false;
		char firstChar = this.cleanedWord.charAt(0);

		if (firstChar <= '\u0DFF' && firstChar >= '\u0D80') {
			isSinhala = true;
		}

		return isSinhala;
	}

}
