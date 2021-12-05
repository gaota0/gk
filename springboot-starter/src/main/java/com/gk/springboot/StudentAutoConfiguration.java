package com.gk.springboot;

import com.gk.springboot.pojo.Student;
import com.gk.springboot.starter.StudentProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gaot
 * @date 2021/12/5
 */
@EnableConfigurationProperties(StudentProperties.class)
@Configuration
@Slf4j
public class StudentAutoConfiguration {
    @Bean
    @ConditionalOnProperty(prefix = "student.vip",name = "name")
    public Student student(StudentProperties studentProperties) {
        final Student student = new Student();
        student.setAge(studentProperties.getAge());
        student.setName(studentProperties.getName());
        log.info("vip student 已加载...");
        return student;
    }
}
