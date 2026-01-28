package com.refine.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class AuthenticationFilter implements Filter {
    // Authentication filter implementation
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 초기화
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        // 필터 로직 (임시로 통과)
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {
        // 정리
    }
}