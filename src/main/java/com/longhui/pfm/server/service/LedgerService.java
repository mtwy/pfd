package com.longhui.pfm.server.service;

import java.util.List;

import com.longhui.common.exception.LoongException;
import com.longhui.common.retobj.ReturnSimpleHandle;
import com.longhui.pfm.server.model.Ledger;

import net.sf.json.JSONObject;

public interface LedgerService {

	/**
	 * 根据帐号获取所有
	 * @param parameter
	 * @return
	 * @throws LoongException
	 */
	ReturnSimpleHandle getAllLedgersByAccount(JSONObject parameter) throws LoongException;

	/**
	 * 根据帐号获取所有账户
	 * @param parameter
	 * @return
	 * @throws LoongException
	 */
	List<Ledger> getAllLedgersByAccount_O(JSONObject parameter) throws LoongException;

	/**
	 * 添加资金账户
	 * @param parameter
	 * @return
	 * @throws LoongException
	 */
	ReturnSimpleHandle addLedger(JSONObject parameter) throws LoongException;
}
