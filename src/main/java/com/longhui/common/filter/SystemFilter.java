package com.longhui.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.Order;

import com.longhui.common.content.SystemContent;

@Order(1)
@WebFilter(filterName = "SystemFilter", urlPatterns = "/*")
public class SystemFilter implements Filter {

	private final Log log = LogFactory.getLog(getClass());


	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("================================= system info =================================");
		log.info("SystemFilter init... " + this);
		log.info("================================= system info =================================");
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		SystemContent.setRequest((HttpServletRequest) request);
		SystemContent.setResponse((HttpServletResponse) response);
		
		//正常请求
		chain.doFilter(request, response);
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
