package com.longhui.pfm.server.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.loong.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longhui.common.exception.LoongException;
import com.longhui.common.retobj.ReturnSimpleHandle;
import com.longhui.pfm.server.dao.AssetDao;
import com.longhui.pfm.server.dao.LoginDao;
import com.longhui.pfm.server.dao.UserDao;
import com.longhui.pfm.server.model.Asset;
import com.longhui.pfm.server.model.Login;
import com.longhui.pfm.server.model.User;
import com.longhui.pfm.server.service.LoginService;

import net.sf.json.JSONObject;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AssetDao assetDao;
	
	/**
	 * 登录
	 */
	public ReturnSimpleHandle login(JSONObject parameter) throws LoongException {
		
		ReturnSimpleHandle handle = ReturnSimpleHandle.createHandle();
		
		String password = StringUtils.toString(parameter.get("password"), "");
		Login login = loginDao.selectByAccount(parameter);
		if(login == null){
			throw new LoongException("帐号不存在");
		}
		if(!password.equals(login.getPassword())){
			throw new LoongException("密码错误");
		}
		
		User user = userDao.selectByAccount(parameter);
		/*Asset asset = assetDao.selectByAccount(parameter);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("user", user);
		data.put("asset", asset);*/
		handle.setData(user);
		return handle;
	}

	/**
	 * 注册
	 */
	public ReturnSimpleHandle register(JSONObject parameter) throws LoongException {
		
		ReturnSimpleHandle handle = ReturnSimpleHandle.createHandle();
		
		String account = StringUtils.toString(parameter.get("account"), "");
		String password = StringUtils.toString(parameter.get("password"), "");
		Login login = loginDao.selectByAccount(parameter);
		if(login != null){
			throw new LoongException("帐号已存在");
		}
		
		Date curDate = new Date();
		
		login = new Login();
		login.setAccount(account);
		login.setPassword(password);
		login.setCreatedAt(curDate);
		login.setUpdatedAt(curDate);
		login.setDelFlag(false);
		loginDao.insertSelective(login);
		
		User user = new User();
		user.setAccount(account);
		user.setCreatedAt(curDate);
		user.setUpdatedAt(curDate);
		user.setDelFlag(false);
		userDao.insertSelective(user);
		
		Asset asset = new Asset();
		asset.setAccount(account);
		asset.setBalance(new BigDecimal(0));
		asset.setCreatedAt(curDate);
		asset.setUpdatedAt(curDate);
		asset.setDelFlag(false);
		assetDao.insertSelective(asset);
		
		handle = login(parameter);
		return handle;
	}
}
