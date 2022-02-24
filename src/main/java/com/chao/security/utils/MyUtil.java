package com.chao.security.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class MyUtil {
    /**
     * 创建 jwt 使用Hs256算法
     * @param jwtSec 密钥
     * @param subject 主体
     * @return jwt(token)
     */

    public static String createJwt(String jwtSec, String subject) {

        // 指定签名的时候使用的签名算法，也就是header那部分
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 创建payload的私有声明（根据特定的业务需要添加）
        /*Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("username", username);*/
        JwtBuilder jwtBuilder = Jwts.builder()
                // 是JWT的唯一标识
                .setId(UUID.fastUUID().toString(true))
                .setSubject(subject)
                // 设置过期时间
                .setExpiration(DateUtil.offsetMinute(new Date(), 3))
                // 设置算法和密钥
                .signWith(signatureAlgorithm, jwtSec);
        return jwtBuilder.compact();
    }

    /**
     * 解析 jwt(token)
     * @param jwtSec 密钥
     * @param jwt jwt
     * @return
     */
    public static Claims parseJwt(String jwtSec, String jwt) {
        return Jwts.parser()
                // 密钥
                .setSigningKey(jwtSec)
                // jwt
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static void main(String[] args) {
        /*String id1 = UUID.fastUUID().toString(false);
        String id2 = UUID.fastUUID().toString(true); // 无'-'
        System.out.println(id1);
        System.out.println(id2);*/
        String jwt = createJwt("chao", "123456");
        System.out.println(jwt);
        Claims claims = parseJwt("chao", jwt);
        System.out.println(claims.getSubject());
    }
}
