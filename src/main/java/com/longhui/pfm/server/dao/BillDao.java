package com.longhui.pfm.server.dao;

import java.util.List;

import com.longhui.pfm.server.model.Bill;

import net.sf.json.JSONObject;

public interface BillDao {
    int deleteByPrimaryKey(Long id);

    int insert(Bill record);

    int insertSelective(Bill record);

    Bill selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Bill record);

    int updateByPrimaryKey(Bill record);
    
    /**
     * 获取所有
     * @param parameter
     * @return
     */
	List<Bill> selectAll(JSONObject parameter);

	/**
	 * 统计
	 * @param parameter
	 * @return
	 */
	int countAll(JSONObject parameter);
}