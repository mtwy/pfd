package com.longhui.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.loong.common.utils.StringUtils;
import org.springframework.core.annotation.Order;

import com.longhui.common.content.SystemContent;
import com.longhui.pfm.server.model.User;

@Order(2)
@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {

    public LoginFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		String url = SystemContent.getRequest().getRequestURI();
		if(!url.contains("login") && !url.contains("register")){
			User user = (User) SystemContent.getSession().getAttribute("user");
			if(user == null || StringUtils.isEmpty(user.getAccount())){
				if(url.contains("wap")){
					SystemContent.getResponse().sendRedirect("/wap/tologin");
				}else if(url.contains("web")){
					SystemContent.getResponse().sendRedirect("/web/tologin");
				}
				return;
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
