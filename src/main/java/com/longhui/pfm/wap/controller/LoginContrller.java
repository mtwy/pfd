package com.longhui.pfm.wap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longhui.common.content.RequestContent;
import com.longhui.common.content.SystemContent;
import com.longhui.common.exception.LoongException;
import com.longhui.common.retobj.ReturnPaginateHandle;
import com.longhui.common.retobj.ReturnSimpleHandle;
import com.longhui.pfm.server.service.LoginService;

import net.sf.json.JSONObject;

@Controller("wapLoginContrller")
@RequestMapping("/wap")
public class LoginContrller {

	@GetMapping("/tologin")
	public String index(){
		return "wap/login";
	}
	
	@Autowired
	private LoginService loginServiceImpl;
	
	/**
	 * 登录
	 * @return
	 */
	@PostMapping(value="/login", produces="application/json; charset=utf-8")
	@ResponseBody
	public String login(){
		
		ReturnSimpleHandle handle = null;
		try {
			JSONObject parameter = RequestContent.receiveParameter();
			handle = loginServiceImpl.login(parameter);
			SystemContent.getSession().setAttribute("user", handle.getData());
		} catch (LoongException e) {
			handle = ReturnSimpleHandle.createServerError(e.getMessage());
		}  catch (Exception e) {
			e.printStackTrace();
			handle = ReturnPaginateHandle.createServerError();
		}
		return handle.toJson();
	}
	
	/**
	 * 注册
	 * @return
	 */
	@PostMapping(value="/register", produces="application/json; charset=utf-8")
	@ResponseBody
	public String register(){
		
		ReturnSimpleHandle handle = null;
		try {
			JSONObject parameter = RequestContent.receiveParameter();
			handle = loginServiceImpl.register(parameter);
			SystemContent.getSession().setAttribute("user", handle.getData());
		} catch (LoongException e) {
			handle = ReturnSimpleHandle.createServerError(e.getMessage());
		}  catch (Exception e) {
			e.printStackTrace();
			handle = ReturnPaginateHandle.createServerError();
		}
		return handle.toJson();
	}
}
