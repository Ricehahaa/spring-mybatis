package org.example.mybatis;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.util.Set;

public class ZhouyuScanner extends ClassPathBeanDefinitionScanner {
    //拿到用于注册bean的registry
    public ZhouyuScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected boolean isCandidateComponent(MetadataReader metadataReader) throws IOException {
        //默认扫描逻辑是判断是否有@Component注解
        //但现在我们就直接全部返回true,都要,不管是否有@Component注解
        //这个逻辑通过之过就会生成bean的定义BeanDefinition
//        return super.isCandidateComponent(metadataReader);
        return true;
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
    //在此之前通过了 isCandidateComponent(MetadataReader metadataReader) 生成了beanDefinition
    //默认处理 逻辑是判断是不是接口,是就不通过 (因为默认要既有@Component注解,也要是个类,才能被注册成bean啊)
//        return super.isCandidateComponent(beanDefinition);
        //现在处理逻辑,就变成了只要是接口才可以通过
        return beanDefinition.getMetadata().isInterface();
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        //super.doScan就是spring默认的扫描逻辑,我经过上面两个函数的判断
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
            //拿到扫描得到的bean的定义,这里得到bean还是普通的mapper接口
            GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
            //设置bean构造函数的参数,就是此时bean定义的类名(UserMapper.class等等)
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanDefinition.getBeanClassName());
            //此时再设置bean定义的类型,为工厂bean
            //千万别后面两句话写反了,因为一开始bean定义的类名就是mapper接口名字啊。
            //如果写反了,你就先把bean定义的类名覆盖了,拿不到构造函数的参数了啊
            beanDefinition.setBeanClass(ZhouyuFactoryBean.class);

            beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
        }
        return beanDefinitionHolders;
    }
}
