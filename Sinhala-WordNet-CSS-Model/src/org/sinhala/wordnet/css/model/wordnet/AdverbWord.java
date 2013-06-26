package org.sinhala.wordnet.css.model.wordnet;

import java.util.ArrayList;
import java.util.List;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.Pointer;
import net.didion.jwnl.data.PointerTarget;
import net.didion.jwnl.data.PointerType;
import net.didion.jwnl.data.Word;

public class AdverbWord extends SinhalaWordNetWord {

	private List<SinhalaWordNetWord> derived;

	public AdverbWord(String id, String lemma, SinhalaWordNetWord antonym,
			SinhalaWordNetWord root, SinhalaWordNetWord origin,
			SinhalaWordNetWord usage, SinhalaWordNetWord derivationType,
			List<SinhalaWordNetWord> derived) {

		super(id, lemma, antonym, root, origin, usage, derivationType);
		this.derived = derived;
	}

	public AdverbWord(Word word) {
		super(word);
		// ////////set derived
		Pointer[] pointers = word.getPointers(PointerType.DERIVED);
		List<SinhalaWordNetWord> list = new ArrayList<SinhalaWordNetWord>();

		for (Pointer p : pointers) {
			PointerTarget derived = null;
			try {
				derived = p.getTarget();
			} catch (JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ArrayIndexOutOfBoundsException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			Word temp = (Word) derived;
			SinhalaWordNetWord tempWord = new AdjectiveWord("",
					temp.getLemma(), null, null, null, null, null, null);
			list.add(tempWord);
		}

		this.derived = list;
		// /////
	}

	public AdverbWord() {
		super();
	}

	public List<SinhalaWordNetWord> getDerived() {
		return derived;
	}

	public void setDerived(List<SinhalaWordNetWord> derived) {
		this.derived = derived;
	}

}
