package com.longhui.pfm.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longhui.common.exception.LoongException;
import com.longhui.common.retobj.ReturnSimpleHandle;
import com.longhui.pfm.server.dao.AssetDao;
import com.longhui.pfm.server.model.Asset;
import com.longhui.pfm.server.service.AssetService;

import net.sf.json.JSONObject;

@Service
public class AssetServiceImpl implements AssetService{

	@Autowired
	private AssetDao assetDao;
	
	/**
	 * 根据帐号获取总资产
	 */
	public ReturnSimpleHandle getAssetByAccount(JSONObject parameter) throws LoongException {
		
		ReturnSimpleHandle handle = ReturnSimpleHandle.createHandle();
		
		Asset asset = assetDao.selectByAccount(parameter);
		handle.setData(asset);
		return handle;
	}

	public Asset getAssetByAccount_O(JSONObject parameter) throws LoongException {
		Asset asset = assetDao.selectByAccount(parameter);
		return asset;
	}
}
