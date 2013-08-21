package org.sinhala.wordnet.css.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/EditRelationship")
public class EditRelationshipController {
	
	@RequestMapping(method = RequestMethod.GET, params = { "action=EditRelationship", "type=noun" })
	public String editRelationship(@RequestParam(value = "id", required = false) String id, ModelMap model, @RequestParam(value = "type", required = false) String type) {
		
		
		return "EditRelationship";
		
	}

}
