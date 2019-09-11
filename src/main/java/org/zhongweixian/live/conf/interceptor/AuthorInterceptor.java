package org.zhongweixian.live.conf.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.zhongweixian.live.exception.BusinessException;
import org.zhongweixian.live.exception.ErrorCode;
import org.zhongweixian.live.conf.context.UserAuthContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: caoliang1918@aliyun.com
 * @date: 2017/12/8 23:54
 */
@Component
public class AuthorInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(AuthorInterceptor.class);

    @Autowired
    private UserAuthContext userAuthContext;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String url = request.getRequestURI();
        String token = null;

        /**
         * 管理后台
         */
        if (url.startsWith("/admin")) {
            Cookie[] cookies = request.getCookies();
            if (cookies == null) {
                response.sendRedirect("/index/login?redirectURL=" + url);
                return false;
            }
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Authorization")) {
                    token = cookie.getValue();
                }
            }
            if (token == null) {
                response.sendRedirect("/index/login?redirectURL=" + url);
                return false;
            }


            /**
             * 前后端分离操作
             */
        } else {
            token = request.getHeader("Authorization");
            if (StringUtils.isEmpty(token)) {
                throw new BusinessException(ErrorCode.SYS_PARAMETERS_EMPTY_ERROR, ErrorCode.SYS_PARAMETERS_EMPTY_ERROR.getMessage("Authorization"));
            }

            logger.info("username:{} , url:{} ", "XXXX", request.getRequestURI());
        }
        /***
         * 登录成功之后把登录对象存到线程
         */
        userAuthContext.getContextUser().set(null);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
