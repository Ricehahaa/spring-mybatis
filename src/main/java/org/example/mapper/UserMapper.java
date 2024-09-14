package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.example.entity.Student;

public interface UserMapper {

    @Insert("insert into user (username, password) values (#{username},#{password}) ")
    public void userInsert(Student stu);
}
