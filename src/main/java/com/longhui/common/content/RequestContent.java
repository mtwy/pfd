package com.longhui.common.content;

import java.util.Set;

import net.sf.json.JSONObject;

public class RequestContent {

	/**
	 * 获取请求参数
	 * @return
	 */
	public static JSONObject receiveParameter(){
		JSONObject jsonParameter = new JSONObject();
		Set<String> keySet = SystemContent.getRequest().getParameterMap().keySet();
		if(keySet != null && keySet.size() > 0){
			for (String key : keySet) {
				String value = SystemContent.getRequest().getParameter(key);
				jsonParameter.put(key, value);
			}
		}
		return jsonParameter;
	}
}
