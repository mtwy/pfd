package com.longhui.pfm.server.service;

import com.longhui.common.exception.LoongException;
import com.longhui.common.retobj.ReturnSimpleHandle;

import net.sf.json.JSONObject;

public interface LoginService {

	/**
	 * 登录
	 * @param parameter
	 * @return
	 * @throws LoongException
	 */
	ReturnSimpleHandle login(JSONObject parameter) throws LoongException;

	/**
	 * 注册
	 * @param parameter
	 * @return
	 * @throws LoongException
	 */
	ReturnSimpleHandle register(JSONObject parameter) throws LoongException;
}
