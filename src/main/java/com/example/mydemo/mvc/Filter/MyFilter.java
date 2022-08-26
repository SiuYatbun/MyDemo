package com.example.mydemo.mvc.Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @WebFilter 用于将一个类声明为过滤器,该注解将会在部署时被容器处理,容器将根据具体的属性配置将相应的类部署为过滤器
 * @Component 注册到Spring容器中
 */
@WebFilter(urlPatterns = "/*")
@Component
public class MyFilter implements Filter {
    public static final Logger logger = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.info("Filter 处理中...");
        chain.doFilter(request, response);//必须加
        logger.info("Filer执行完毕!!");
    }
}
