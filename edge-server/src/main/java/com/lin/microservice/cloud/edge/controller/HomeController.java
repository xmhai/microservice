package com.lin.microservice.cloud.edge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping(value = {"/home", "/", "/index"})
    public String index(Model model) {
        return "home";
    }	

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }	

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}
}
