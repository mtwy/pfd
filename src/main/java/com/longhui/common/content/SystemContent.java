package com.longhui.common.content;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * 系统内容 request、response、session
 * 
 * @author loong
 *
 */
public class SystemContent {

	// request
	private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) requestLocal.get();
	}

	public static void setRequest(HttpServletRequest request) {
		requestLocal.set(request);
	}

	// respose
	private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();

	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) responseLocal.get();
	}

	public static void setResponse(HttpServletResponse response) {
		responseLocal.set(response);
	}

	// session
	public static HttpSession getSession() {
		return (HttpSession) ((HttpServletRequest) requestLocal.get()).getSession();
	}

	// websocket session
	private static Map<String, HttpSession> wssMap = new Hashtable<String, HttpSession>();

	public static Map<String, HttpSession> getWssMap() {
		return wssMap;
	}

	public static HttpSession getWebSocketSession(String sessionId) {
		return wssMap.get(sessionId);
	}

	public static void putWebSocketSession(String sessionId, HttpSession session) {
		wssMap.put(sessionId, session);
	}

	public static void removeWebSocketSession(String sessionId) {
		wssMap.remove(sessionId);
	}

	// BasePath
	public static String getBasePath() {
		ApplicationContext factory = ContextLoader.getCurrentWebApplicationContext();
		ServletContext context = ((WebApplicationContext) factory).getServletContext();
		String basePath = context.getRealPath("/");
		return basePath;
	}

	// AppName
	public static String getAppName() {
		ApplicationContext factory = ContextLoader.getCurrentWebApplicationContext();
		ServletContext context = ((WebApplicationContext) factory).getServletContext();
		String appName = "/" + context.getInitParameter("webAppRootKey").split("\\.")[0];
		return appName;
	}
}
