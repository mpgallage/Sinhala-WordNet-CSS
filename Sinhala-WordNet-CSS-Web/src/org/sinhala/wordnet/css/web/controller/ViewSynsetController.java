package org.sinhala.wordnet.css.web.controller;

import java.util.ArrayList;
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
import org.sinhala.wordnet.css.web.model.BreadCrumb;
import org.sinhala.wordnet.wordnetDB.core.SinhalaSynsetMongoSynsetConvertor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ViewSynset")
public class ViewSynsetController {

	@RequestMapping(method = RequestMethod.GET, params = { "action=ViewSynset", "type=noun" })
	public String viewSynset(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "type", required = false) String type, ModelMap model) {

		if (id != null && !"".equals(id)) {
			BreadCrumb breadCrumb = new BreadCrumb(Long.parseLong(id), POS.NOUN);
			Dictionary dict = WordNetDictionary.getInstance();
			Synset synset = null;
			try {
				synset = dict.getSynsetAt(POS.NOUN, Long.parseLong(id));
			} catch (NumberFormatException | JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			PointerUtils pointerUtils = PointerUtils.getInstance();
			PointerTargetNodeList nodeList = null;
			PointerTargetNodeList parentsList = null;
			try {
				nodeList = pointerUtils.getDirectHyponyms(synset);
				parentsList = pointerUtils.getDirectHypernyms(synset);
			} catch (JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			List<NounSynset[]> list = new ArrayList<NounSynset[]>();
			List<NounSynset[]> parents = new ArrayList<NounSynset[]>();

			System.out.println("**********" + nodeList.size());

			if (nodeList.size() > 0) {

				for (int i = 0; i < nodeList.size(); i++) {
					NounSynset[] nounsynsetArr = new NounSynset[2];
					PointerTargetNode node = (PointerTargetNode) nodeList.get(i);
					PointerTarget target = node.getPointerTarget();
					Synset s = (Synset) target;
					NounSynset tempNoun = new NounSynset(s);
					SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
					NounSynset castSynset = mongoSynsetConvertor.OverWriteByMongo(tempNoun);
					//NounSynset castSynset = new NounSynset();
					nounsynsetArr[0] = tempNoun;
					nounsynsetArr[1] = castSynset;

					list.add(nounsynsetArr);
				}

			}
			
			if (parentsList.size() > 0) {
				
				for (int i = 0; i < parentsList.size(); i++) {
					
					NounSynset[] nounsynsetArr = new NounSynset[2];
					PointerTargetNode node = (PointerTargetNode) parentsList.get(i);
					PointerTarget target = node.getPointerTarget();
					Synset s = (Synset) target;
					NounSynset tempNoun = new NounSynset(s);
					SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
					NounSynset castSynset = mongoSynsetConvertor.OverWriteByMongo(tempNoun);
					//NounSynset castSynset = new NounSynset();
					nounsynsetArr[0] = tempNoun;
					nounsynsetArr[1] = castSynset;

					parents.add(nounsynsetArr);
				}
			}

			NounSynset n = new NounSynset(synset);
			System.out.println("*******"+list.size());
			model.addAttribute("hyponymsList", list);
			model.addAttribute("parentsList", parents);
			model.addAttribute("synset", n);
			model.addAttribute("type", type);
			model.addAttribute("breadCrumb", breadCrumb);
			return "ViewSynset";
		} else {
			return "error";
		}

	}

}
