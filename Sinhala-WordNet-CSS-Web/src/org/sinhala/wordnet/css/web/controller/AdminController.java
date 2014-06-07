package org.sinhala.wordnet.css.web.controller;

import java.util.List;

import org.sinhala.wordnet.css.model.wordnet.NounSynset;
import org.sinhala.wordnet.wordnetDB.core.UserDBHandler;
import org.sinhala.wordnet.wordnetDB.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/AdminOptions")
public class AdminController {

	@RequestMapping(method = RequestMethod.GET, params = { "action=ShowUsers" })
	public String showUsers(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); //get logged in username
		UserDBHandler dbHandler = new UserDBHandler();
		List<User> userList = dbHandler.getUsers(name);
		model.addAttribute("userList", userList);
		return "AdminUser";
	}
	
	@RequestMapping(method = RequestMethod.GET, params = { "action=ShowEditUsers" })
	public String showEditUsers(@RequestParam(value = "username", required = true) String username, ModelMap model) {
		UserDBHandler dbHandler = new UserDBHandler();
		User user = dbHandler.getUserByUsername(username);
		
		model.addAttribute("user", user);
		
		return "EditUser";
	}
	
	@RequestMapping(method = RequestMethod.POST )
	public String editUser(@ModelAttribute User user, ModelMap model) {
		UserDBHandler dbHandler = new UserDBHandler();
		dbHandler.editUser(user);
		return showUsers(model);
	}
}
