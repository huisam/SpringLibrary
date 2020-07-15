package com.huisam.springstudy.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import java.io.IOException;

@Log4j2
public class WebFilters implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("hi from WebFilters");
        chain.doFilter(request, response);
    }
}
