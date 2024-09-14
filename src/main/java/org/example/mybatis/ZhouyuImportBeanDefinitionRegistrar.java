package org.example.mybatis;

import org.example.mapper.OrderMapper;
import org.example.mapper.UserMapper;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

public class ZhouyuImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //扫描路径
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(ZhouyuScan.class.getName());
        String path = (String) annotationAttributes.get("value");


        ZhouyuScanner zhouyuScanner = new ZhouyuScanner(registry);
        zhouyuScanner.doScan(path);
    }
}
