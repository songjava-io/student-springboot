package kr.songjava.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping(value = { "/", "/home", "/main " })
	public String home(Model model) {
		model.addAttribute("age", 38);
		return "home";
	}
}
