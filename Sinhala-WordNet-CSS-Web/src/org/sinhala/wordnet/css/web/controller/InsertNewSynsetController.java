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
import org.sinhala.wordnet.css.web.model.BreadCrumb;
import org.sinhala.wordnet.wordnetDB.core.SinhalaSynsetMongoSynsetConvertor;
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

	@RequestMapping(method = RequestMethod.GET, params = { "action=ShowInsertNewSynset", "type=noun" })
	public String showInsertNewSynset(ModelMap model, @RequestParam(value = "type", required = false) String type) {

			List<SinhalaWordNetWord> wordList = new ArrayList<SinhalaWordNetWord>();
			SinhalaWordNetWord word = new SinhalaWordNetWord();
			word.setId("0");
			wordList.add(word);
			
			NounSynset castSynset = new NounSynset();
			castSynset.setOffset(0);
			//castSynset.setWords(wordList);
			model.addAttribute("synset", castSynset);
			model.addAttribute("type", type);
			

			return "InsetNewSynsets";
		
	}

	@RequestMapping(method = RequestMethod.POST)
	public String insetNewSynsets(@ModelAttribute NounSynset synset, ModelMap model) {

		

		SinhalaWordNetSynset CommSynset = synset;
		
		List<SinhalaWordNetWord> words = synset.getWords();
		

		for (int i = 0; i < words.size(); i++) {
			if (words.get(i).isLemmaNull()) {
				words.remove(i);
			}
		}

		
		

		SynsetMongoDbHandler synsetdb = new SynsetMongoDbHandler();
		synsetdb.addSynset(synset);
		// App app = new App();
		// synsetdb.test();

		// model.addAttribute("synset", synset);

//		return showInsertNewSynset(String.valueOf(synset.getOffset()), model, "noun");
		return "suceeded";
	
		
	}
}
