package com.wang.hm_takeout.filter;//wangDD

import com.alibaba.druid.support.json.JSONUtils;
import com.wang.hm_takeout.dao.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//2022-05-2022/5/8-18:48
@WebFilter(filterName = "loginFilter",urlPatterns = "/*")
@Slf4j
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest servletRequest1 = (HttpServletRequest) servletRequest;
        HttpServletResponse servletResponse1 = (HttpServletResponse) servletResponse;
        AntPathMatcher antPathMatcher = new AntPathMatcher(); //匹配url
        log.info("获取到请求{}",servletRequest1.getRequestURI());

        String[] strings = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };
        boolean b = false;
        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            boolean match = antPathMatcher.match(string, servletRequest1.getRequestURI());
            if (match){
                b=true;
                break;
            }
        }

        //如果请求路径在允许访问的路径上则放行
        if (b){
            filterChain.doFilter(servletRequest1,servletResponse1); //去执行下一个拦截器
            return;
        }

        if (servletRequest1.getSession().getAttribute("employee")!=null){

            filterChain.doFilter(servletRequest1,servletResponse1); //去执行下一个拦截器
            return;

        }

        servletResponse1.getWriter().write(JSONUtils.toJSONString(R.error("NOTLOGIN")));
        filterChain.doFilter(servletRequest1,servletResponse1); //去执行下一个拦截器
        return;


    }
}
