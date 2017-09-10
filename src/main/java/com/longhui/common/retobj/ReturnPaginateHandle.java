package com.longhui.common.retobj;

import java.io.Serializable;

import com.longhui.common.constant.SystemConstant;

import net.sf.json.JSONObject;

/**
 * 分页信息返回对象
 * 
 * @author loong
 *
 */
public class ReturnPaginateHandle extends ReturnSimpleHandle implements Serializable {

	private static final long serialVersionUID = -4915500582417432476L;

	private Integer dataMaxPage;// 总页数
	private Integer pageCount;// 每页行数
	private Integer curPage;// 当页页码
	private Integer dataMaxCount;// 数据总数
	private Integer draw;

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getDataMaxPage() {
		return dataMaxPage;
	}

	public void setDataMaxPage(Integer dataMaxPage) {
		this.dataMaxPage = dataMaxPage;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getDataMaxCount() {
		return dataMaxCount;
	}

	public void setDataMaxCount(Integer dataMaxCount) {
		this.dataMaxCount = dataMaxCount;
	}

	/**
	 * 创建一个返回服务端对象
	 * 
	 * @return
	 */
	public static ReturnPaginateHandle createHandle() {
		ReturnPaginateHandle handle = new ReturnPaginateHandle();
		handle.setMessage(SystemConstant.MESSAGE_SERVER_Z00);
		handle.setIsSuccess(true);
		handle.setCode(SystemConstant.MESSAGE_SERVER_CODE_Z00);
		return handle;
	}

	/**
	 * 创建一个返回服务端对象
	 * 
	 * @return
	 */
	public static ReturnPaginateHandle createServerHandle() {
		return createHandle();
	}

	/**
	 * convert父类
	 * 
	 * @return
	 */
	public ReturnSimpleHandle convertSimple() {
		JSONObject json = (JSONObject) getData();
		ReturnSimpleHandle retobj = new ReturnSimpleHandle();
		retobj.setCode(json.getString("code"));
		retobj.setData(json.get("data"));
		retobj.setIsSuccess(json.getBoolean("isSuccess"));
		retobj.setMessage(json.getString("message"));
		return retobj;
	}

}