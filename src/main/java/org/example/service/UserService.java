package org.example.service;

import jakarta.annotation.Resource;
import org.example.entity.Student;
import org.example.mapper.OrderMapper;
import org.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private OrderMapper orderMapper;

    public void test(){
        Student student = new Student("wuningfeng", "123456", 2L);
        userMapper.userInsert(student);
        orderMapper.orderInsert(student);
    }
}
