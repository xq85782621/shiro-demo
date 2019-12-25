package com.meizhi.relam;


import cn.hutool.json.JSONUtil;
import com.meizhi.model.User;
import com.meizhi.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义的realm
 */
public class CustomRealm extends AuthorizingRealm {

    public void setName(String name) {
        super.setName("customRealm");
    }

    @Autowired
    private UserService userService;

    /**
     * 授权方法
     *      操作的时候，判断用户是否具有响应的权限
     *          先认证 -- 安全数据
     *          再授权 -- 根据安全数据获取用户具有的所有操作权限
     *
     *
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.获取已认证的用户数据
        User user = (User) principalCollection.getPrimaryPrincipal();//得到唯一的安全数据
        //2.根据用户数据获取用户的权限信息（所有角色，所有权限）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();//所有角色
        roles.add("admin");
        Set<String> perms = new HashSet<>();//所有权限
        perms.add("userInfo");

        System.err.println(perms);
        System.err.println(roles);
        info.setStringPermissions(perms);
        info.setRoles(roles);
        return info;
    }


    /**
     * 认证方法
     *  参数：传递的用户名密码
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.err.println("===========");
        //1.获取登录的用户名密码（token）
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        System.err.println("===========");
        String username = upToken.getUsername();
        String password = new String( upToken.getPassword());

        System.err.println(username);
        System.err.println(password);

        //2.根据用户名查询数据库
        User user = userService.findUserByUsernameAndPassword(username,password);

        System.err.println(JSONUtil.toJsonStr(user));

        //3.判断用户是否存在或者密码是否一致
        if(user != null ) {
            //4.如果一致返回安全数据
            //构造方法：安全数据，密码，realm域名
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
            return info;

        }
        //5.不一致，返回null（抛出异常）
        return null;
    }


}
