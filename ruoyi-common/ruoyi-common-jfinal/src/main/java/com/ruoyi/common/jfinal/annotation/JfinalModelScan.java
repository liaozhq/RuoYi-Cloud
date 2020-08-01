package com.ruoyi.common.jfinal.annotation;

import com.ruoyi.common.jfinal.configure.TableScannerRegistrar;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(TableScannerRegistrar.class)
public @interface JfinalModelScan {

    String[] basePackages() default {};

}
