package com.chao.security;

import com.chao.security.mapper.MenuMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class SecurityApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testBCryptPasswordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        /*String encode = passwordEncoder.encode("123456");
        System.out.println(encode);*/
        //$2a$10$sfH4g7Rgu2/6/q8GALoo6O4gNaMzxr//1/pyOwP0kH7STp9PwnNfS
        boolean b = passwordEncoder.matches("123456", "$2a$10$sfH4g7Rgu2/6/q8GALoo6O4gNaMzxr//1/pyOwP0kH7STp9PwnNfS");
        System.out.println(b);
    }

    @Test
    public void testMenuMapper(@Autowired MenuMapper menuMapper) {
        List<String> list = menuMapper.selectPermsByUserId(1l);
        System.out.println(list);
    }
}
