package com.plucial.mulcms.controller.mulcms;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.Template;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.template.PageTemplateService;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
//        return redirect("/mulcms/page/");
        List<? extends Template> templateList = PageTemplateService.getList();
        requestScope("templateList", templateList);
        
        List<Page> pageList = PageService.getList();
        requestScope("pageList", pageList);
        
        return forward("index.jsp");
    }
}
