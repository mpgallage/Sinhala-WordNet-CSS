package org.sinhala.wordnet.css.web.controller;

import java.util.ArrayList;
import java.util.List;

import net.didion.jwnl.data.POS;

import org.sinhala.wordnet.css.model.wordnet.NounSynset;
import org.sinhala.wordnet.wordnetDB.core.SynsetMongoDbHandler;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaAdjective;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaNoun;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaSynset;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaVerb;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/EvaluaterMode")
public class EvaluaterController {
	
	@RequestMapping(method = RequestMethod.GET, params = { "action=ShowEvaluater" })
	public String nounEvaluaterMode(@RequestParam(value = "mode", required = false) String mode, ModelMap model,@RequestParam(value = "type", required = false) String type) {
		
		List<MongoSinhalaSynset> list = new ArrayList<MongoSinhalaSynset>();
		SynsetMongoDbHandler synMongoDbHandler = new SynsetMongoDbHandler();
		
		POS pos = POS.NOUN;
		if(type.equals("noun")){
			pos = POS.NOUN;
		}
		else if(type.equals("verb")){
			pos = POS.VERB;
			
		}
		else if(type.equals("adj")){
			pos = POS.ADJECTIVE;
		}
		list = synMongoDbHandler.findSynsets(pos,mode);
		
		
		model.addAttribute("synsetList", list);
		model.addAttribute("type", type);
		model.addAttribute("mode", mode);
		return "Evaluater";
	}
	
	

}
