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
import com.longhui.pfm.server.service.BillService;

import net.sf.json.JSONObject;

@Controller("appBillContrller")
@RequestMapping("/app")
public class BillContrller {
	

	@Autowired
	private BillService billServiceImpl;

	/**
	 * 获取所有账单
	 * @return
	 */
	@PostMapping(value="/getallbills", produces="application/json; charset=utf-8")
	@ResponseBody
	public String getAllBills(){
		
		ReturnSimpleHandle handle = null;
		try {
			JSONObject parameter = RequestContent.receiveParameter();
			handle = billServiceImpl.getAllBills(parameter);
		} catch (LoongException e) {
			handle = ReturnSimpleHandle.createServerError(e.getMessage());
		}  catch (Exception e) {
			e.printStackTrace();
			handle = ReturnPaginateHandle.createServerError();
		}
		return handle.toJson();
	}
}
