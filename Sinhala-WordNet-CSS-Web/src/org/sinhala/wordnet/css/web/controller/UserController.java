package org.sinhala.wordnet.css.web.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.sinhala.wordnet.css.common.mail.MailHandler;
import org.sinhala.wordnet.wordnetDB.core.CustomUserDetailsService;
import org.sinhala.wordnet.wordnetDB.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/SignUp")
public class UserController {

	@RequestMapping(method = RequestMethod.GET, params = "action=Register")
	public String showSignUp(ModelMap model) {

		User user = new User();
		model.addAttribute("user", user);

		return "signup";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String SignUp(@ModelAttribute User user, ModelMap model) {

		CustomUserDetailsService cuds = new CustomUserDetailsService();
		if (cuds.isUsernameExist(user.getUsername())) {
			model.addAttribute("error", "Username already exist");
			user.setUsername("");
			model.addAttribute("user", user);
			return "signup";
		}
		
		if ("".equals(user.getPassword()) || user.getPassword() == null) {
			model.addAttribute("error", "Password Cannot be empty.");
			model.addAttribute("user", user);
			return "signup";
		}

		if (cuds.isEmailExist(user.getEmail())) {
			model.addAttribute("error", "Email already exist");
			user.setEmail("");
			model.addAttribute("user", user);
			return "signup";
		}

		user.setRole(3);
		String key = RandomStringUtils.random(16, true, true);
		user.setKey(key);
		cuds.addUserDetail(user);

		String from = "no-reply@wordnet.lk";
		String to = user.getEmail();
		String subject = "Wordnet.lk account verification";
		String content = "<p>Thank you for registering on woordnet.lk. Please click the link below to confirm your account.</p><br>"
				+ "<p><a href=\"http://www.wordnet.lk/crowdsource/Verify?key=" + key + "\" >" + "http://www.wordnet.lk/crowdsource/Verify?key=" + key + "</a></p>";
		
		MailHandler.sendMail(to, from, subject, content);

		model.addAttribute("success", "Please check your email account for a confirmation email.");
		model.addAttribute("error", "");
		return "confirm_reg";
	}

}
