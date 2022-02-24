package com.chao.security.handler;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSONArray;
import com.chao.security.entity.ResponseResult;
import com.chao.security.utils.WebUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.HTTP_FORBIDDEN, "权限不足");
        String json = JSONArray.toJSONString(result);
        WebUtil.renderString(response, json);
    }
}
