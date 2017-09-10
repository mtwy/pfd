package com.longhui.pfm.web.contrller;

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
import com.longhui.pfm.server.model.User;
import com.longhui.pfm.server.service.BillService;

import net.sf.json.JSONObject;

@Controller("webFlatContrller")
@RequestMapping("/web")
public class FlatContrller {
	

	@Autowired
	private BillService billServiceImpl;

	@GetMapping("/toflat")
	public String index(){
		return "web/flat";
	}
	
	/**
	 * 转账
	 * @return
	 */
	@PostMapping(value="/flat", produces="application/json; charset=utf-8")
	@ResponseBody
	public String flat(){
		
		ReturnSimpleHandle handle = null;
		try {
			JSONObject parameter = RequestContent.receiveParameter();
			User user = (User) SystemContent.getSession().getAttribute("user");
			parameter.put("account", user.getAccount());
			handle = billServiceImpl.flat(parameter);
		} catch (LoongException e) {
			handle = ReturnSimpleHandle.createServerError(e.getMessage());
		}  catch (Exception e) {
			e.printStackTrace();
			handle = ReturnPaginateHandle.createServerError();
		}
		return handle.toJson();
	}
}
