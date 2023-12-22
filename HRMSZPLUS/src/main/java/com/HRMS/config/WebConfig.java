//package com.HRMS.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import com.HRMS.interceptor.SessionValidationInterceptor;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//	@Autowired
//	private SessionValidationInterceptor sessionValidationInterceptor;
//
//	@Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(sessionValidationInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/**/login", "/logout", "/otp","/**/forgot_password","/**/verify-otp","/**/resetPassword") // Exclude specific URLs
//                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg", "/**/*.gif",
//                        "/**/*.svg", "/**/*.ico", "/**/*.woff", "/**/*.woff2","/**/*.ttf","/**/*.map","/**/*.mp3"); // Exclude URLs with specific extensions
//    }
//
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/css/**", "/js/**", "/images/**", "/vendor/**", "/fonts/**")
//				.addResourceLocations("classpath:/static/css/", "classpath:/static/js/", "classpath:/static/images/",
//						"classpath:/static/vendor/", "classpath:/static/fonts/")
//				.setCachePeriod(3600);
//	}
//}
//
