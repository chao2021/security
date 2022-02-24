package com.chao.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chao.security.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(@Param("id") Long id);
}
