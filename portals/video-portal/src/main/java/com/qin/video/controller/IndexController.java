package com.qin.video.controller;

import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.render.FreeMarkerRender;
import com.ruoyi.common.jfinal.annotation.Ctrl;

import java.util.List;

@Ctrl(value = "/", viewPath = IndexController.VIEW_PATH)
public class IndexController extends Controller {
    
    public static final String VIEW_PATH = "index/";

    public void index(@Para(value = "title", defaultValue = "") String title) {
        List<Record> records = Db.find(Db.getSqlPara("user.queryUser", Kv.by("title", title)));
        renderJson(records);
    }

    public void setting(){
        FreeMarkerRender.setTemplateLoadingPath("classes/templates/");
        render("setting.html");
    }
}
