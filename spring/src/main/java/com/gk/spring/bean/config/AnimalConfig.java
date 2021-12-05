package com.gk.spring.bean.config;

import com.gk.spring.bean.animal.Cat;
import com.gk.spring.bean.animal.Pig;
import com.gk.spring.bean.selector.AnimalImportBeanDefinitionRegister;
import com.gk.spring.bean.selector.AnimalImportSelector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author gaot
 * @date 2021/7/24
 */
@Configuration
@Import({Pig.class, AnimalImportSelector.class, AnimalImportBeanDefinitionRegister.class})
@ComponentScan("com.gk.spring.bean")
public class AnimalConfig {
    @Bean
    public Cat cat() {
        return new Cat();
    }
}
