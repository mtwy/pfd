package com.longhui.pfm.server.service;

import com.longhui.common.exception.LoongException;
import com.longhui.common.retobj.ReturnSimpleHandle;
import com.longhui.pfm.server.model.Asset;

import net.sf.json.JSONObject;

public interface AssetService {

	/**
	 * 根据帐号获取总资产
	 * @param parameter
	 * @return
	 * @throws LoongException
	 */
	ReturnSimpleHandle getAssetByAccount(JSONObject parameter) throws LoongException;

	/**
	 * 根据帐号获取总资产
	 * @param parameter
	 * @return
	 * @throws LoongException
	 */
	Asset getAssetByAccount_O(JSONObject parameter) throws LoongException;

}
