package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.example.entity.Student;

public interface OrderMapper {

    @Insert("insert into user (username, password) values (#{username},#{password}) ")
    public void orderInsert(Student stu);
}
