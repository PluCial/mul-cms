package com.plucial.mulcms.controller.mulcms.template;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.Template;
import com.plucial.mulcms.service.template.PageTemplateService;

public class DeleteController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        
        String keyString = asString("keyString");
        Template template = PageTemplateService.get(keyString);
        
        requestScope("template", template);
        
        return forward("delete.jsp");
    }
}
