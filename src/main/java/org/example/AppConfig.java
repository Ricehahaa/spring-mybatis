//package org.example;
//
//import org.apache.ibatis.datasource.pooled.PooledDataSource;
//import org.example.entity.Student;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.context.annotation.*;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.sql.DataSource;
//
//@ComponentScans({
//        @ComponentScan("org.example.service"),
//})
//@Configuration
//public class AppConfig {
//
//
//    @Bean   //单独创建一个Bean，方便之后更换
//    public DataSource dataSource(){
//        return new PooledDataSource("com.mysql.cj.jdbc.Driver",
//                "jdbc:mysql://192.168.169.3:3306/study", "root", "123456");
//    }
//
//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){  //直接参数得到Bean对象
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        return bean;
//    }
//
//    //事务管理器
//    @Bean
//    public PlatformTransactionManager transactionManager(){
//        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
//        transactionManager.setDataSource(dataSource());
//        return transactionManager;
//    }
//}
