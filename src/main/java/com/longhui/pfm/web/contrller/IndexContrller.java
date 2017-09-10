package com.longhui.pfm.web.contrller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("webIndexContrller")
@RequestMapping("/web")
public class IndexContrller {

	@GetMapping("/index")
	public String index(){
		return "web/index";
	}
}
