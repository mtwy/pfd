package com.longhui.pfm.server.dao;

import com.longhui.pfm.server.model.Asset;

import net.sf.json.JSONObject;

public interface AssetDao {
	int deleteByPrimaryKey(Long id);

    int insert(Asset record);

    int insertSelective(Asset record);

    Asset selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Asset record);

    int updateByPrimaryKey(Asset record);

    /**
     * 根据帐号查找
     * @param parameter
     * @return
     */
	Asset selectByAccount(JSONObject parameter);
}