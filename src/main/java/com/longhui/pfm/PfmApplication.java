package com.longhui.pfm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;

import com.longhui.common.filter.LoginFilter;
import com.longhui.common.filter.SystemFilter;

@SpringBootApplication
@Controller
@MapperScan("com.longhui.*.*.dao")
@ServletComponentScan
@EnableTransactionManagement
public class PfmApplication {

	@GetMapping(value = "/")
	public String index() {
		return "index";
	}

	public static void main(String[] args) {
		SpringApplication.run(PfmApplication.class, args);
	}
	
	/**
	 * 注册系统过滤器
	 * @return
	 */
	@Order(1)
	@Bean
    public FilterRegistrationBean<SystemFilter> SystemFilterRegistration() {
        FilterRegistrationBean<SystemFilter> registration = new FilterRegistrationBean<SystemFilter>(new SystemFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }
	
	/**
	 * 注册登录验证过滤器
	 * @return
	 */
	@Order(2)
	@Bean
	public FilterRegistrationBean<LoginFilter> LoginFilterRegistration() {
		FilterRegistrationBean<LoginFilter> registration = new FilterRegistrationBean<LoginFilter>(new LoginFilter());
		//registration.addUrlPatterns("/*");
		registration.addUrlPatterns("/wap/*");
		registration.addUrlPatterns("/web/*");
		return registration;
	}
}
