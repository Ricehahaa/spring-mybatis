package org.example.service;


import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")
public class BService {

    @Resource
    AService aService;


//    @Lazy
//    public BService(AService aService){}
}
