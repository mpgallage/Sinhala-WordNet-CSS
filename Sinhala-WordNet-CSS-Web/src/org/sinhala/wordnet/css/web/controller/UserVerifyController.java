package org.sinhala.wordnet.css.web.controller;

import org.sinhala.wordnet.wordnetDB.core.CustomUserDetailsService;
import org.sinhala.wordnet.wordnetDB.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Verify")
public class UserVerifyController {

	@RequestMapping(method = RequestMethod.GET)
	public String veryfySignup(
			@RequestParam(value = "key", required = true) String key,
			ModelMap model) {

		CustomUserDetailsService cuds = new CustomUserDetailsService();
		User user = cuds.getUserDetailByKey(key);

		if (user != null) {
			if (!user.isVerified()) {
				cuds.confirmUser(user);
				model.addAttribute("msg",
						"Successfully verified account. Please login.");
			} else {
				model.addAttribute("msg",
						"This user has already verified his/her account.");
			}
		} else {
			model.addAttribute("msg",
					"Bad Request. Please try again with a valid request.");
		}

		return "confirm_reg";
	}
}
