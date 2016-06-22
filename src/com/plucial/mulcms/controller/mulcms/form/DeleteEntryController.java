package com.plucial.mulcms.controller.mulcms.form;

import java.util.Map;
import java.util.Properties;

import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.model.widgets.form.Form;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.widgets.form.FormService;

public class DeleteEntryController extends BaseController {

    @Override
    public Navigation execute(Map<String, String> appPropertyMap, User user,
            Properties userLocaleProp) throws Exception {
        
        String keyString = asString("keyString");
        Page page = (Page)PageService.get(asString("parentKeyString"));
        
        Form form = (Form)FormService.get(page, keyString);
        FormService.delete(form);
        
        
        return redirect("/mulcms/form/?keyString=" + page.getKey().getName());
    }
}
