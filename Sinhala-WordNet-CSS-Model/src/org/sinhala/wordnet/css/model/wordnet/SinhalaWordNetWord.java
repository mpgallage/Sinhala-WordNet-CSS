package org.sinhala.wordnet.css.model.wordnet;

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
			return this.word.getLemma();
		} catch (NullPointerException e) {
			return "";
		}
	}

	public void setLemma(String lemma) {
		this.lemma = lemma;
	}

	public SinhalaWordNetWord getAntonym() {
		Pointer[] p = this.word.getPointers(PointerType.ANTONYM);
		PointerTarget target = null;
		Word antonym = null;
		try {
			target = p[0].getTarget();
			antonym = (Word) target;
		} catch (JWNLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
			// e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new SinhalaWordNetWord(antonym);
	}

	public void setAntonym(SinhalaWordNetWord antonym) {
		this.antonym = antonym;
	}

	public SinhalaWordNetWord getRoot() {
		return root;
	}

	public void setRoot(SinhalaWordNetWord root) {
		this.root = root;
	}

	public SinhalaWordNetWord getOrigin() {
		return origin;
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

	public long getSynsetOffset() {
		return this.word.getSynset().getOffset();
	}

	public void setSynsetOffset(long synsetOffset) {
		this.synsetOffset = synsetOffset;
	}

}
