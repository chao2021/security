package com.chao.security.handler;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSONArray;
import com.chao.security.entity.ResponseResult;
import com.chao.security.utils.WebUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.HTTP_UNAUTHORIZED, "用户认证失败");
        String json = JSONArray.toJSONString(result);
        WebUtil.renderString(response, json);
    }
}
