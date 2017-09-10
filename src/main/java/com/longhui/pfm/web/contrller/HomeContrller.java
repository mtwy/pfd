package com.longhui.pfm.web.contrller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("webHomeContrller")
@RequestMapping("/web")
public class HomeContrller {

	@GetMapping("/tohome")
	public String index(){
		return "web/home";
	}
}
