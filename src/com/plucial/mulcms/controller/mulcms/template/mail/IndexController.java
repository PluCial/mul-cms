package com.plucial.mulcms.controller.mulcms.template.mail;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.plucial.mulcms.model.template.Template;
import com.plucial.mulcms.service.template.MailTemplateService;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        List<? extends Template> templateList = MailTemplateService.getList();
        requestScope("templateList", templateList);
        
        return forward("index.jsp");
    }
}
