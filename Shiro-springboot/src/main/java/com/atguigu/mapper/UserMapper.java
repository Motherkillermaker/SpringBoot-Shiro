package com.atguigu.mapper;

import com.atguigu.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
// 在对应的Mapper上面继承基本的类 BaseMapper
public interface UserMapper extends BaseMapper<User> {
    // 所有的CRUD操作都已经编写完成了
    // 你不需要像以前的配置一大堆文件了！
}
