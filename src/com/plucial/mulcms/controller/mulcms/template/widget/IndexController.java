package com.plucial.mulcms.controller.mulcms.template.widget;

import java.util.List;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.template.Template;
import com.plucial.mulcms.service.template.WidgetTemplateService;

public class IndexController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        
        List<? extends Template> templateList = WidgetTemplateService.getList();
        requestScope("templateList", templateList);
        
        return forward("index.jsp");
    }
}
