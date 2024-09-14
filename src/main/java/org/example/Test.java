package org.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.entity.Student;
import org.example.mapper.OrderMapper;
import org.example.mapper.UserMapper;
import org.example.mybatis.ZhouyuFactoryBean;
import org.example.mybatis.ZhouyuImportBeanDefinitionRegistrar;
import org.example.mybatis.ZhouyuScan;
import org.example.service.AService;
import org.example.service.UserService;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@ComponentScan("org.example")
@ZhouyuScan("org.example.mapper")
public class Test {

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws IOException {  //直接参数得到Bean对象
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        return sqlSessionFactory;
    }


    public static void main(String[] args) throws IOException {
        //注意这里不可以再写成AnnotationConfigApplicationContext applicationContext =
        // new AnnotationConfigApplicationContext(Test.class);
        //因为如果直接构造函数传参的话,内部会进行refresh操作,下面又会refresh一下,而GenericApplicationContext不支持多次刷新尝试

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Test.class);
        //refresh容器
        applicationContext.refresh();

        UserService userService = (UserService) applicationContext.getBean("userService", UserService.class);
        userService.test();


//        AService aService = applicationContext.getBean("AService", AService.class);

//        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        sqlSessionFactory.getConfiguration().addMapper(UserMapper.class);
//        SqlSession sqlSession = sqlSessionFactory.openSession(true);
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        mapper.userInsert(new Student("111","111",100L));
    }
}
