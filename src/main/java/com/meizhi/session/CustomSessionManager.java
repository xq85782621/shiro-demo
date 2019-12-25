package com.meizhi.session;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 自定义的sessionManager
 */
public class CustomSessionManager extends DefaultWebSessionManager {


    /**
     * 头信息中具有sessionid
     * 请求头：Authorization： sessionid
     * <p>
     * 指定sessionId的获取方式
     */
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {

        //获取请求头Authorization中的数据(前后端约定的自定义请求头)
        String id = WebUtils.toHttp(request).getHeader("Authorization");
        if (StringUtils.isEmpty(id)) {
            //如果没有携带，生成新的sessionId
            return super.getSessionId(request, response);
        } else {
            //返回sessionId；
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "header");// sessionId的来源
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id); // 值是什么
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE); // sessionId要不要验证
            return id;
        }
    }
}
