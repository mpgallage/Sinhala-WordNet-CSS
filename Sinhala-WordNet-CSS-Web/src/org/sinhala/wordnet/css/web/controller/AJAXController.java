package org.sinhala.wordnet.css.web.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.sinhala.wordnet.css.model.wordnet.NounSynset;
import org.sinhala.wordnet.css.model.wordnet.NounWord;
import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetWord;
import org.sinhala.wordnet.wordnetDB.core.SynsetMongoDbHandler;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaRoot;
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
	public @ResponseBody List<MongoSinhalaRoot> returnNounRoot(@RequestParam(value = "term", required = false) String term){
		
		byte ptext[] =null;
		//byte ptext1[] =null;
		String value=null;
		//lemma.getBytes()
		try {
			//ptext = lemma.getBytes();
			ptext = term.getBytes("ISO8859_1");
			value = new String(ptext, Charset.forName("UTF-8"));
			//value = new String(ptext, "UTF-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// load from mongo DB
		List<MongoSinhalaRoot> list = new ArrayList<MongoSinhalaRoot>();
		
		list = new SynsetMongoDbHandler().findAllRoots();
				
		
		List<MongoSinhalaRoot> returnList = new ArrayList<MongoSinhalaRoot>();
		
		for(MongoSinhalaRoot w : list){
			if(w.getWords().get(0).getLemma().startsWith(value)){
				returnList.add(w);
			}
		}
		
		return returnList;
		
	}
}
