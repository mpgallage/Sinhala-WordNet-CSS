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

	}
	
	public VerbWord(){
		super();
	}

	public List<SinhalaWordNetWord> getSeealso() {
		Pointer[] pointers = word.getPointers(PointerType.SEE_ALSO);
		List<SinhalaWordNetWord> list = new ArrayList<SinhalaWordNetWord>();

		for (Pointer p : pointers) {
			PointerTarget target = null;
			try {
				target = p.getTarget();
			} catch (JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ArrayIndexOutOfBoundsException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			Word seeAlso = (Word) target;
			list.add(new VerbWord(seeAlso));
		}

		return list;
	}

	public void setSeeAlso(List<SinhalaWordNetWord> alsoSee) {
		this.seeAlso = alsoSee;
	}
}
