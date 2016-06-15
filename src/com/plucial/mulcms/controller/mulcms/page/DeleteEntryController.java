package com.plucial.mulcms.controller.mulcms.page;

import java.util.Map;
import java.util.Properties;

import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.service.assets.PageService;

public class DeleteEntryController extends BaseController {

    @Override
    public Navigation execute(Map<String, String> appPropertyMap, User user,
            Properties userLocaleProp) throws Exception {
        
        String keyString = asString("keyString");
        Page page = (Page)PageService.get(keyString);
        
        PageService.delete(page);
        
        return redirect("/mulcms/");
    }
}
