package com.chao.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu implements Serializable {

    private Long id;
    private String menuName;
    private String path;
    private String component;
    private String visible;
    private String status;
    private String perms;
    private String icon;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;
    private String delFlag;
    private String remark;
}
