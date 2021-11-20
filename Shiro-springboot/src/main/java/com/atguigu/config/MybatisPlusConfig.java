package com.atguigu.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @title: MybatisPlusConfig
 * @Author Tan
 * @Date: 2021/11/20 18:33
 * @Version 1.0
 */
@Configuration
@MapperScan("com.atguigu.mapper")
public class MybatisPlusConfig {

    //下划线转驼峰
    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer(){
        return configuration -> configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
    }

}

