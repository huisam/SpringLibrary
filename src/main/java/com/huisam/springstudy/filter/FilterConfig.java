package com.huisam.springstudy.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<ComponentFilter> componentFilter() {
        FilterRegistrationBean<ComponentFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new ComponentFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<WebFilters> webFilters() {
        FilterRegistrationBean<WebFilters> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new WebFilters());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(0);

        return registrationBean;
    }
}
