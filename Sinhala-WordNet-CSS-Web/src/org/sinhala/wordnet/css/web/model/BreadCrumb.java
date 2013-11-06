package org.sinhala.wordnet.css.web.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.PointerTarget;
import net.didion.jwnl.data.PointerUtils;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;
import net.didion.jwnl.data.list.PointerTargetNode;
import net.didion.jwnl.data.list.PointerTargetNodeList;
import net.didion.jwnl.data.list.PointerTargetTree;
import net.didion.jwnl.dictionary.Dictionary;

import org.sinhala.wordnet.css.jwnl.WordNetDictionary;
import org.sinhala.wordnet.css.model.wordnet.AdjectiveSynset;
import org.sinhala.wordnet.css.model.wordnet.NounSynset;
import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetSynset;
import org.sinhala.wordnet.css.model.wordnet.VerbSynset;
import org.sinhala.wordnet.wordnetDB.core.SinhalaSynsetMongoSynsetConvertor;

public class BreadCrumb {

	private List<BreadCrumbObject> breadCrumbList = new ArrayList<BreadCrumbObject>();

	public BreadCrumb() {

	}

	public BreadCrumb(long offset, POS pos) {

		String type = "";

		if (pos.equals(POS.NOUN)) {
			type = "noun";
		} else if (pos.equals(POS.VERB)) {
			type = "verb";
		} else if (pos.equals(POS.ADJECTIVE)) {
			type = "adj";
		} else if (pos.equals(POS.ADVERB)) {
			type = "adv";
		}

		Dictionary dict = WordNetDictionary.getInstance();
		Synset synset = null;
		try {
			synset = dict.getSynsetAt(pos, offset);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JWNLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(type == "noun"){
		NounSynset nounSynset = new NounSynset(synset);
		SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();

		NounSynset castSynset = (NounSynset) mongoSynsetConvertor
				.OverWriteByMongo(nounSynset,"");

		String wordsAsString = castSynset.getWordsAsString();

		if (!nounSynset.getWordsAsString()
				.equals(castSynset.getWordsAsString())) {
			wordsAsString = castSynset.getWordsAsString() + "("
					+ nounSynset.getWordsAsString() + ")";
		}
		BreadCrumbObject bcrumObject = new BreadCrumbObject("", wordsAsString,
				"");
		breadCrumbList.add(bcrumObject);

		while (true) {

			PointerUtils pointerUtils = PointerUtils.getInstance();
			PointerTargetNodeList nodeList = new PointerTargetNodeList();
			try {
				nodeList = pointerUtils.getDirectHypernyms(synset);
			} catch (JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (nodeList.size() > 0) {

				PointerTargetNode node = (PointerTargetNode) nodeList.get(0);
				PointerTarget target = node.getPointerTarget();
				Synset s = (Synset) target;
				NounSynset nSynset = new NounSynset(s);

				mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
				castSynset = (NounSynset) mongoSynsetConvertor.OverWriteByMongo(nSynset,"");

				String def = castSynset.getDefinition();
				wordsAsString = castSynset.getWordsAsString();

				if (!nSynset.getDefinition().equals(castSynset.getDefinition())) {
					def = castSynset.getDefinition() + "("
							+ nSynset.getDefinition() + ")";
				}
				if (!nSynset.getWordsAsString().equals(
						castSynset.getWordsAsString())) {
					wordsAsString = castSynset.getWordsAsString() + "("
							+ nSynset.getWordsAsString() + ")";
				}

				BreadCrumbObject bcObject = new BreadCrumbObject(def,
						wordsAsString, "ShowSynsets?action=ShowHyponyms&type="
								+ type + "&id=" + s.getOffset());

				breadCrumbList.add(bcObject);
				synset = s;
			}

			else {
				break;
			}
		}
		}
		if(type == "verb"){
			
			VerbSynset verbSynset = new VerbSynset(synset);
			SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();

			VerbSynset castSynset = (VerbSynset) mongoSynsetConvertor
					.OverWriteByMongo(verbSynset,"");

			String wordsAsString = castSynset.getWordsAsString();

			if (!verbSynset.getWordsAsString()
					.equals(castSynset.getWordsAsString())) {
				wordsAsString = castSynset.getWordsAsString() + "("
						+ verbSynset.getWordsAsString() + ")";
			}
			BreadCrumbObject bcrumObject = new BreadCrumbObject("", wordsAsString,
					"");
			breadCrumbList.add(bcrumObject);

			while (true) {

				PointerUtils pointerUtils = PointerUtils.getInstance();
				PointerTargetNodeList nodeList = new PointerTargetNodeList();
				try {
					nodeList = pointerUtils.getDirectHypernyms(synset);
				} catch (JWNLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (nodeList.size() > 0) {

					PointerTargetNode node = (PointerTargetNode) nodeList.get(0);
					PointerTarget target = node.getPointerTarget();
					Synset s = (Synset) target;
					VerbSynset vSynset = new VerbSynset(s);

					mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
					castSynset = (VerbSynset) mongoSynsetConvertor.OverWriteByMongo(vSynset,"");

					String def = castSynset.getDefinition();
					wordsAsString = castSynset.getWordsAsString();

					if (!vSynset.getDefinition().equals(castSynset.getDefinition())) {
						def = castSynset.getDefinition() + "("
								+ vSynset.getDefinition() + ")";
					}
					if (!vSynset.getWordsAsString().equals(
							castSynset.getWordsAsString())) {
						wordsAsString = castSynset.getWordsAsString() + "("
								+ vSynset.getWordsAsString() + ")";
					}

					BreadCrumbObject bcObject = new BreadCrumbObject(def,
							wordsAsString, "ShowSynsets?action=ShowHyponyms&type="
									+ type + "&id=" + s.getOffset());

					breadCrumbList.add(bcObject);
					synset = s;
				}

				else {
					break;
				}
			}
		}
			else if(type == "adj"){
				AdjectiveSynset adjSynset = new AdjectiveSynset(synset);
				SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();

				AdjectiveSynset castSynset = (AdjectiveSynset) mongoSynsetConvertor
						.OverWriteByMongo(adjSynset,"");

				String wordsAsString = castSynset.getWordsAsString();

				if (!adjSynset.getWordsAsString()
						.equals(castSynset.getWordsAsString())) {
					wordsAsString = castSynset.getWordsAsString() + "("
							+ adjSynset.getWordsAsString() + ")";
				}
				BreadCrumbObject bcrumObject = new BreadCrumbObject("", wordsAsString,
						"");
				breadCrumbList.add(bcrumObject);

				while (true) {

					PointerUtils pointerUtils = PointerUtils.getInstance();
					PointerTargetNodeList nodeList = new PointerTargetNodeList();
					try {
						nodeList = pointerUtils.getDirectHypernyms(synset);
					} catch (JWNLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (nodeList.size() > 0) {

						PointerTargetNode node = (PointerTargetNode) nodeList.get(0);
						PointerTarget target = node.getPointerTarget();
						Synset s = (Synset) target;
						AdjectiveSynset aSynset = new AdjectiveSynset(s);

						mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
						castSynset = (AdjectiveSynset) mongoSynsetConvertor.OverWriteByMongo(aSynset,"");

						String def = castSynset.getDefinition();
						wordsAsString = castSynset.getWordsAsString();

						if (!aSynset.getDefinition().equals(castSynset.getDefinition())) {
							def = castSynset.getDefinition() + "("
									+ aSynset.getDefinition() + ")";
						}
						if (!aSynset.getWordsAsString().equals(
								castSynset.getWordsAsString())) {
							wordsAsString = castSynset.getWordsAsString() + "("
									+ aSynset.getWordsAsString() + ")";
						}

						BreadCrumbObject bcObject = new BreadCrumbObject(def,
								wordsAsString, "ShowSynsets?action=ShowHyponyms&type="
										+ type + "&id=" + s.getOffset());

						breadCrumbList.add(bcObject);
						synset = s;
					}

					else {
						break;
					}
				}
				}
			
		
		BreadCrumbObject root = new BreadCrumbObject("මුලය(Root)",
				"මුලය(Root)", "ShowSynsets?action=ShowRoot");
		BreadCrumbObject posRoot = null;

		if (pos.equals(POS.NOUN)) {
			posRoot = new BreadCrumbObject("නාමපද(Nouns)", "නාමපද(Nouns)",
					"ShowSynsets?action=ShowHyponyms&type=" + type);
		}
		if (pos.equals(POS.VERB)) {
			posRoot = new BreadCrumbObject("ක්‍රියාපද(Verbs)", "ක්‍රියාපද(Verbs)",
					"ShowSynsets?action=ShowHyponyms&type=" + type);
		}
		if (pos.equals(POS.ADJECTIVE)) {
			posRoot = new BreadCrumbObject("නාම විශේෂණ(Adjective)", "නාම විශේෂණ(Adjective)",
					"ShowSynsets?action=ShowHyponyms&type=" + type);
		}
		breadCrumbList.add(posRoot);
		breadCrumbList.add(root);

		Collections.reverse(breadCrumbList);

	}

	public BreadCrumb(POS pos) {

		String type = "";

		if (pos.equals(POS.NOUN)) {
			type = "noun";
		} else if (pos.equals(POS.VERB)) {
			type = "verb";
		} else if (pos.equals(POS.ADJECTIVE)) {
			type = "adj";
		} else if (pos.equals(POS.ADVERB)) {
			type = "adv";
		}

		BreadCrumbObject root = new BreadCrumbObject("මුලය(Root)",
				"මුලය(Root)", "ShowSynsets?action=ShowRoot");
		BreadCrumbObject posRoot = null;

		if (pos.equals(POS.NOUN)) {
			posRoot = new BreadCrumbObject("නාමපද(Nouns)", "නාමපද(Nouns)",
					"ShowSynsets?action=ShowHyponyms&type=" + type);
		}
		if (pos.equals(POS.VERB)) {
			posRoot = new BreadCrumbObject("ක්‍රියාපද(Verbs)", "ක්‍රියාපද(Verbs)",
					"ShowSynsets?action=ShowHyponyms&type=" + type);
		}
		if (pos.equals(POS.ADJECTIVE)) {
			posRoot = new BreadCrumbObject("නාම විශේෂණ(Adjective)", "නාම විශේෂණ(Adjective)",
					"ShowSynsets?action=ShowHyponyms&type=" + type);
		}
		breadCrumbList.add(posRoot);
		breadCrumbList.add(root);

		Collections.reverse(breadCrumbList);

	}

	public List<BreadCrumbObject> getBreadCrumbList() {
		return breadCrumbList;
	}

	public void setBreadCrumbList(List<BreadCrumbObject> breadCrumbList) {
		this.breadCrumbList = breadCrumbList;
	}

}
