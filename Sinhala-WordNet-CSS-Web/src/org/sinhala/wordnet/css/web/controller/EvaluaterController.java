package org.sinhala.wordnet.css.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.sinhala.wordnet.css.model.wordnet.NounSynset;
import org.sinhala.wordnet.wordnetDB.core.SynsetMongoDbHandler;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaAdjective;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaNoun;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaVerb;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/EvaluaterMode")
public class EvaluaterController {
	
	@RequestMapping(method = RequestMethod.GET, params = { "action=ShowEvaluater", "type=noun" , "mode=evaluated" })
	public String nounEvaluaterMode(ModelMap model) {
		
		List<MongoSinhalaNoun> list = new ArrayList<MongoSinhalaNoun>();
		SynsetMongoDbHandler synMongoDbHandler = new SynsetMongoDbHandler();
		list = synMongoDbHandler.findAllEvaluatedNoun();
		
		
		model.addAttribute("synsetList", list);
		model.addAttribute("type", "noun");
		return "Evaluater";
	}
	@RequestMapping(method = RequestMethod.GET, params = { "action=ShowEvaluater", "type=noun" , "mode=all" })
	public String nounEvaluaterAllMode(ModelMap model) {
		
		List<MongoSinhalaNoun> list = new ArrayList<MongoSinhalaNoun>();
		SynsetMongoDbHandler synMongoDbHandler = new SynsetMongoDbHandler();
		list = synMongoDbHandler.findAllEditedNoun();
		
		
		model.addAttribute("synsetList", list);
		model.addAttribute("type", "noun");
		return "Evaluater";
	}
	@RequestMapping(method = RequestMethod.GET, params = { "action=ShowEvaluater", "type=noun" , "mode=notevaluated" })
	public String nounEvaluaterNotEvaluatedMode(ModelMap model) {
		
		List<MongoSinhalaNoun> list = new ArrayList<MongoSinhalaNoun>();
		SynsetMongoDbHandler synMongoDbHandler = new SynsetMongoDbHandler();
		list = synMongoDbHandler.findAllNotEvaluatedNoun();
		
		
		model.addAttribute("synsetList", list);
		model.addAttribute("type", "noun");
		return "Evaluater";
	}
	
	@RequestMapping(method = RequestMethod.GET, params = { "action=ShowEvaluater", "type=verb" , "mode=evaluated"})
	public String verbEvaluaterMode(ModelMap model) {
		
		List<MongoSinhalaVerb> list = new ArrayList<MongoSinhalaVerb>();
		SynsetMongoDbHandler synMongoDbHandler = new SynsetMongoDbHandler();
		list = synMongoDbHandler.findAllEvaluatedVerb();
		
		
		model.addAttribute("synsetList", list);
		model.addAttribute("type", "verb");
		return "Evaluater";
	}
	@RequestMapping(method = RequestMethod.GET, params = { "action=ShowEvaluater", "type=verb" , "mode=all"})
	public String verbEvaluaterAllMode(ModelMap model) {
		
		List<MongoSinhalaVerb> list = new ArrayList<MongoSinhalaVerb>();
		SynsetMongoDbHandler synMongoDbHandler = new SynsetMongoDbHandler();
		list = synMongoDbHandler.findAlleditedVerb();
		
		
		model.addAttribute("synsetList", list);
		model.addAttribute("type", "verb");
		return "Evaluater";
	}
	@RequestMapping(method = RequestMethod.GET, params = { "action=ShowEvaluater", "type=verb" , "mode=notevaluated"})
	public String verbEvaluaterNotEvaluatedMode(ModelMap model) {
		
		List<MongoSinhalaVerb> list = new ArrayList<MongoSinhalaVerb>();
		SynsetMongoDbHandler synMongoDbHandler = new SynsetMongoDbHandler();
		list = synMongoDbHandler.findAllNotEvaluatedVerb();
		
		
		model.addAttribute("synsetList", list);
		model.addAttribute("type", "verb");
		return "Evaluater";
	}
	
	@RequestMapping(method = RequestMethod.GET, params = { "action=ShowEvaluater", "type=adj" ,"mode=evaluated" })
	public String adjEvaluaterMode(ModelMap model) {
		
		List<MongoSinhalaAdjective> list = new ArrayList<MongoSinhalaAdjective>();
		SynsetMongoDbHandler synMongoDbHandler = new SynsetMongoDbHandler();
		list = synMongoDbHandler.findAllEvaluatedAdj();
		
		
		model.addAttribute("synsetList", list);
		model.addAttribute("type", "adj");
		return "Evaluater";
	}
	@RequestMapping(method = RequestMethod.GET, params = { "action=ShowEvaluater", "type=adj" , "mode=all"})
	public String adjEvaluaterAllMode(ModelMap model) {
		
		List<MongoSinhalaAdjective> list = new ArrayList<MongoSinhalaAdjective>();
		SynsetMongoDbHandler synMongoDbHandler = new SynsetMongoDbHandler();
		list = synMongoDbHandler.findAllEditedAdj();
		
		
		model.addAttribute("synsetList", list);
		model.addAttribute("type", "adj");
		return "Evaluater";
	}
	@RequestMapping(method = RequestMethod.GET, params = { "action=ShowEvaluater", "type=adj" , "mode=notevaluated"})
	public String adjEvaluaterNotEvaluatedMode(ModelMap model) {
		
		List<MongoSinhalaAdjective> list = new ArrayList<MongoSinhalaAdjective>();
		SynsetMongoDbHandler synMongoDbHandler = new SynsetMongoDbHandler();
		list = synMongoDbHandler.findAllNotEvaluatedAdj();
		
		
		model.addAttribute("synsetList", list);
		model.addAttribute("type", "adj");
		return "Evaluater";
	}

}
