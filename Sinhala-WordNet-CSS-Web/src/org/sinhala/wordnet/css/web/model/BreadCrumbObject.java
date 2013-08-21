package org.sinhala.wordnet.css.web.model;

public class BreadCrumbObject {

	private String lemma;
	private String wordsAsCSV;
	private String link;

	public BreadCrumbObject(String lemma, String wordsAsCSV, String link) {
		super();
		this.lemma = lemma;
		this.wordsAsCSV = wordsAsCSV;
		this.link = link;
	}

	public String getLemma() {
		return lemma;
	}

	public void setLemma(String lemma) {
		this.lemma = lemma;
	}

	public String getWordsAsCSV() {
		return wordsAsCSV;
	}

	public void setWordsAsCSV(String wordsAsCSV) {
		this.wordsAsCSV = wordsAsCSV;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
