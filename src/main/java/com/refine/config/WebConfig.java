package com.refine.config;

import com.refine.filter.XSSFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean getFilterRegistrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new XSSFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*");    //filter를 거칠 url patterns
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean getMultipartFilterRegistrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MultipartFilter());
        registrationBean.setOrder(0);
        registrationBean.addUrlPatterns("/*");    //filter를 거칠 url patterns
        return registrationBean;
    }


}
