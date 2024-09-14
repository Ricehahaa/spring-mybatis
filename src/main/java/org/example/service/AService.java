package org.example.service;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class AService {

    @Resource
    BService bService;

//    public AService(BService bService){}
}
