package org.sinhala.wordnet.css.model.wordnet;

public class SynsetElement {
	
	private long offset;
	private String wordList;
	private String gloss;
	
	public SynsetElement(long offset, String wordList, String gloss) {
		super();
		this.offset = offset;
		this.wordList = wordList;
		this.gloss = gloss;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public String getWordList() {
		return wordList;
	}

	public void setWordList(String wordList) {
		this.wordList = wordList;
	}

	public String getGloss() {
		return gloss;
	}

	public void setGloss(String gloss) {
		this.gloss = gloss;
	}

	
}
