package com.longhui.pfm.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longhui.common.content.RequestContent;
import com.longhui.common.exception.LoongException;
import com.longhui.common.retobj.ReturnPaginateHandle;
import com.longhui.common.retobj.ReturnSimpleHandle;
import com.longhui.pfm.server.service.LedgerService;

import net.sf.json.JSONObject;

@Controller("appLedgerContrller")
@RequestMapping("/app")
public class LedgerContrller {

	@Autowired
	private LedgerService ledgerServiceImpl;
	
	/**
	 * 根据帐号获取所有
	 * @return
	 */
	@PostMapping(value="/getallledgersbyaccount", produces="application/json; charset=utf-8")
	@ResponseBody
	public String getAllLedgersByAccount(){
		
		ReturnSimpleHandle handle = null;
		try {
			JSONObject parameter = RequestContent.receiveParameter();
			handle = ledgerServiceImpl.getAllLedgersByAccount(parameter);
		} catch (LoongException e) {
			handle = ReturnSimpleHandle.createServerError(e.getMessage());
		}  catch (Exception e) {
			e.printStackTrace();
			handle = ReturnPaginateHandle.createServerError();
		}
		return handle.toJson();
	}
}
