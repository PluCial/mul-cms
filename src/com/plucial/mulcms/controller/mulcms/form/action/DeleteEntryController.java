package com.plucial.mulcms.controller.mulcms.form.action;

import java.util.Map;
import java.util.Properties;

import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.model.widgets.form.Form;
import com.plucial.mulcms.model.widgets.form.FormAction;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.widgets.form.FormActionService;

public class DeleteEntryController extends BaseController {

    @Override
    public Navigation execute(Map<String, String> appPropertyMap, User user,
            Properties userLocaleProp) throws Exception {
        
        String keyString = asString("keyString");
        Page page = (Page)PageService.get(asString("parentKeyString"));
        
        FormAction action = FormActionService.get(keyString);
        
        Form form = action.getFormRef().getModel();
        
        FormActionService.delete(action);
        
        return redirect("/mulcms/form/setting?keyString=" + form.getKey().getName() + "&parentKeyString=" + page.getKey().getName());
    }
}
