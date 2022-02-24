package com.chao.security.service.impl;

import com.chao.security.entity.LoginUser;
import com.chao.security.entity.ResponseResult;
import com.chao.security.entity.User;
import com.chao.security.service.LoginService;
import com.chao.security.utils.MyUtil;
import com.chao.security.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Value("${jwt.jwtSec}")
    private String jwtSec;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        // AuthenticationManager  authenticate  进行用户认证
        // 拿到用户输入的用户名和密码，封装成 authenticate 对象
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        // 让 authenticationManager 拿着信息帮助我们去进行认证操作，查数据库校验
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }

        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();

        String token = MyUtil.createJwt(jwtSec, userId);
        System.out.println("jwtSec----------" + jwtSec);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        redisCache.setCacheObject("login:"+userId, loginUser);
        return new ResponseResult(200, "登录成功", map);
    }

    @Override
    public ResponseResult logout() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        long userId = loginUser.getUser().getId();
        redisCache.deleteObject("login:" + userId);
        return new ResponseResult(200, "退出成功");
    }
}
