package com.gk.springboot.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author gaot
 * @date 2021/12/5
 */
@Data
@ConfigurationProperties(prefix = "student.vip")
public class StudentProperties {
    private String name;
    private Integer age;
}
