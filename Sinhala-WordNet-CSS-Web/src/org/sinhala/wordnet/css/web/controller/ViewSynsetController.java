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
import org.sinhala.wordnet.css.model.wordnet.AdjectiveSynset;
import org.sinhala.wordnet.css.model.wordnet.AdverbSynset;
import org.sinhala.wordnet.css.model.wordnet.NounSynset;
import org.sinhala.wordnet.css.model.wordnet.VerbSynset;
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

			//System.out.println("**********" + nodeList.size());

			if (nodeList.size() > 0) {

				for (int i = 0; i < nodeList.size(); i++) {
					NounSynset[] nounsynsetArr = new NounSynset[2];
					PointerTargetNode node = (PointerTargetNode) nodeList.get(i);
					PointerTarget target = node.getPointerTarget();
					Synset s = (Synset) target;
					NounSynset tempNoun = new NounSynset(s);
					SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
					NounSynset castSynset = (NounSynset) mongoSynsetConvertor.OverWriteByMongo(tempNoun,"");
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
					NounSynset castSynset = (NounSynset) mongoSynsetConvertor.OverWriteByMongo(tempNoun,"");
					//NounSynset castSynset = new NounSynset();
					nounsynsetArr[0] = tempNoun;
					nounsynsetArr[1] = castSynset;

					parents.add(nounsynsetArr);
				}
			}

			NounSynset nSynset = new NounSynset(synset);
			SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
			NounSynset castMainSynset = (NounSynset) mongoSynsetConvertor.OverWriteByMongo(nSynset,"");
			
			//System.out.println("*******"+list.size());
			model.addAttribute("hyponymsList", list);
			model.addAttribute("parentsList", parents);
			model.addAttribute("synset", castMainSynset);
			model.addAttribute("type", type);
			model.addAttribute("breadCrumb", breadCrumb);
			return "ViewSynset";
		} else {
			return "error";
		}

	}

	
	
	@RequestMapping(method = RequestMethod.GET, params = { "action=ViewSynset", "type=verb" })
	public String viewVerbSynset(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "type", required = false) String type, ModelMap model) {

		if (id != null && !"".equals(id)) {
			BreadCrumb breadCrumb = new BreadCrumb(Long.parseLong(id), POS.VERB);
			Dictionary dict = WordNetDictionary.getInstance();
			Synset synset = null;
			try {
				synset = dict.getSynsetAt(POS.VERB, Long.parseLong(id));
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

			List<VerbSynset[]> list = new ArrayList<VerbSynset[]>();
			List<VerbSynset[]> parents = new ArrayList<VerbSynset[]>();

			//System.out.println("**********" + nodeList.size());

			if (nodeList.size() > 0) {

				for (int i = 0; i < nodeList.size(); i++) {
					VerbSynset[] verbsynsetArr = new VerbSynset[2];
					PointerTargetNode node = (PointerTargetNode) nodeList.get(i);
					PointerTarget target = node.getPointerTarget();
					Synset s = (Synset) target;
					VerbSynset tempVerb = new VerbSynset(s);
					SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
					VerbSynset castSynset = (VerbSynset) mongoSynsetConvertor.OverWriteByMongo(tempVerb,"");
					//NounSynset castSynset = new NounSynset();
					verbsynsetArr[0] = tempVerb;
					verbsynsetArr[1] = castSynset;

					list.add(verbsynsetArr);
				}

			}
			
			if (parentsList.size() > 0) {
				
				for (int i = 0; i < parentsList.size(); i++) {
					
					VerbSynset[] verbsynsetArr = new VerbSynset[2];
					PointerTargetNode node = (PointerTargetNode) parentsList.get(i);
					PointerTarget target = node.getPointerTarget();
					Synset s = (Synset) target;
					VerbSynset tempVerb = new VerbSynset(s);
					SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
					VerbSynset castSynset = (VerbSynset) mongoSynsetConvertor.OverWriteByMongo(tempVerb,"");
					//NounSynset castSynset = new NounSynset();
					verbsynsetArr[0] = tempVerb;
					verbsynsetArr[1] = castSynset;

					parents.add(verbsynsetArr);
				}
			}

			VerbSynset vSynset = new VerbSynset(synset);
			SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
			VerbSynset castMainSynset = (VerbSynset) mongoSynsetConvertor.OverWriteByMongo(vSynset,"");
			
			//System.out.println("*******"+list.size());
			model.addAttribute("hyponymsList", list);
			model.addAttribute("parentsList", parents);
			model.addAttribute("synset", castMainSynset);
			model.addAttribute("type", type);
			model.addAttribute("breadCrumb", breadCrumb);
			return "ViewSynset";
		} else {
			return "error";
		}

	}
	
	
	@RequestMapping(method = RequestMethod.GET, params = { "action=ViewSynset", "type=adj" })
	public String viewAdjSynset(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "type", required = false) String type, ModelMap model) {

		if (id != null && !"".equals(id)) {
			BreadCrumb breadCrumb = new BreadCrumb(Long.parseLong(id), POS.ADJECTIVE);
			Dictionary dict = WordNetDictionary.getInstance();
			Synset synset = null;
			try {
				synset = dict.getSynsetAt(POS.ADJECTIVE, Long.parseLong(id));
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

			List<AdjectiveSynset[]> list = new ArrayList<AdjectiveSynset[]>();
			List<AdjectiveSynset[]> parents = new ArrayList<AdjectiveSynset[]>();

			//System.out.println("**********" + nodeList.size());

			if (nodeList.size() > 0) {

				for (int i = 0; i < nodeList.size(); i++) {
					AdjectiveSynset[] adjsynsetArr = new AdjectiveSynset[2];
					PointerTargetNode node = (PointerTargetNode) nodeList.get(i);
					PointerTarget target = node.getPointerTarget();
					Synset s = (Synset) target;
					AdjectiveSynset tempAdj = new AdjectiveSynset(s);
					SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
					AdjectiveSynset castSynset = (AdjectiveSynset) mongoSynsetConvertor.OverWriteByMongo(tempAdj,"");
					//NounSynset castSynset = new NounSynset();
					adjsynsetArr[0] = tempAdj;
					adjsynsetArr[1] = castSynset;

					list.add(adjsynsetArr);
				}

			}
			
			if (parentsList.size() > 0) {
				
				for (int i = 0; i < parentsList.size(); i++) {
					
					AdjectiveSynset[] adjsynsetArr = new AdjectiveSynset[2];
					PointerTargetNode node = (PointerTargetNode) parentsList.get(i);
					PointerTarget target = node.getPointerTarget();
					Synset s = (Synset) target;
					AdjectiveSynset tempAdj = new AdjectiveSynset(s);
					SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
					AdjectiveSynset castSynset = (AdjectiveSynset) mongoSynsetConvertor.OverWriteByMongo(tempAdj,"");
					//NounSynset castSynset = new NounSynset();
					adjsynsetArr[0] = tempAdj;
					adjsynsetArr[1] = castSynset;

					parents.add(adjsynsetArr);
				}
			}

			AdjectiveSynset aSynset = new AdjectiveSynset(synset);
			SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
			AdjectiveSynset castMainSynset = (AdjectiveSynset) mongoSynsetConvertor.OverWriteByMongo(aSynset,"");
			
			//System.out.println("*******"+list.size());
			model.addAttribute("hyponymsList", list);
			model.addAttribute("parentsList", parents);
			model.addAttribute("synset", castMainSynset);
			model.addAttribute("type", type);
			model.addAttribute("breadCrumb", breadCrumb);
			return "ViewSynset";
		} else {
			return "error";
		}

	}

	
	@RequestMapping(method = RequestMethod.GET, params = { "action=ViewSynset", "type=adv" })
	public String viewAdvSynset(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "type", required = false) String type, ModelMap model) {

		if (id != null && !"".equals(id)) {
			BreadCrumb breadCrumb = new BreadCrumb(Long.parseLong(id), POS.ADVERB);
			Dictionary dict = WordNetDictionary.getInstance();
			Synset synset = null;
			try {
				synset = dict.getSynsetAt(POS.ADVERB, Long.parseLong(id));
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

			List<AdverbSynset[]> list = new ArrayList<AdverbSynset[]>();
			List<AdverbSynset[]> parents = new ArrayList<AdverbSynset[]>();

			//System.out.println("**********" + nodeList.size());

			if (nodeList.size() > 0) {

				for (int i = 0; i < nodeList.size(); i++) {
					AdverbSynset[] advsynsetArr = new AdverbSynset[2];
					PointerTargetNode node = (PointerTargetNode) nodeList.get(i);
					PointerTarget target = node.getPointerTarget();
					Synset s = (Synset) target;
					AdverbSynset tempAdv = new AdverbSynset(s);
					SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
					AdverbSynset castSynset = (AdverbSynset) mongoSynsetConvertor.OverWriteByMongo(tempAdv,"");
					//NounSynset castSynset = new NounSynset();
					advsynsetArr[0] = tempAdv;
					advsynsetArr[1] = castSynset;

					list.add(advsynsetArr);
				}

			}
			
			if (parentsList.size() > 0) {
				
				for (int i = 0; i < parentsList.size(); i++) {
					
					AdverbSynset[] advsynsetArr = new AdverbSynset[2];
					PointerTargetNode node = (PointerTargetNode) parentsList.get(i);
					PointerTarget target = node.getPointerTarget();
					Synset s = (Synset) target;
					AdverbSynset tempAdv = new AdverbSynset(s);
					SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
					AdverbSynset castSynset = (AdverbSynset) mongoSynsetConvertor.OverWriteByMongo(tempAdv,"");
					//NounSynset castSynset = new NounSynset();
					advsynsetArr[0] = tempAdv;
					advsynsetArr[1] = castSynset;

					parents.add(advsynsetArr);
				}
			}

			AdverbSynset aSynset = new AdverbSynset(synset);
			SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
			AdverbSynset castMainSynset = (AdverbSynset) mongoSynsetConvertor.OverWriteByMongo(aSynset,"");
			
			//System.out.println("*******"+list.size());
			model.addAttribute("hyponymsList", list);
			model.addAttribute("parentsList", parents);
			model.addAttribute("synset", castMainSynset);
			model.addAttribute("type", type);
			model.addAttribute("breadCrumb", breadCrumb);
			return "ViewSynset";
		} else {
			return "error";
		}

	}
	
	
}
