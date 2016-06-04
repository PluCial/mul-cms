package com.plucial.mulcms.controller.mulcms.template.page;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.PageTemplate;
import com.plucial.mulcms.service.template.PageTemplateService;

public class EditController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        
        String keyString = asString("keyString");
        PageTemplate template = (PageTemplate)PageTemplateService.get(keyString);
        
        requestScope("template", template);
        requestScope("name", template.getName());
        requestScope("html", template.getHtmlString());
        
        return forward("edit.jsp");
    }
}
