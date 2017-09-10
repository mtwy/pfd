package com.longhui.pfm.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longhui.common.content.RequestContent;
import com.longhui.common.exception.LoongException;
import com.longhui.common.retobj.ReturnPaginateHandle;
import com.longhui.common.retobj.ReturnSimpleHandle;
import com.longhui.pfm.server.model.Asset;
import com.longhui.pfm.server.model.Ledger;
import com.longhui.pfm.server.service.AssetService;
import com.longhui.pfm.server.service.LedgerService;

import net.sf.json.JSONObject;

@Controller("appInitDataContrller")
@RequestMapping("/app")
public class InitDataContrller {

	@Autowired
	private AssetService assetServiceImpl;
	
	@Autowired
	private LedgerService ledgerServiceImpl;
	
	/**
	 * 初始化数据获取asset/ledger
	 * @return
	 */
	@PostMapping(value="/initdata", produces="application/json; charset=utf-8")
	@ResponseBody
	public String initData(){
		
		ReturnSimpleHandle handle = null;
		try {
			JSONObject parameter = RequestContent.receiveParameter();
			
			Asset asset = assetServiceImpl.getAssetByAccount_O(parameter);
			List<Ledger> ledgers = ledgerServiceImpl.getAllLedgersByAccount_O(parameter);
			
			handle = ReturnSimpleHandle.createHandle();

			Map<String, Object> data = new HashMap<String, Object>();
			data.put("asset", asset);
			data.put("ledgers", ledgers);
			handle.setData(data);
		} catch (LoongException e) {
			handle = ReturnSimpleHandle.createServerError(e.getMessage());
		}  catch (Exception e) {
			e.printStackTrace();
			handle = ReturnPaginateHandle.createServerError();
		}
		return handle.toJson();
	}
}
