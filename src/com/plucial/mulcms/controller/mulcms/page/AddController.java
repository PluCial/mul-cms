package com.plucial.mulcms.controller.mulcms.page;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.plucial.mulcms.model.Template;
import com.plucial.mulcms.service.template.PageTemplateService;

public class AddController extends Controller {

    @Override
    public Navigation run() throws Exception {
        List<? extends Template> templateList = PageTemplateService.getList();
        requestScope("templateList", templateList);
        
        return forward("add.jsp");
    }
}