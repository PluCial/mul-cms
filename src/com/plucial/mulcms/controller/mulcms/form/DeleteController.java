package com.plucial.mulcms.controller.mulcms.form;

import java.util.Map;
import java.util.Properties;

import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.model.widgets.form.Form;
import com.plucial.mulcms.service.widgets.form.FormService;

public class DeleteController extends BaseController {

    @Override
    public Navigation execute(Map<String, String> appPropertyMap, User user,
            Properties userLocaleProp) throws Exception {
        
        String keyString = asString("keyString");
        Form form = (Form)FormService.get(keyString);
        requestScope("form", form);
        
        Page page = (Page)form.getAssetsRef().getModel();
        requestScope("page", page);
        
        
        
        return forward("delete.jsp");
    }
}
