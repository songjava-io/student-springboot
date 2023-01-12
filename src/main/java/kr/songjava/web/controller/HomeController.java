package kr.songjava.web.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.songjava.web.service.HomeService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final HomeService homeService;
	
	@GetMapping(value = { "/", "/home", "/main " })
	public String home(Model model) {
		
		homeService.print();
		
		model.addAttribute("age", 38);
		model.addAttribute("display", false);
		model.addAttribute("frameworks", Arrays.asList("Spring", 
				"Vue.js", "React"));
		return "home";
	}
}
