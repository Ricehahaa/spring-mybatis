package org.example.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class myAop {

    @Before("execution(* org.example.service.UserService.test())")  //execution写法跟之前一样
    public void before(){
        System.out.println("我是之前执行的内容！");
    }
}
