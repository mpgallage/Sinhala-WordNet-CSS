package org.sinhala.wordnet.css.web.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.PointerTarget;
import net.didion.jwnl.data.PointerUtils;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.list.PointerTargetNode;
import net.didion.jwnl.data.list.PointerTargetNodeList;
import net.didion.jwnl.dictionary.Dictionary;

import org.sinhala.wordnet.css.jwnl.WordNetDictionary;
import org.sinhala.wordnet.css.model.wordnet.NounSynset;
import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetSynset;
import org.sinhala.wordnet.wordnetDB.core.SinhalaSynsetMongoSynsetConvertor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ShowSynsets")
public class ShowSynsetsController {

	@RequestMapping(method = RequestMethod.GET, params = "action=ShowRoot")
	public String showRoot(ModelMap model) {
		return "root";
	}

	@RequestMapping(method = RequestMethod.GET, params = { "action=ShowHyponyms", "type=noun" })
	public String showHyponyms(@RequestParam(value = "id", required = false) String id, ModelMap model, @RequestParam(value = "type", required = false) String type) {
		if (id != null && !"".equalsIgnoreCase(id)) {
			Dictionary dict = WordNetDictionary.getInstance();
			Synset synset = null;
			try {
				synset = dict.getSynsetAt(POS.NOUN, Long.parseLong(id));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			PointerUtils pointerUtils = PointerUtils.getInstance();
			PointerTargetNodeList nodeList = null;
			try {
				nodeList = pointerUtils.getDirectHyponyms(synset);
			} catch (JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (nodeList.size() > 0) {

				// List<SynsetElement> elementList = new
				// ArrayList<SynsetElement>();

				// List<NounSynset> list = new ArrayList<NounSynset>();
				List<NounSynset[]> list = new ArrayList<NounSynset[]>();

				for (int i = 0; i < nodeList.size(); i++) {
					NounSynset[] nounsynsetArr = new NounSynset[2];
					PointerTargetNode node = (PointerTargetNode) nodeList.get(i);
					PointerTarget target = node.getPointerTarget();
					Synset s = (Synset) target;
					NounSynset tempNoun = new NounSynset(s);
					SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
					NounSynset castSynset1 = mongoSynsetConvertor.OverWriteByMongo(tempNoun);
					// NounSynset castSynset1 = new NounSynset();
					nounsynsetArr[0] = tempNoun;
					nounsynsetArr[1] = castSynset1;

					list.add(nounsynsetArr);

				}
				model.addAttribute("synsetList", list);
				model.addAttribute("type", type);
			} else {
				PointerTargetNodeList list = null;
				try {
					list = pointerUtils.getDirectHypernyms(synset);
				} catch (JWNLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				PointerTargetNode node = (PointerTargetNode) list.get(0);
				Synset tempSynset = (Synset) node.getPointerTarget();
				long parentOffset = tempSynset.getOffset();

				model.addAttribute("synsetList", new ArrayList<SinhalaWordNetSynset>());
				model.addAttribute("type", type);
				model.addAttribute("parent", String.valueOf(parentOffset));
			}
			return "ShowHyponyms";
		} else {
			Dictionary dict = WordNetDictionary.getInstance();

			Iterator<Synset> itr = null;
			try {
				itr = dict.getSynsetIterator(POS.NOUN);
			} catch (JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<Synset> synsetList = new ArrayList<Synset>();

			while (itr.hasNext()) {
				Synset tempSynset = itr.next();
				PointerUtils pointerUtils = PointerUtils.getInstance();
				PointerTargetNodeList list = null;
				try {
					list = pointerUtils.getDirectHypernyms(tempSynset);
				} catch (JWNLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (list.size() < 1) {
					synsetList.add(tempSynset);
				}
			}

			// List<SinhalaWordNetSynset> elementList = new
			// ArrayList<SinhalaWordNetSynset>();
			List<NounSynset[]> list = new ArrayList<NounSynset[]>();

			for (Synset s : synsetList) {
				NounSynset[] nounsynsetArr = new NounSynset[2];
				NounSynset tempNoun = new NounSynset(s);
				SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
				NounSynset castSynset1 = mongoSynsetConvertor.OverWriteByMongo(tempNoun);
				nounsynsetArr[0] = tempNoun;
				nounsynsetArr[1] = castSynset1;

				list.add(nounsynsetArr);
			}

			model.addAttribute("synsetList", list);
			model.addAttribute("type", type);

			return "ShowHyponyms";
		}
	}
}
