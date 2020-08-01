package com.qin.video;

import com.jfinal.aop.Interceptor;
import com.jfinal.config.Routes;
import com.jfinal.core.Controller;
import com.jfinal.handler.Handler;
import com.jfinal.render.Render;
import com.ruoyi.common.jfinal.annotation.Ctrl;
import com.ruoyi.common.jfinal.annotation.JfinalModelScan;
import com.ruoyi.common.jfinal.annotation.JfinalScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

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
public class QinVideoApplication {
    public static void main(String[] args) {
        SpringApplication.run(QinVideoApplication.class, args);
    }
}
