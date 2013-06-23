package org.sinhala.wordnet.css.model.wordnet;

import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;

public class SinhalaSynset {

	public SynsetElement getSynsetElement(Synset synset) {
		Word[] wordList = synset.getWords();

		String wordsQueue = "";

		for (Word word : wordList) {
			wordsQueue += word.getLemma() + ", ";
		}

		wordsQueue = wordsQueue.trim();
		if (wordsQueue.length() > 0) {
			wordsQueue = wordsQueue.substring(0, wordsQueue.length() - 1);
		}

		SynsetElement element = new SynsetElement(synset.getOffset(),
				wordsQueue, synset.getGloss());

		return element;
	}

}
