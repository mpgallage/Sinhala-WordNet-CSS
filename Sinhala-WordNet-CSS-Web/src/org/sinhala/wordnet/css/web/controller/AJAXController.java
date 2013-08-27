package org.sinhala.wordnet.css.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.sinhala.wordnet.css.model.wordnet.NounSynset;
import org.sinhala.wordnet.css.model.wordnet.NounWord;
import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetWord;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaWord;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Ajax")
public class AJAXController {

	@RequestMapping(method = RequestMethod.GET, params = {
			"action=getRoots"})
	public @ResponseBody List<MongoSinhalaWord> returnNounRoot(){
		
		// load from mongo DB
		List<MongoSinhalaWord> list = new ArrayList<MongoSinhalaWord>();
		
		list.add(new MongoSinhalaWord("aaaa", "1", null));
		list.add(new MongoSinhalaWord("abdfg", "1", null));
		list.add(new MongoSinhalaWord("abfdg", "1", null));
		list.add(new MongoSinhalaWord("acrfs", "1", null));
		list.add(new MongoSinhalaWord("bsfgd", "1", null));
		
		return list;
		
	}
}
