package com.gk.spring.bean.config;

import com.gk.spring.bean.animal.Elephant;
import com.gk.spring.bean.animal.Pig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author gaot
 * @date 2021/7/24
 */
class AnimalConfigTest {
    @Test
    public void testBean() {
        final AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AnimalConfig.class);
        //@Bean
        Assertions.assertNotNull(context.getBean("cat"));
        //ComponentScan
        Assertions.assertNotNull(context.getBean("dog"));
        // ImportBeanDefinitionRegistrar
        Assertions.assertNotNull(context.getBean("duck"));
        // importSelector
        Assertions.assertNotNull(context.getBeansOfType(Elephant.class));
        // @Import
        Assertions.assertNotNull(context.getBeansOfType(Pig.class));

    }

}