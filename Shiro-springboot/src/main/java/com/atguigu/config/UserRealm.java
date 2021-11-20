package com.atguigu.config;


import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Security;

/**
 * @title: UserRealm
 * @Author Tan
 * @Date: 2021/11/20 16:14
 * @Version 1.0
 */
//自定义的UserRealm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");
        //授予用户权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //拿到当前登录的这个对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User)subject.getPrincipal();   //拿到user对象

        //设置当前用户的权限
        info.addStringPermission(currentUser.getPerms());

        return info;

    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthorizationInfo");
        // 用户名、密码  (数据库中取)
//        String name = "root";
//        String password = "123456";
        userService.queryUserByName("userToken.getUsername()");

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        String username = userToken.getUsername();
        //数据库中查询用户
        User user = userService.queryUserByName(username);
        if (user==null){
//            没有这个人 UnknownAccountException e
            return null;
        }

        //将登录的用户信息放入 session (shiro中的session) 中
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser",user);

        // 密码认证，shiro做
        // （principal: 获取当前用户的认证   credential: 密码对象   realmName: 认证名）
        return new SimpleAuthenticationInfo(user,user.getPassWord(),"");
    }

}



