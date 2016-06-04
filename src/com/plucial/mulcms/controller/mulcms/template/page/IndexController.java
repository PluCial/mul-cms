package com.plucial.mulcms.controller.mulcms.template.page;

import java.util.List;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.Template;
import com.plucial.mulcms.service.template.PageTemplateService;

public class IndexController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        
        List<? extends Template> templateList = PageTemplateService.getList();
        requestScope("templateList", templateList);
        
        return forward("index.jsp");
    }
}
