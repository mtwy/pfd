package com.longhui.pfm.server.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.loong.common.utils.DateUtils;
import org.loong.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longhui.common.exception.LoongException;
import com.longhui.common.retobj.ReturnPaginateHandle;
import com.longhui.common.retobj.ReturnSimpleHandle;
import com.longhui.pfm.server.dao.AssetDao;
import com.longhui.pfm.server.dao.BillDao;
import com.longhui.pfm.server.dao.LedgerDao;
import com.longhui.pfm.server.model.Asset;
import com.longhui.pfm.server.model.Bill;
import com.longhui.pfm.server.model.Ledger;
import com.longhui.pfm.server.service.BillService;
import com.longhui.pfm.system.constant.EnumConstant;

import net.sf.json.JSONObject;

@Service
@Transactional(rollbackFor=LoongException.class)
public class BillServiceImpl implements BillService{

	@Autowired
	private BillDao billDao;
	
	@Autowired
	private AssetDao assetDao;
	
	@Autowired
	private LedgerDao ledgerDao;
	
	/**
	 * 分页获取所有
	 */
	public ReturnSimpleHandle getAllBills(JSONObject parameter) throws LoongException {
		
		ReturnPaginateHandle handle = ReturnPaginateHandle.createHandle();
		
		int start = StringUtils.toInt(parameter.get("start"), 0);
		int pageCount = StringUtils.toInt(parameter.get("pageCount"), 5);
		
		parameter.put("start", start);
		parameter.put("pageCount", pageCount);
		
		List<Bill> data = billDao.selectAll(parameter);
		int count = billDao.countAll(parameter);
		int page = count % pageCount == 0 ? count / pageCount : count / pageCount + 1;
		handle.setData(data);
		handle.setCurPage(page);
		handle.setDataMaxCount(count);
		return handle;
	}

	/**
	 * 入账
	 */
	public ReturnSimpleHandle income(JSONObject parameter) throws LoongException {
		
		ReturnSimpleHandle handle = ReturnSimpleHandle.createHandle();
		
		try{
			String account = StringUtils.toString(parameter.get("account"), "");
			String fundsSource = StringUtils.toString(parameter.get("fundsSource"), "");
			String fundsTrend = StringUtils.toString(parameter.get("fundsTrend"), "");
			String money = StringUtils.toString(parameter.get("money"), "");
			String remarks = StringUtils.toString(parameter.get("remarks"), "");
			String createdAt = StringUtils.toString(parameter.get("createdAt"), "");
			Date curDate = new Date();
			
			// 创建账单
			Bill bill = new Bill();
			bill.setAccount(account);
			bill.setFundsSource(fundsSource);
			bill.setFundsTrend(fundsTrend);
			bill.setMoney(new BigDecimal(money));
			bill.setRemarks(remarks);
			bill.setType(EnumConstant.BILL_TYPE_INCOME);
			bill.setCreatedAt(DateUtils.string2Date(createdAt, DateUtils.YYYYMMDD));
			bill.setUpdatedAt(curDate);
			bill.setDelFlag(false);
			billDao.insertSelective(bill);
			
			// 更新分账余额
			JSONObject params = new JSONObject();
			params.put("account", account);
			params.put("name", fundsTrend);
			Ledger ledger = ledgerDao.selectByName(params);
			ledger.setBalance(ledger.getBalance().add(bill.getMoney()));
			ledger.setUpdatedAt(curDate);
			ledgerDao.updateByPrimaryKeySelective(ledger);
			
			// 跟新总资产余额
			Asset asset = assetDao.selectByAccount(parameter);
			asset.setBalance(asset.getBalance().add(bill.getMoney()));
			asset.setUpdatedAt(curDate);
			assetDao.updateByPrimaryKeySelective(asset);
		}catch (Exception e) {
			throw new LoongException(e.getMessage());
		}
		return handle;
	}

