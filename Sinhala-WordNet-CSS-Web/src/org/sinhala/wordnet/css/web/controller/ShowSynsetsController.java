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
import org.sinhala.wordnet.css.web.model.BreadCrumb;
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
			
			BreadCrumb breadCrumb = new BreadCrumb();
			try{
				breadCrumb = new BreadCrumb(synset.getOffset(), POS.NOUN);
			} catch (Exception e){
				return "error";
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

				List<NounSynset[]> list = new ArrayList<NounSynset[]>();
				List<Boolean> nextLevelList = new ArrayList<Boolean>();

				for (int i = 0; i < nodeList.size(); i++) {
					NounSynset[] nounsynsetArr = new NounSynset[2];
					PointerTargetNode node = (PointerTargetNode) nodeList.get(i);
					PointerTarget target = node.getPointerTarget();
					Synset s = (Synset) target;
					NounSynset tempNoun = new NounSynset(s);
					SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
					NounSynset castSynset = mongoSynsetConvertor.OverWriteByMongo(tempNoun);
					// NounSynset castSynset = new NounSynset();
					nounsynsetArr[0] = tempNoun;
					nounsynsetArr[1] = castSynset;

					list.add(nounsynsetArr);
					
					PointerTargetNodeList subNodeList = null;
					try {
						subNodeList = pointerUtils.getDirectHyponyms(s);
					} catch (JWNLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (subNodeList.size() > 0) {
						nextLevelList.add(true);
					} else {
						nextLevelList.add(false);
					}

				}
				model.addAttribute("synsetList", list);
				model.addAttribute("type", type);
				model.addAttribute("nextLevelList", nextLevelList);
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
			
			NounSynset nounSynset = new NounSynset(synset);
			SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();

			NounSynset enSynset = mongoSynsetConvertor
					.OverWriteByMongo(nounSynset);
			model.addAttribute("enSynset", enSynset);
			
			model.addAttribute("breadCrumb", breadCrumb);
			return "ShowHyponyms";
			
		} else {
			
			BreadCrumb breadCrumb = new BreadCrumb(POS.NOUN);
			Dictionary dict = WordNetDictionary.getInstance();

			Synset synset = null;
			try {
				synset = dict.getSynsetAt(POS.NOUN, 1740);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			List<Boolean> nextLevelList = new ArrayList<Boolean>();
			nextLevelList.add(true);
			
			List<NounSynset[]> list = new ArrayList<NounSynset[]>();
			NounSynset[] nounsynsetArr = new NounSynset[2];
			NounSynset tempNoun = new NounSynset(synset);
			SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
			NounSynset castSynset = mongoSynsetConvertor.OverWriteByMongo(tempNoun);
			// NounSynset castSynset = new NounSynset();
			nounsynsetArr[0] = tempNoun;
			nounsynsetArr[1] = castSynset;

			list.add(nounsynsetArr);
			
			

			model.addAttribute("synsetList", list);
			model.addAttribute("type", type);
			model.addAttribute("nextLevelList", nextLevelList);
			
			NounSynset nounSynset = new NounSynset(synset);
			mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();

			NounSynset enSynset = mongoSynsetConvertor
					.OverWriteByMongo(nounSynset);
			model.addAttribute("enSynset", enSynset);

			model.addAttribute("breadCrumb", breadCrumb);
			return "ShowHyponyms";
		}
	}
}
