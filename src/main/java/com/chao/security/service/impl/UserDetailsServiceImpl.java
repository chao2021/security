package com.chao.security.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chao.security.entity.LoginUser;
import com.chao.security.entity.User;
import com.chao.security.mapper.MenuMapper;
import com.chao.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserName, username));
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或密码错误");
        }

        List<String> list = menuMapper.selectPermsByUserId(user.getId());
        //List<String> list = new ArrayList<>(Arrays.asList("test", "admin"));

        return new LoginUser(user, list);
    }
}
