package com.gramfood.foodstargram.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gramfood.foodstargram.Interceptor.PermissionInterceptor;
import com.gramfood.foodstargram.common.FileManager;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**")
		.addResourceLocations("file:///" + FileManager.FILE_UPLOAD_PATH + "/");
	}
	
	@Override 
	public void addInterceptors(InterceptorRegistry registry) {
		PermissionInterceptor intercpetor = new PermissionInterceptor();
		registry.addInterceptor(intercpetor)
		.addPathPatterns("/**")
		.excludePathPatterns("/static/**", "/images/**", "/user/logout");
	}
}
