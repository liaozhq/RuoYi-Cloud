package com.ruoyi.common.jfinal.configure;

import com.ruoyi.common.jfinal.annotation.Table;
import com.ruoyi.common.jfinal.utils.KitClassUtils;
import lombok.Data;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.Set;

@Data
public class ClassPathTableScanner   extends ClassPathBeanDefinitionScanner {

    private Class<? extends Annotation> annotationClass;

    public ClassPathTableScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public ClassPathTableScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry, useDefaultFilters);
    }

    public ClassPathTableScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters, Environment environment) {
        super(registry, useDefaultFilters, environment);
    }

    public ClassPathTableScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters, Environment environment, @Nullable ResourceLoader resourceLoader) {
        super(registry, useDefaultFilters, environment, resourceLoader);
    }

    /**
     * 配置扫描接口
     * 扫描添加了markerInterfaces标志类的类或标注了annotationClass注解的类,
     * 或者扫描所有类
     */
    public void registerFilters() {

        // if specified, use the given annotation and / or marker interface
        if (this.annotationClass != null) {
            addIncludeFilter(new AnnotationTypeFilter(this.annotationClass));
        }

    }

        /**
         * Calls the parent search that will search and register all the candidates.
         * Then the registered objects are post processed to set them as
         * MapperFactoryBeans
         */
    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);

        beanDefinitions.forEach(beanDefinition->{
            String beanclassname = beanDefinition.getBeanDefinition().getBeanClassName();
            try {
                Class onwClass = Class.forName(beanclassname);
                KitClassUtils.tableclass.add(onwClass);
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        if (beanDefinitions.isEmpty()) {
            logger.warn("No DgbSecurity Spring Componet was found in '" + Arrays.toString(basePackages) + "' package. Please check your configuration.");
        }

        return beanDefinitions;
    }
}