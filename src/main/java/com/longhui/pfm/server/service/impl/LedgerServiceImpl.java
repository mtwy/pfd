package com.longhui.pfm.server.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.loong.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longhui.common.exception.LoongException;
import com.longhui.common.retobj.ReturnPaginateHandle;
import com.longhui.common.retobj.ReturnSimpleHandle;
import com.longhui.pfm.server.dao.LedgerDao;
import com.longhui.pfm.server.model.Ledger;
import com.longhui.pfm.server.service.LedgerService;

import net.sf.json.JSONObject;

@Service
public class LedgerServiceImpl implements LedgerService{

	@Autowired
	private LedgerDao ledgerDao;
	
	/**
	 * 根据帐号获取所有
	 */
	public ReturnSimpleHandle getAllLedgersByAccount(JSONObject parameter) throws LoongException {
		
		ReturnPaginateHandle handle = ReturnPaginateHandle.createHandle();
		
		if(parameter.containsKey("start") && parameter.containsKey("pageCount")){
			int start = StringUtils.toInt(parameter.get("start"), 0);
			int pageCount = StringUtils.toInt(parameter.get("pageCount"), 10);
			parameter.put("start", start);
			parameter.put("pageCount", pageCount);
		}
		
		List<Ledger> data = ledgerDao.selectAll(parameter);
		int count = ledgerDao.countAll(parameter);
		handle.setData(data);
		handle.setDataMaxCount(count);
		return handle;
	}

	/**
	 * 根据帐号获取所有资金账户
	 */
	public List<Ledger> getAllLedgersByAccount_O(JSONObject parameter) throws LoongException {
		List<Ledger> data = ledgerDao.selectAll(parameter);
		return data;
	}

	/**
	 * 添加资金账户
	 */
	public ReturnSimpleHandle addLedger(JSONObject parameter) throws LoongException {

		ReturnSimpleHandle handle = ReturnSimpleHandle.createHandle();
		
		try{
			String account = StringUtils.toString(parameter.get("account"), "");
			String name = StringUtils.toString(parameter.get("name"), "");
			String remarks = StringUtils.toString(parameter.get("remarks"), "");
			Date curDate = new Date();
			
			// 创建账户
			JSONObject params = new JSONObject();
			params.put("account", account);
			params.put("name", name);
			Ledger ledger = ledgerDao.selectByName(params);
			if(ledger != null){
				throw new LoongException("账户已存在");
			}
			ledger = new Ledger();
			ledger.setAccount(account);
			ledger.setBalance(new BigDecimal(0));
			ledger.setName(name);
			ledger.setCreatedAt(curDate);
			ledger.setRemarks(remarks);
			ledger.setUpdatedAt(curDate);
			ledgerDao.insertSelective(ledger);
			
		}catch (Exception e) {
			throw new LoongException(e.getMessage());
		}
		return handle;
	}
}
