package com.longhui.pfm.wap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("wapIndexContrller")
@RequestMapping("/wap")
public class IndexContrller {

	@GetMapping("/index")
	public String index(){
		return "wap/index";
	}
}
