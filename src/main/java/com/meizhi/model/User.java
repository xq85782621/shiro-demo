package com.meizhi.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.crazycake.shiro.AuthCachePrincipal;

import java.io.Serializable;


@TableName("user")
@Getter
@Setter
@ToString
public class User implements Serializable, AuthCachePrincipal {

    // 用户ID
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // 用户名
    private String username;

    // 密码
    private String password;

    // 联系电话
    private String phone;


    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
