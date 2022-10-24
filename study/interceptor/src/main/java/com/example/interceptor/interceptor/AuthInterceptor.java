package com.example.interceptor.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();

        log.info("request url : {}",url);

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    public boolean checkAnnotation(Object handler, Class clazz) {
        // 리소스
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        // 어노테이션 체크
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (null != handlerMethod.getMethodAnnotation(clazz) ||
            null != handlerMethod.getBeanType().getAnnotation(clazz)) {
            return true;
        }

        return false;
    }
}
