package org.sinhala.wordnet.css.model.wordnet;

import java.util.ArrayList;
import java.util.List;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.Pointer;
import net.didion.jwnl.data.PointerTarget;
import net.didion.jwnl.data.PointerType;
import net.didion.jwnl.data.Word;

public class VerbWord extends SinhalaWordNetWord {

	private List<SinhalaWordNetWord> seeAlso;

	public VerbWord(String id, String lemma, SinhalaWordNetWord antonym,
			SinhalaWordNetWord root, SinhalaWordNetWord origin,
			SinhalaWordNetWord usage, SinhalaWordNetWord derivationType,
			List<SinhalaWordNetWord> seeAlso) {
		super(id, lemma, antonym, root, origin, usage, derivationType);
		this.seeAlso = seeAlso;
	}

	public VerbWord(Word word) {
		super(word);
		// ////////set derived
		Pointer[] pointers = word.getPointers(PointerType.SEE_ALSO);
		List<SinhalaWordNetWord> list = new ArrayList<SinhalaWordNetWord>();

		for (Pointer p : pointers) {
			PointerTarget seeAlso = null;
			try {
				seeAlso = p.getTarget();
			} catch (JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ArrayIndexOutOfBoundsException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			Word temp = (Word) seeAlso;
			SinhalaWordNetWord tempWord = new AdjectiveWord("",
					temp.getLemma(), null, null, null, null, null, null);
			list.add(tempWord);
		}

		this.seeAlso = list;
		// /////
	}

	public VerbWord() {
		super();
	}

	public List<SinhalaWordNetWord> getSeealso() {
		return seeAlso;
	}

	public void setSeeAlso(List<SinhalaWordNetWord> alsoSee) {
		this.seeAlso = alsoSee;
	}
}
