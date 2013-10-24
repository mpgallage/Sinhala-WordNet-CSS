package org.sinhala.wordnet.css.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.PointerTarget;
import net.didion.jwnl.data.PointerUtils;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.list.PointerTargetNode;
import net.didion.jwnl.data.list.PointerTargetNodeList;
import net.didion.jwnl.dictionary.Dictionary;
import net.didion.jwnl.util.TypeCheckingList;

import org.sinhala.wordnet.css.jwnl.WordNetDictionary;
import org.sinhala.wordnet.css.model.wordnet.NounSynset;
import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetSynset;
import org.sinhala.wordnet.css.model.wordnet.VerbSynset;
import org.sinhala.wordnet.css.web.model.BreadCrumb;
import org.sinhala.wordnet.wordnetDB.core.SinhalaSynsetMongoSynsetConvertor;
import org.sinhala.wordnet.wordnetDB.core.SynsetMongoDbHandler;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaSynset;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaVerb;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/SearchWordController")
public class SearchWordController {

	@RequestMapping(method = RequestMethod.GET, params = "action=Search")
	public String showSignUp(ModelMap model) {

		SearchWord searchWord = new SearchWord();
		model.addAttribute("searchWord", searchWord);

		return "signup";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String search(@ModelAttribute SearchWord searchWord, ModelMap model) {
		/**
		 * Searching block : English
		 */

		System.out.println(searchWord.getRawWord());
		System.out.println(searchWord.getPos());

		String type = searchWord.getPOS();
		POS pos = searchWord.getPos();

		model.addAttribute("type", type);

		PointerUtils pointerUtils = null;
		IndexWord indexWord = null;
		long[] offsetArray = null;
		Synset synset = null;
		long parentOffset = 0;

		BreadCrumb breadCrumb = new BreadCrumb(searchWord.getPos());
		List<SinhalaWordNetSynset[]> list = new ArrayList<SinhalaWordNetSynset[]>();// ************************
		List<Boolean> nextLevelList = new ArrayList<Boolean>();// ************************

		String theWord = searchWord.getCleanedWord();
		Dictionary dict = WordNetDictionary.getInstance();

		if (searchWord.isSinhala()) {
			// search for Sinhala synsets
			SynsetMongoDbHandler dbHandler = new SynsetMongoDbHandler();
			Collection<Long> offsetCollection = dbHandler.findSynsetIDByLemma(
					theWord, pos);

			if (offsetCollection.size() < 1) {
				return "error";
			} else {
				offsetArray = new long[offsetCollection.size()];
				for (int i = 0; i < offsetCollection.size(); i++) {
					offsetArray[i] = offsetCollection.iterator().next()
							.longValue();
				}
			}
		} else {
			// search for English synsets
			try {
				indexWord = dict.lookupIndexWord(pos, theWord);
			} catch (JWNLException e1) {
				e1.printStackTrace();
			}
			offsetArray = indexWord.getSynsetOffsets();
		}

		if (offsetArray.length < 1) {
			return "error";
		} else {

			try {
				pointerUtils = PointerUtils.getInstance();
				synset = dict.getSynsetAt(searchWord.getPos(), offsetArray[0]);

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (JWNLException e) {
				e.printStackTrace();
			}

			// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxxx
			for (int i = 0; i < offsetArray.length; i++) {
				// PointerTargetNodeList nodeList = new PointerTargetNodeList();
				Synset tempSynset = null;
				try {
					tempSynset = dict.getSynsetAt(searchWord.getPos(),
							offsetArray[i]);
				} catch (JWNLException e) {

					e.printStackTrace();
				}
				SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
				SinhalaWordNetSynset[] sinhalaSynArray = new SinhalaWordNetSynset[2];
				
				SinhalaWordNetSynset tempVerb =null;
				SinhalaWordNetSynset castSynset =null;

				if (pos == POS.NOUN) {

				} else if (pos == POS.VERB) {
					tempVerb = new VerbSynset(tempSynset);
					 castSynset = (SinhalaWordNetSynset) mongoSynsetConvertor
							.OverWriteByMongo((VerbSynset) tempVerb,"");

				} else if (pos == POS.ADJECTIVE) {

				} else if (pos == POS.ADVERB) {

				}

				 

				sinhalaSynArray[0] = tempVerb;
				sinhalaSynArray[1] = castSynset;
				list.add(sinhalaSynArray);

				PointerTargetNodeList subNodeList = null;
				try {
					subNodeList = pointerUtils.getDirectHyponyms(tempSynset);
				} catch (JWNLException e) {
					e.printStackTrace();
				}
				if (subNodeList.size() > 0) {
					nextLevelList.add(true);
				} else {
					nextLevelList.add(false);
				}

				if (i == 1000) {
					break;
				}

			}
			// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxx
			// PointerTargetNodeList listn = null;
			// try {
			// listn = pointerUtils.getDirectHypernyms(synset);
			// } catch (JWNLException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			parentOffset = offsetArray[0];
		}

		/**
		 * Search : Sinhala
		 */

		model.addAttribute("synsetList", list);
		// String type = "verb";
		model.addAttribute("type", type);
		model.addAttribute("nextLevelList", nextLevelList);

		VerbSynset verbSynset = new VerbSynset(synset);
		SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
		VerbSynset enSynset = mongoSynsetConvertor.OverWriteByMongo(verbSynset,"");

		model.addAttribute("enSynset", enSynset);
		model.addAttribute("breadCrumb", breadCrumb);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("parent", parentOffset);

		return "SearchSynset";

	}

	@RequestMapping(method = RequestMethod.GET, params = {
			"action=ShowHyponyms", "type=verb" })
	public String showVerbHyponyms(
			@RequestParam(value = "id", required = false) String id,
			ModelMap model,
			@RequestParam(value = "type", required = false) String type) {
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
			try {
				breadCrumb = new BreadCrumb(synset.getOffset(), POS.VERB);
			} catch (Exception e) {
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
					PointerTargetNode node = (PointerTargetNode) nodeList
							.get(i);
					PointerTarget target = node.getPointerTarget();
					Synset s = (Synset) target;
					VerbSynset tempVerb = new VerbSynset(s);
					SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
					VerbSynset castSynset = mongoSynsetConvertor
							.OverWriteByMongo(tempVerb,"");
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

				model.addAttribute("synsetList",
						new ArrayList<SinhalaWordNetSynset>());
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
			Iterator<Synset> allVerbSynsets = null;
			PointerUtils pointerUtils = null;
			try {
				allVerbSynsets = dict.getSynsetIterator(POS.VERB);
				pointerUtils = PointerUtils.getInstance();

				synset = dict.getSynsetAt(POS.VERB, 1740);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int i = 0;
			List<Boolean> nextLevelList = new ArrayList<Boolean>();
			while (allVerbSynsets.hasNext()) {
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
				if (nodeList.size() == 0) {

					verbSynsets.add(tempVerb);
					SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
					VerbSynset castSynset = mongoSynsetConvertor
							.OverWriteByMongo(tempVerb,"");
					// VerbSynset castSynset = tempVerb;
					verbsynsetArr[0] = tempVerb;
					verbsynsetArr[1] = castSynset;
					list.add(verbsynsetArr);

					PointerTargetNodeList subNodeList = null;
					try {
						subNodeList = pointerUtils
								.getDirectHyponyms(tempSynset);
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
				if (i == 1000) {
					break;
				}

				i++;

			}

			// System.out.println("count"+i);

			// VerbSynset[] verbsynsetArr = new VerbSynset[2];

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

}
