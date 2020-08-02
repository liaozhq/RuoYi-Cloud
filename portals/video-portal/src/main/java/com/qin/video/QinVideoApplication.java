package com.qin.video;

import com.jfinal.aop.Interceptor;
import com.jfinal.config.Routes;
import com.jfinal.core.Controller;
import com.jfinal.handler.Handler;
import com.jfinal.render.FreeMarkerRender;
import com.jfinal.render.Render;
import com.ruoyi.common.jfinal.annotation.Ctrl;
import com.ruoyi.common.jfinal.annotation.JfinalModelScan;
import com.ruoyi.common.jfinal.annotation.JfinalScan;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.IOException;

@JfinalScan(
        basePackages = "com.qin.video",
        markerInterfaces = {
                Controller.class,
                Interceptor.class,
                Routes.class,
                Handler.class,
                Render.class
        },
        annotationClass = Ctrl.class)
@JfinalModelScan(basePackages = {"com.qin.video"})
@Configuration
@SpringBootApplication
public class QinVideoApplication implements ApplicationListener<ApplicationReadyEvent> {
    public static void main(String[] args) {
        SpringApplication.run(QinVideoApplication.class, args);
    }


    /**
     * 容器加载完成之后执行的方法
     * 需继承ApplicationListener<ApplicationReadyEvent>接口实现
     * */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            //************ 设置freemarker页面根路径 start ************//
            String freemarkerpath = "templates";
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
            TemplateLoader templateLoader=new FileTemplateLoader(new File(path+freemarkerpath));
            FreeMarkerRender.getConfiguration().setTemplateLoader(templateLoader);
            //************ 设置freemarker更路径 end   ************//
        }catch (IOException e){

        }
    }
}
