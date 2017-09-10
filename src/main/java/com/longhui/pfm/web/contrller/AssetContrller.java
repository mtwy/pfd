package com.longhui.pfm.web.contrller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longhui.common.content.RequestContent;
import com.longhui.common.content.SystemContent;
import com.longhui.common.exception.LoongException;
import com.longhui.common.retobj.ReturnPaginateHandle;
import com.longhui.common.retobj.ReturnSimpleHandle;
import com.longhui.pfm.server.model.User;
import com.longhui.pfm.server.service.AssetService;

import net.sf.json.JSONObject;

@Controller("webAssetContrller")
@RequestMapping("/web/asset")
public class AssetContrller {
	
	@Autowired
	private AssetService assetServiceImpl;
	
	/**
	 * 获取资产
	 * @return
	 */
	@PostMapping(value="/getassetbyaccount", produces="application/json; charset=utf-8")
	@ResponseBody
	public String getAssetByAccount(){
		
		ReturnSimpleHandle handle = null;
		try {
			JSONObject parameter = RequestContent.receiveParameter();
			
			User user = (User) SystemContent.getSession().getAttribute("user");
			parameter.put("account", user.getAccount());
			
			handle = assetServiceImpl.getAssetByAccount(parameter);
		} catch (LoongException e) {
			handle = ReturnSimpleHandle.createServerError(e.getMessage());
		}  catch (Exception e) {
			e.printStackTrace();
			handle = ReturnPaginateHandle.createServerError();
		}
		return handle.toJson();
	}
}
