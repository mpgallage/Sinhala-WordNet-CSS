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
import org.sinhala.wordnet.css.model.wordnet.NounSynset;
import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetSynset;
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

		NounSynset nounSynset = new NounSynset(synset);
		SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
		NounSynset castSynset = mongoSynsetConvertor.OverWriteByMongo(nounSynset);
		BreadCrumbObject bcrumObject = new BreadCrumbObject("", castSynset.getWordsAsString(), "");

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
				castSynset = mongoSynsetConvertor.OverWriteByMongo(nSynset);
				
				BreadCrumbObject bcObject = new BreadCrumbObject(
						castSynset.getDefinition(), castSynset.getWordsAsString(), "ShowSynsets?action=ShowHyponyms&type=" + type + "&id=" + s.getOffset());

				breadCrumbList.add(bcObject);
				synset = s;
			}

			else {
				break;
			}
		}

		BreadCrumbObject root = new BreadCrumbObject("මූලය",  "මූලය",  "ShowSynsets?action=ShowRoot");
		BreadCrumbObject posRoot = null;

		if (pos.equals(POS.NOUN)) {
			posRoot = new BreadCrumbObject("නාමපද මූලය",  "නාමපද මූලය",  "ShowSynsets?action=ShowHyponyms&type=" + type);
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
		
		BreadCrumbObject root = new BreadCrumbObject("මූලය",  "මූලය",  "ShowSynsets?action=ShowRoot");
		BreadCrumbObject posRoot = null;

		if (pos.equals(POS.NOUN)) {
			posRoot = new BreadCrumbObject("නාමපද මූලය",  "නාමපද මූලය",  "ShowSynsets?action=ShowHyponyms&type=" + type);
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
