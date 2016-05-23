package com.plucial.mulcms.controller.mulcms.template.widget;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.plucial.mulcms.model.WidgetTemplate;
import com.plucial.mulcms.service.template.PageTemplateService;

public class EditController extends Controller {

    @Override
    public Navigation run() throws Exception {
        String keyString = asString("keyString");
        WidgetTemplate template = (WidgetTemplate)PageTemplateService.get(keyString);
        
        requestScope("template", template);
        requestScope("name", template.getName());
        requestScope("cssQuery", template.getCssQuery());
        requestScope("html", template.getContentString());
        
        return forward("edit.jsp");
    }
}
