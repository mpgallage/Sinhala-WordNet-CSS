package org.sinhala.wordnet.css.model.wordnet;

import java.util.ArrayList;
import java.util.List;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.Pointer;
import net.didion.jwnl.data.PointerTarget;
import net.didion.jwnl.data.PointerType;
import net.didion.jwnl.data.Word;

public class AdjectiveWord extends SinhalaWordNetWord {

	private List<SinhalaWordNetWord> relational;

	public AdjectiveWord(String id, String lemma, SinhalaWordNetWord antonym,
			SinhalaWordNetWord root, SinhalaWordNetWord origin,
			SinhalaWordNetWord usage, SinhalaWordNetWord derivationType,
			List<SinhalaWordNetWord> relational) {

		super(id, lemma, antonym, root, origin, usage, derivationType);
		this.relational = relational;
	}

	public AdjectiveWord(Word word) {
		super(word);
		
		// ////////set relational
		/* relational pointer type not defined
		Pointer[] pointers = word.getPointers(PointerType.XXXX);
		List<SinhalaWordNetWord> list = new ArrayList<SinhalaWordNetWord>();
		
		for(Pointer p : pointers){
			PointerTarget relational = null;
			try {
				relational = p.getTarget();
			} catch (JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ArrayIndexOutOfBoundsException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			Word temp = (Word) relational;
			SinhalaWordNetWord tempWord = new AdjectiveWord("",
					temp.getLemma(), null, null, null, null, null, null);
			list.add(tempWord);
		}
		
		this.relational = list;
		// /////*/
	}

	public AdjectiveWord() {
		super();
	}

	public List<SinhalaWordNetWord> getRelational() {
		return relational;
	}

	public void setRelational(List<SinhalaWordNetWord> relational) {
		this.relational = relational;
	}

}
