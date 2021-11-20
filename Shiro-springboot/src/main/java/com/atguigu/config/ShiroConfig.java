package com.atguigu.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @title: ShiroConfig
 * @Author Tan
 * @Date: 2021/11/20 16:11
 * @Version 1.0
 */
@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean （连接到前端）
    //DefaultWebSecurityManager (接管对象)
    //创建 Realm 对象 （自定义）


    //3. shiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
//        anon： 无需认证就可以访问
//        authc： 必须认证了才能访问
//        user： 必须拥有记住我功能才能用
//        perms： 拥有对某个资源的权限才能访问
//        role： 拥有某个角色权限
        Map<String, String> filterMap = new LinkedHashMap<>();

        //授权请求 (正常情况下没有授权会跳转到未授权页面, 含有该请求参数才能进入 )
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");

        //添加shiro的内置过滤器 拦截请求
//        filterMap.put("/user/add","authc");
//        filterMap.put("/user/update","authc");
        filterMap.put("/user/*","authc");

        bean.setFilterChainDefinitionMap(filterMap);

        //未登录设置前往登录的页面
        bean.setLoginUrl("/toLogin");

        //权限不够设置未授权页面
        bean.setUnauthorizedUrl("/noauto");

        return bean;
    }

    //2. DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联userRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //1. 创建realm对象，需要自定义类
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }


    // 整合ShiroDialect： 用来整合 Shiro thymeleaf
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }
}


