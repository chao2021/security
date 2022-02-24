package com.chao.security.filter;

import com.chao.security.entity.LoginUser;
import com.chao.security.utils.MyUtil;
import com.chao.security.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;
    @Value("${jwt.jwtSec}")
    private String jwtSec;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        //System.out.println("token----------" + token);

        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request,response);
            return;
        }

        Claims claims = MyUtil.parseJwt(jwtSec, token);
        //System.out.println("jwtSec2----------" + jwtSec);
        String userId = claims.getSubject();
        LoginUser loginUser = redisCache.getCacheObject("login:" + userId);

        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("用户未登录");
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);

    }
}