	/**
	 * 出账
	 */
	public ReturnSimpleHandle defray(JSONObject parameter) throws LoongException {

		ReturnSimpleHandle handle = ReturnSimpleHandle.createHandle();
		
		try{
			String account = StringUtils.toString(parameter.get("account"), "");
			String fundsSource = StringUtils.toString(parameter.get("fundsSource"), "");
			String fundsTrend = StringUtils.toString(parameter.get("fundsTrend"), "");
			String money = StringUtils.toString(parameter.get("money"), "");
			String remarks = StringUtils.toString(parameter.get("remarks"), "");
			String createdAt = StringUtils.toString(parameter.get("createdAt"), "");
			Date curDate = new Date();
			
			// 创建账单
			Bill bill = new Bill();
			bill.setAccount(account);
			bill.setFundsSource(fundsSource);
			bill.setFundsTrend(fundsTrend);
			bill.setMoney(new BigDecimal(money));
			bill.setRemarks(remarks);
			bill.setType(EnumConstant.BILL_TYPE_DEFRAY);
			bill.setCreatedAt(DateUtils.string2Date(createdAt, DateUtils.YYYYMMDD));
			bill.setUpdatedAt(curDate);
			bill.setDelFlag(false);
			
			// 更新分账余额
			JSONObject params = new JSONObject();
			params.put("account", account);
			params.put("name", fundsSource);
			Ledger ledger = ledgerDao.selectByName(params);
			if(bill.getMoney().compareTo(ledger.getBalance()) > 0){
				throw new LoongException("账户余额不足");
			}
			ledger.setBalance(ledger.getBalance().subtract(bill.getMoney()));
			ledger.setUpdatedAt(curDate);
			
			// 跟新总资产余额
			Asset asset = assetDao.selectByAccount(parameter);
			asset.setBalance(asset.getBalance().subtract(bill.getMoney()));
			asset.setUpdatedAt(curDate);
			
			billDao.insertSelective(bill);
			ledgerDao.updateByPrimaryKeySelective(ledger);
			assetDao.updateByPrimaryKeySelective(asset);
		}catch (Exception e) {
			throw new LoongException(e.getMessage());
		}
		return handle;
	}

	/**
	 * 平账
	 */
	public ReturnSimpleHandle flat(JSONObject parameter) throws LoongException {

		ReturnSimpleHandle handle = ReturnSimpleHandle.createHandle();
		
		try{
			String account = StringUtils.toString(parameter.get("account"), "");
			String fundsSource = StringUtils.toString(parameter.get("fundsSource"), "");
			String fundsTrend = StringUtils.toString(parameter.get("fundsTrend"), "");
			String money = StringUtils.toString(parameter.get("money"), "");
			String remarks = StringUtils.toString(parameter.get("remarks"), "");
			String createdAt = StringUtils.toString(parameter.get("createdAt"), "");
			Date curDate = new Date();
			
			// 创建账单
			Bill bill = new Bill();
			bill.setAccount(account);
			bill.setFundsSource(fundsSource);
			bill.setFundsTrend(fundsTrend);
			bill.setMoney(new BigDecimal(money));
			bill.setRemarks(remarks);
			bill.setType(EnumConstant.BILL_TYPE_FLAT);
			bill.setCreatedAt(DateUtils.string2Date(createdAt, DateUtils.YYYYMMDD));
			bill.setUpdatedAt(curDate);
			bill.setDelFlag(false);
			billDao.insertSelective(bill);
			
			// 更新出账账户余额
			JSONObject params = new JSONObject();
			params.put("account", account);
			params.put("name", fundsSource);
			Ledger ledger = ledgerDao.selectByName(params);
			if(bill.getMoney().compareTo(ledger.getBalance()) > 0){
				throw new LoongException("账户余额不足");
			}
			ledger.setBalance(ledger.getBalance().subtract(bill.getMoney()));
			ledger.setUpdatedAt(curDate);
			ledgerDao.updateByPrimaryKeySelective(ledger);
			
			// 更新入账账户余额
			params = new JSONObject();
			params.put("account", account);
			params.put("name", fundsTrend);
			ledger = ledgerDao.selectByName(params);
			ledger.setBalance(ledger.getBalance().add(bill.getMoney()));
			ledger.setUpdatedAt(curDate);
			ledgerDao.updateByPrimaryKeySelective(ledger);
			
			billDao.insertSelective(bill);
		}catch (Exception e) {
			throw new LoongException(e.getMessage());
		}
		return handle;
	}

	/**
	 * 获取最新账单
	 */
	public ReturnSimpleHandle getAllUpToDateBills(JSONObject parameter) throws LoongException {
		
		ReturnSimpleHandle handle = ReturnSimpleHandle.createHandle();
		parameter.put("start", 0);
		parameter.put("pageCount", 5);
		List<Bill> bills = billDao.selectAll(parameter);
		handle.setData(bills);
		return handle;
	}
}
