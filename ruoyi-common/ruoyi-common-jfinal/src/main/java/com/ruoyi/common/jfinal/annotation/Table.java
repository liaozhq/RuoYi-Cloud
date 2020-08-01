package com.ruoyi.common.jfinal.annotation;

import com.jfinal.plugin.activerecord.Model;

import java.lang.annotation.*;

/**
 * @author liaozhenqin
 * @date 2017-08-01
 * */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Table {

    String tableName();

    Class<? extends Model<?>> clazz();

    String primaryKey() default "id";
}
