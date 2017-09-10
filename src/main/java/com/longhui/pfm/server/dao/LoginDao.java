package com.longhui.pfm.server.dao;

import com.longhui.pfm.server.model.Login;

import net.sf.json.JSONObject;

public interface LoginDao {
    int deleteByPrimaryKey(Long id);

    int insert(Login record);

    int insertSelective(Login record);

    Login selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Login record);

    int updateByPrimaryKey(Login record);

    /**
     * 根据帐号查找
     * @param parameter
     * @return
     */
	Login selectByAccount(JSONObject parameter);
}