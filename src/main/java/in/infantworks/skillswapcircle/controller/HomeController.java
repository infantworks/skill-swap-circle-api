package in.infantworks.skillswapcircle.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping("/")
	public String welcomeToSkillSwapCircle() {
		return "The SKILL SWAP CIRCLE application is working";
	}
	
	
	@RequestMapping("/home")
	public String home() {
		return "Welcome to SKILL SWAP CIRCLE";
	}
	
}
