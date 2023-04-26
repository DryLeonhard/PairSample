package org.ssafy.sample;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "index.html";
	}
	/*
	@GetMapping("/member/join")
	public String join() {
		return "/member/join.html";
	}
	
	@GetMapping("/member/login")
	public String login() {
		return "index.html";
	}
	
	@GetMapping("/board/list")
	public String boardList() {
		return "/board/list.html";
	}
	
	@GetMapping("/board/detail")
	public String boardDetail(@RequestParam long id) {
		return "/board/view.html";
	}
	
	@GetMapping("/board/modify")
	public String modifyBoard(@RequestParam long id) {
		return "/board/modify.html";
	}
	
	@GetMapping("board/write")
	public String writeBoard() {
		return "/board/write.html";
	}
	*/
}
