package com.plucial.mulcms.controller.mulcms.form;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.form.Form;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.form.FormService;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        List<Form> formList = FormService.getList();
        requestScope("formList", formList);
        
        List<Page> pageList = PageService.getList();
        requestScope("pageList", pageList);
        
        return forward("index.jsp");
    }
}
