package com.plucial.mulcms.controller.mulcms.res;

import java.util.Map;
import java.util.Properties;

import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;
import com.plucial.global.Lang;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.model.res.Res;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.res.ResService;

public class DeleteResEntryController extends BaseController {

    @Override
    public Navigation execute(Map<String, String> appPropertyMap, User user,
            Properties userLocaleProp) throws Exception {
        
        Page page = (Page)PageService.get(asString("parentKeyString"));
        String keyString = asString("keyString");
        Lang lang = Lang.valueOf(asString("lang"));
        
        Res model = (Res)ResService.get(page, keyString);
        
        
        
        ResService.delete(model);
        
        return redirect("/mulcms/page/resource?keyString=" + page.getKey().getName() + "&lang=" + lang.toString());
    }
}
