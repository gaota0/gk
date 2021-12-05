package com.gk.spring.bean.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author gaot
 * @date 2021/7/24
 */
public class AnimalImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {"com.gk.spring.bean.animal.Elephant"};
    }
}
