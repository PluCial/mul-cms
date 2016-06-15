package com.plucial.mulcms.controller.mulcms.form.control;

import java.util.Map;
import java.util.Properties;

import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.service.widgets.form.FormControlService;

public class DeleteEntryController extends BaseController {

    @Override
    public Navigation execute(Map<String, String> appPropertyMap, User user,
            Properties userLocaleProp) throws Exception {
        
        String keyString = asString("keyString");
        
        FormControlService.get(keyString);

        FormControlService.delete(keyString);
        
        return redirect("/mulcms/form/setting?keyString=" + asString("formKey"));
    }
}
