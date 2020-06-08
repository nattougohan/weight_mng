package com.practice.weightMng.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
			public void addViewControllers(ViewControllerRegistry registry) {
				registry.addViewController("/index").setViewName("index");
				registry.addViewController("/").setViewName("index");
				registry.addViewController("/loginResult").setViewName("loginResult");
				registry.addViewController("/weightRecord").setViewName("weightRecord");
				registry.addViewController("/weightRecordResult").setViewName("weightRecordResult");
				registry.addViewController("/showGraph").setViewName("showGraph");
			}
}
