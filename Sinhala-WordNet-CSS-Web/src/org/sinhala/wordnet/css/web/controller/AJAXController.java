package org.sinhala.wordnet.css.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.sinhala.wordnet.css.model.wordnet.NounSynset;
import org.sinhala.wordnet.css.model.wordnet.NounWord;
import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetWord;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ajax")
public class AJAXController {

	@RequestMapping(method = RequestMethod.GET, headers = "Accept = application/json", params = {
			"action=getRoots"})
	public @ResponseBody List<SinhalaWordNetWord> returnNounRoot(){
		
		// load from mongo DB
		List<SinhalaWordNetWord> list = new ArrayList<SinhalaWordNetWord>();
		
		list.add(new SinhalaWordNetWord("1", "abcd", null, null, null, null, null));
		list.add(new SinhalaWordNetWord("2", "abcdsf", null, null, null, null, null));
		list.add(new SinhalaWordNetWord("2", "absfg", null, null, null, null, null));
		list.add(new SinhalaWordNetWord("1", "gdgcd", null, null, null, null, null));
		list.add(new SinhalaWordNetWord("3", "bcdjjtr", null, null, null, null, null));
		
		return list;
		
	}
}
