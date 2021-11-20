package com.atguigu;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroSpringbootApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        User user = userService.queryUserByName("624597867");
        System.out.println(user);

    }

}
