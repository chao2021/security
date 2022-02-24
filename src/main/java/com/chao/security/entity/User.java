package com.chao.security.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author  ljc 
 * @since 2022-02-11 15:45 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User implements Serializable {

	/**
	 * table name:id
	 * table type:bigint
	 * table comment:主键
	 */
	@TableId
	private Long id;

	/**
	 * table name:user_name
	 * table type:varchar(64)
	 * table comment:用户名
	 */
	private String userName;

	/**
	 * table name:nick_name
	 * table type:varchar(64)
	 * table comment:昵称
	 */
	private String nickName;

	/**
	 * table name:password
	 * table type:varchar(64)
	 * table comment:密码
	 */
	private String password;

	/**
	 * table name:status
	 * table type:char(1)
	 * table comment:账号状态（0正常 1停用）
	 */
	private String status;

	/**
	 * table name:email
	 * table type:varchar(64)
	 * table comment:邮箱
	 */
	private String email;

	/**
	 * table name:phonenumber
	 * table type:varchar(32)
	 * table comment:手机号
	 */
	private String phonenumber;

	/**
	 * table name:sex
	 * table type:char(1)
	 * table comment:用户性别（0男，1女，2未知）
	 */
	private String sex;

	/**
	 * table name:avatar
	 * table type:varchar(128)
	 * table comment:头像
	 */
	private String avatar;

	/**
	 * table name:user_type
	 * table type:char(1)
	 * table comment:用户类型（0管理员，1普通用户）
	 */
	private String userType;

	/**
	 * table name:create_by
	 * table type:bigint
	 * table comment:创建人的用户id
	 */
	private Long createBy;

	/**
	 * table name:create_time
	 * table type:datetime
	 * table comment:创建时间
	 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

	/**
	 * table name:update_by
	 * table type:bigint
	 * table comment:更新人
	 */
	private Long updateBy;

	/**
	 * table name:update_time
	 * table type:datetime
	 * table comment:更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/**
	 * table name:del_flag
	 * table type:int
	 * table comment:删除标志（0代表未删除，1代表已删除）
	 */
	private Integer delFlag;

}
