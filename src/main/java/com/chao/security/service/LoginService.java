package com.chao.security.service;

import com.chao.security.entity.ResponseResult;
import com.chao.security.entity.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
