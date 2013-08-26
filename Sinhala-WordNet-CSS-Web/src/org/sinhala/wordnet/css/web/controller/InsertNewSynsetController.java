package org.sinhala.wordnet.css.web.controller;

import java.util.ArrayList;
import java.util.List;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;
import net.didion.jwnl.dictionary.Dictionary;

import org.sinhala.wordnet.css.jwnl.WordNetDictionary;
import org.sinhala.wordnet.css.model.wordnet.NounSynset;
import org.sinhala.wordnet.css.model.wordnet.NounWord;
import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetSynset;
import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetWord;
import org.sinhala.wordnet.css.utils.maduraapi.MeaningRequestHandler;
import org.sinhala.wordnet.wordnetDB.core.SynsetMongoDbHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/InsetNewSynset")
public class InsertNewSynsetController {

	@RequestMapping(method = RequestMethod.GET, params = {
			"action=ShowInsertNewSynset", "type=noun" })
	public String ShowInsertNewSynset(
			@RequestParam(value = "id", required = false) String id,
			ModelMap model,
			@RequestParam(value = "type", required = false) String type) {

		if (id != null && !"".equals(id)) {
			
			NounSynset castSynset = new NounSynset();
//			castSynset.setId(id);
			castSynset.setOffset(0);
			List<SinhalaWordNetWord> wordList = new ArrayList<SinhalaWordNetWord>();
			NounWord newWord = new NounWord();
			newWord.setId("0");
			newWord.setLemma("insert1");
			wordList.add(newWord);
			
			castSynset.setWords(wordList);
			
			System.out.println(castSynset.getWords().get(0).getLemma());
//			NounWord newWord = new NounWord();
//			((NounSynset) castSynset).insertWordX(newWord);
			
		

			// override cast synset with database values here
//
//			MeaningRequestHandler meaningRequestHandler = new MeaningRequestHandler();
//			List<String> wordList = castSynset.getWordArrayList();
//			List<List<String>> meaningsList = meaningRequestHandler
//					.getMeaningLists(wordList);
//			List<String> intersection = meaningRequestHandler
//					.getIntersection(meaningsList);

			model.addAttribute("synset", castSynset);
//			model.addAttribute("meaningsList", meaningsList);
//			model.addAttribute("intersection", intersection);
			model.addAttribute("wordList", wordList);
			model.addAttribute("type", type);

			return "InsetNewSynsets";
		}

		else {
			return "error";
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public String insertSynset(@ModelAttribute SinhalaWordNetSynset synset,
			ModelMap model) {
		
		System.out.println("inside sending");
				
		SynsetMongoDbHandler synsetdb = new SynsetMongoDbHandler();
		synsetdb.addNounSynset((NounSynset) synset);
	
		
		return "Inserted a synset successfully";
	
		
	}
}
