package com.longhui.pfm.server.dao;

import com.longhui.pfm.server.model.User;

import net.sf.json.JSONObject;

public interface UserDao {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据帐号查找
     * @param parameter
     * @return
     */
	User selectByAccount(JSONObject parameter);
}