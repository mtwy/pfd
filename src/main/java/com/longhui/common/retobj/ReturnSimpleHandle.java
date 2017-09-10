package com.longhui.common.retobj;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;

import com.longhui.common.constant.SystemConstant;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class ReturnSimpleHandle implements Serializable {

	private static final long serialVersionUID = 5750308905528388534L;

	private String message;
	private String debugMessage;
	private Boolean isSuccess;
	private Object data;
	private String code;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

	/**
	 * 创建一个返回服务端对象
	 * 
	 * @return
	 */
	public static ReturnSimpleHandle createHandle() {
		ReturnSimpleHandle handle = new ReturnSimpleHandle();
		handle.setMessage(SystemConstant.MESSAGE_SERVER_Z00);
		handle.setIsSuccess(true);
		handle.setCode(SystemConstant.MESSAGE_SERVER_CODE_Z00);
		return handle;
	}

	/**
	 * 创建一个返回服务端异常对象
	 * 
	 * @return
	 */
	public static ReturnSimpleHandle createErrorHandle(String msg) {
		ReturnSimpleHandle handle = new ReturnSimpleHandle();
		handle.setMessage(msg);
		handle.setIsSuccess(false);
		handle.setCode(SystemConstant.ERROR_MESSAGE_SERVER_CODE_F02);
		return handle;
	}

	/**
	 * 返回服务端处理异常json串
	 * 
	 * @return
	 */
	public static ReturnSimpleHandle createServerError(Exception e, Log log) {
		ReturnSimpleHandle handle = new ReturnSimpleHandle();
		handle.setMessage(SystemConstant.ERROR_MESSAGE_SERVER_F01);
		handle.setIsSuccess(false);
		handle.setCode(SystemConstant.ERROR_MESSAGE_SERVER_CODE_F01);
		handle.setData(new Object());
		if (e != null) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));
			handle.setDebugMessage(sw.toString());
			// 统一记录日志
			log.error(sw.toString(), e);
		}
		return handle;
	}

	/**
	 * 返回服务端处理异常json串,自定义参数
	 * 
	 * @return
	 */
	public static String createServerError(String message, String code, Exception e, Log log) {
		ReturnSimpleHandle handle = new ReturnSimpleHandle();
		handle.setIsSuccess(false);
		handle.setCode(code);
		handle.setMessage(message);
		handle.setData(new Object());
		if (e != null) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));
			handle.setDebugMessage(sw.toString());
			String error = "message: " + message + ", code: " + code + "\r\n" + sw.toString();
			log.error(error, e);
		}
		String string = JSONObject.fromObject(handle).toString();
		return string;
	}

	public static ReturnSimpleHandle createServerError() {
		ReturnSimpleHandle handle = new ReturnSimpleHandle();
		handle.setMessage(SystemConstant.ERROR_MESSAGE_SERVER_F01);
		handle.setIsSuccess(false);
		handle.setCode(SystemConstant.MESSAGE_SERVER_CODE_Z00);
		return handle;
	}

	public static ReturnSimpleHandle createServerError(String msg) {
		ReturnSimpleHandle handle = new ReturnSimpleHandle();
		handle.setMessage(msg);
		handle.setIsSuccess(false);
		handle.setCode(SystemConstant.MESSAGE_SERVER_CODE_Z00);
		return handle;
	}

	public String toJson() {
		JsonConfig jf = new JsonConfig();
		jf.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		JSONObject jsonObject = JSONObject.fromObject(this, jf);
		jsonObject.remove("debugMessage");
		String json = jsonObject.toString();
		return json;
	}
	
	/**
	 * JSON 日期格式化 辅助类
	 * @author 83624
	 *
	 */
	class DateJsonValueProcessor implements JsonValueProcessor {

		private String format;

		public DateJsonValueProcessor(String format) {
			this.format = format;
		}

		public Object processArrayValue(Object value, JsonConfig jsonConfig) {
			return null;
		}

		public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
			if (value == null) {
				return "";
			}
			if (value instanceof java.sql.Timestamp) {
				String str = new SimpleDateFormat(format).format((java.sql.Timestamp) value);
				return str;
			}
			if (value instanceof java.util.Date) {
				String str = new SimpleDateFormat(format).format((java.util.Date) value);
				return str;
			}

			return value.toString();
		}
	}
}
