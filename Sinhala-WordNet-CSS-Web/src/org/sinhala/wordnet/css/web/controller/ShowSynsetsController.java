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
import org.sinhala.wordnet.css.model.wordnet.AdjectiveSynset;
import org.sinhala.wordnet.css.model.wordnet.NounSynset;
import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetSynset;
import org.sinhala.wordnet.css.model.wordnet.VerbSynset;
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
					NounSynset castSynset = mongoSynsetConvertor.OverWriteByMongo(tempNoun,"");
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
					.OverWriteByMongo(nounSynset,"");
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
			NounSynset castSynset = mongoSynsetConvertor.OverWriteByMongo(tempNoun,"");
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
					.OverWriteByMongo(nounSynset,"");
			model.addAttribute("enSynset", enSynset);

			model.addAttribute("breadCrumb", breadCrumb);
			return "ShowHyponyms";
		}
	}
	@RequestMapping(method = RequestMethod.GET, params = { "action=ShowHyponyms", "type=verb" })
	public String showVerbHyponyms(@RequestParam(value = "id", required = false) String id, ModelMap model, @RequestParam(value = "type", required = false) String type) {
		if (id != null && !"".equalsIgnoreCase(id)) {
			Dictionary dict = WordNetDictionary.getInstance();
			Synset synset = null;
			try {
				synset = dict.getSynsetAt(POS.VERB, Long.parseLong(id));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			BreadCrumb breadCrumb = new BreadCrumb();
			try{
				breadCrumb = new BreadCrumb(synset.getOffset(), POS.VERB);
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

				List<VerbSynset[]> list = new ArrayList<VerbSynset[]>();
				List<Boolean> nextLevelList = new ArrayList<Boolean>();

				for (int i = 0; i < nodeList.size(); i++) {
					VerbSynset[] verbsynsetArr = new VerbSynset[2];
					PointerTargetNode node = (PointerTargetNode) nodeList.get(i);
					PointerTarget target = node.getPointerTarget();
					Synset s = (Synset) target;
					VerbSynset tempVerb = new VerbSynset(s);
					SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
					VerbSynset castSynset = mongoSynsetConvertor.OverWriteByMongo(tempVerb,"");
					// NounSynset castSynset = new NounSynset();
					verbsynsetArr[0] = tempVerb;
					verbsynsetArr[1] = castSynset;

					list.add(verbsynsetArr);
					
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
			
			VerbSynset verbSynset = new VerbSynset(synset);
			SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();

			VerbSynset enSynset = mongoSynsetConvertor
					.OverWriteByMongo(verbSynset,"");
			model.addAttribute("enSynset", enSynset);
			
			model.addAttribute("breadCrumb", breadCrumb);
			SearchWord searchWord = new SearchWord();
			model.addAttribute("searchWord", searchWord);
			return "SearchSynset";
			
		} else {
			
			BreadCrumb breadCrumb = new BreadCrumb(POS.VERB);
			Dictionary dict = WordNetDictionary.getInstance();
			List<VerbSynset> verbSynsets = new ArrayList<VerbSynset>();
			Synset synset = null;
			List<VerbSynset[]> list = new ArrayList<VerbSynset[]>();
			Iterator<Synset> allVerbSynsets =null;
			PointerUtils pointerUtils = null;
			try {
				allVerbSynsets= dict.getSynsetIterator(POS.VERB);
				pointerUtils = PointerUtils.getInstance();
				
				
								
				
				synset = dict.getSynsetAt(POS.VERB, 1740);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int i=0;
			List<Boolean> nextLevelList = new ArrayList<Boolean>();
			while(allVerbSynsets.hasNext()){
				PointerTargetNodeList nodeList = new PointerTargetNodeList();
				Synset tempSynset = allVerbSynsets.next();
				VerbSynset tempVerb = new VerbSynset(tempSynset);
				try {
					nodeList = pointerUtils.getDirectHypernyms(tempSynset);
					
				} catch (JWNLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				VerbSynset[] verbsynsetArr = new VerbSynset[2];
				if(nodeList.size()==0){
					
					verbSynsets.add(tempVerb);
					SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
					VerbSynset castSynset = mongoSynsetConvertor.OverWriteByMongo(tempVerb,"");
					//VerbSynset castSynset = tempVerb;
					verbsynsetArr[0] = tempVerb;
					verbsynsetArr[1] = castSynset;
					list.add(verbsynsetArr);
					
					
					
					PointerTargetNodeList subNodeList = null;
					try {
						subNodeList = pointerUtils.getDirectHyponyms(tempSynset);
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
				if(i == 1000){
					break;
				}
				
				i++;
				
			}

			
			
			//System.out.println("count"+i);
			
			//VerbSynset[] verbsynsetArr = new VerbSynset[2];
			
			
			// NounSynset castSynset = new NounSynset();
			

			
			
			

			model.addAttribute("synsetList", list);
			model.addAttribute("type", type);
			model.addAttribute("nextLevelList", nextLevelList);
			
			VerbSynset verbSynset = new VerbSynset(synset);
			SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();

			VerbSynset enSynset = mongoSynsetConvertor
					.OverWriteByMongo(verbSynset,"");
			model.addAttribute("enSynset", enSynset);

			model.addAttribute("breadCrumb", breadCrumb);
			
			SearchWord searchWord = new SearchWord();
			model.addAttribute("searchWord", searchWord);
			
			
			
			return "SearchSynset";
		}
		}
	@RequestMapping(method = RequestMethod.GET, params = { "action=ShowHyponyms", "type=adj" })
	public String showAdjHyponyms(@RequestParam(value = "id", required = false) String id, ModelMap model, @RequestParam(value = "type", required = false) String type) {
		if (id != null && !"".equalsIgnoreCase(id)) {
			Dictionary dict = WordNetDictionary.getInstance();
			Synset synset = null;
			try {
				synset = dict.getSynsetAt(POS.ADJECTIVE, Long.parseLong(id));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			BreadCrumb breadCrumb = new BreadCrumb();
			try{
				breadCrumb = new BreadCrumb(synset.getOffset(), POS.ADJECTIVE);
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

				List<AdjectiveSynset[]> list = new ArrayList<AdjectiveSynset[]>();
				List<Boolean> nextLevelList = new ArrayList<Boolean>();

				for (int i = 0; i < nodeList.size(); i++) {
					AdjectiveSynset[] adjsynsetArr = new AdjectiveSynset[2];
					PointerTargetNode node = (PointerTargetNode) nodeList.get(i);
					PointerTarget target = node.getPointerTarget();
					Synset s = (Synset) target;
					AdjectiveSynset tempAdj = new AdjectiveSynset(s);
					SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
					AdjectiveSynset castSynset = mongoSynsetConvertor.OverWriteByMongo(tempAdj,"");
					// NounSynset castSynset = new NounSynset();
					adjsynsetArr[0] = tempAdj;
					adjsynsetArr[1] = castSynset;

					list.add(adjsynsetArr);
					
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
			
			AdjectiveSynset adjSynset = new AdjectiveSynset(synset);
			SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();

			AdjectiveSynset enSynset = mongoSynsetConvertor
					.OverWriteByMongo(adjSynset,"");
			model.addAttribute("enSynset", enSynset);
			
			model.addAttribute("breadCrumb", breadCrumb);
			return "ShowHyponyms";
			
		} else {
			
			BreadCrumb breadCrumb = new BreadCrumb(POS.ADJECTIVE);
			Dictionary dict = WordNetDictionary.getInstance();
			List<AdjectiveSynset> adjSynsets = new ArrayList<AdjectiveSynset>();
			Synset synset = null;
			List<AdjectiveSynset[]> list = new ArrayList<AdjectiveSynset[]>();
			Iterator<Synset> allAdjSynsets =null;
			PointerUtils pointerUtils = null;
			try {
				allAdjSynsets= dict.getSynsetIterator(POS.ADJECTIVE);
				pointerUtils = PointerUtils.getInstance();
				
				
								
				
				synset = dict.getSynsetAt(POS.ADJECTIVE, 1740);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int i=0;
			List<Boolean> nextLevelList = new ArrayList<Boolean>();
			while(allAdjSynsets.hasNext()){
				PointerTargetNodeList nodeList = new PointerTargetNodeList();
				Synset tempSynset = allAdjSynsets.next();
				AdjectiveSynset tempVerb = new AdjectiveSynset(tempSynset);
				try {
					nodeList = pointerUtils.getDirectHypernyms(tempSynset);
					
				} catch (JWNLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				AdjectiveSynset[] adjsynsetArr = new AdjectiveSynset[2];
				if(nodeList.size()==0){
					
					adjSynsets.add(tempVerb);
					SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
					AdjectiveSynset castSynset = mongoSynsetConvertor.OverWriteByMongo(tempVerb,"");
					//VerbSynset castSynset = tempVerb;
					adjsynsetArr[0] = tempVerb;
					adjsynsetArr[1] = castSynset;
					list.add(adjsynsetArr);
					
					
					
					PointerTargetNodeList subNodeList = null;
					try {
						subNodeList = pointerUtils.getDirectHyponyms(tempSynset);
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
				if(i == 50){
					break;
				}
				
				i++;
				
			}

			
			
			//System.out.println("count"+i);
			
			//VerbSynset[] verbsynsetArr = new VerbSynset[2];
			
			
			// NounSynset castSynset = new NounSynset();
			

			
			
			

			model.addAttribute("synsetList", list);
			model.addAttribute("type", type);
			model.addAttribute("nextLevelList", nextLevelList);
			
			AdjectiveSynset adjSynset = new AdjectiveSynset(synset);
			SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();

			AdjectiveSynset enSynset = mongoSynsetConvertor
					.OverWriteByMongo(adjSynset,"");
			model.addAttribute("enSynset", enSynset);

			model.addAttribute("breadCrumb", breadCrumb);
			return "ShowHyponyms";
		}
		}
		@RequestMapping(method = RequestMethod.GET, params = { "action=ShowHyponyms"})
		public String showHyponyms(ModelMap model, @RequestParam(value = "type", required = false) String type){
			if("verb".equals(type) || "adj".equals(type) || "adv".equals(type)){
				return "underConstruction";
			}
			else{
				return "error";

			}
		}
		

}
