package org.example.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.Test;
import org.example.mapper.UserMapper;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ZhouyuFactoryBean implements FactoryBean {

    private Class mapperClass;

    private SqlSession sqlSession;

    public ZhouyuFactoryBean(Class mapperClass){
        this.mapperClass = mapperClass;
    }
    //依赖注入SqlSessionFactory,来生成SqlSession
    //跟以前的很像,这里可能@Autowired冒红线,但是可以运行
    public void setSqlSession(SqlSessionFactory sqlSessionFactory){
        //org.example.mapper.UserMapper is not known to the MapperRegistry.
        //这句话也需要配置不然报上面的错误
        sqlSessionFactory.getConfiguration().addMapper(mapperClass);
        //true是设置自动提交
        this.sqlSession = sqlSessionFactory.openSession(true);
    }

    @Override
    public Object getObject() throws Exception {
        //mybatis底层也是用jdk的动态代理的
        return sqlSession.getMapper(mapperClass);
    }

    @Override
    public Class<?> getObjectType() {
        return mapperClass;
    }
}
