package by.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/goodbye")
	public String mainPersonnelDepartment() {
		return "goodbye";
	}

}
