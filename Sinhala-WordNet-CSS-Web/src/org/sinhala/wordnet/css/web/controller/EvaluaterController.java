package org.sinhala.wordnet.css.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.sinhala.wordnet.css.model.wordnet.NounSynset;
import org.sinhala.wordnet.wordnetDB.core.SynsetMongoDbHandler;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaNoun;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/EvaluaterMode")
public class EvaluaterController {
	
	@RequestMapping(method = RequestMethod.GET, params = { "action=ShowEvaluater", "type=noun" })
	public String showRoot(ModelMap model) {
		
		List<MongoSinhalaNoun> list = new ArrayList<MongoSinhalaNoun>();
		SynsetMongoDbHandler synMongoDbHandler = new SynsetMongoDbHandler();
		list = synMongoDbHandler.findAllNotEvaluatedNoun();
		System.out.println("list size"+list.size());
		
		model.addAttribute("synsetList", list);
		return "Evaluater";
	}

}
