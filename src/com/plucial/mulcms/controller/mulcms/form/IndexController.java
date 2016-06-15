package com.plucial.mulcms.controller.mulcms.form;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.model.widgets.form.Form;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.widgets.form.FormService;

public class IndexController extends BaseController {

    @Override
    public Navigation execute(Map<String, String> appPropertyMap, User user,
            Properties userLocaleProp) throws Exception {
        
        Page page = (Page)PageService.get(asString("keyString"));
        requestScope("page", page);
        
        List<Form> formList = FormService.getList(page);
        requestScope("formList", formList);
        
        List<Page> pageList = PageService.getList();
        requestScope("pageList", pageList);
        
        return forward("index.jsp");
    }
}
