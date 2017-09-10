package com.longhui.pfm.server.dao;

import java.util.List;

import com.longhui.pfm.server.model.Ledger;

import net.sf.json.JSONObject;

public interface LedgerDao {
    int deleteByPrimaryKey(Long id);

    int insert(Ledger record);

    int insertSelective(Ledger record);

    Ledger selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Ledger record);

    int updateByPrimaryKey(Ledger record);

    /**
     * 查询suoyou
     * @param parameter
     * @return
     */
	List<Ledger> selectAll(JSONObject parameter);
	
	/**
	 * 统计
	 * @param parameter
	 * @return
	 */
	int countAll(JSONObject parameter);

    /**
     * 根据分账类别查找用户分账信息
     * @param params
     * @return
     */
	Ledger selectByName(JSONObject params);
}