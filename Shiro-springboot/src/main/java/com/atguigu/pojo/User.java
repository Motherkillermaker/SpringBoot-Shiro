package com.atguigu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @title: User
 * @Author Tan
 * @Date: 2021/11/20 18:34
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@TableName(value = "user")
public class User {
    @TableId(type = IdType.AUTO)
    private int userId;

    private String userName;

    private String passWord;

    private String perms;
}

