package com.example.mydemo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        return "index";
    }

    @RequestMapping("/file")
    public String file() {
        System.out.println("=> /file");
        return "file";
    }

    /**
     * 转发是request对象触发的，服务器内部进行转发
     * 重定向是response对象触发的，要将重定向的路径响应给浏览器
     * 转发是一次请求，浏览器地址栏上地址不变
     * 重定向是两次请求，浏览器地址栏上的地址发生变化
     * 重定向路径需要加项目名（webapp根路径web目录），因为该路径由浏览器解析
     * 转发是在本项目内部完成资源的跳转
     * 重定向可以完成跨网站跳转，例如可以跳转到 <a href="https://www.baidu.com">https://www.baidu.com</a>
     */
    @RequestMapping("/fwd")// 转发 经服务器
    public void fwd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("=> /fwd");
        request.getRequestDispatcher("/file").forward(request, response);
    }

    @RequestMapping("/rdt")// 重定向
    public void rdt(HttpServletResponse response) throws IOException {
        System.out.println("=> /rdt");
        response.sendRedirect("/file");
    }

    @RequestMapping("/rdt2")// 重定向 2
    public String rdt2() {
        return "redirect:file";
    }
}
