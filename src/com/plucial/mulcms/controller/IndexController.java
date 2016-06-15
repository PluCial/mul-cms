package com.plucial.mulcms.controller;

import java.util.Map;
import java.util.Properties;

import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;
import com.plucial.global.Lang;
import com.plucial.mulcms.enums.AppProperty;

public class IndexController extends AppController {

    @Override
    public Navigation run() throws Exception {
        
        Map<String, String> appPropertyMap = super.getAppPropertyMap();
        Lang lang = Lang.valueOf(appPropertyMap.get(AppProperty.APP_BASE_LANG.toString()));
        
        
        return redirect("/" + lang.toString() + "/");
    }

    @Override
    protected Navigation notSigned(Map<String, String> appPropertyMap,
            Lang localeLang) throws Exception {
        return redirect("/" + localeLang.toString() + "/");
    }

    @Override
    protected Navigation signed(Map<String, String> appPropertyMap, User user,
            Lang localeLang, Properties userLocaleProp) throws Exception {
        return redirect("/" + localeLang.toString() + "/");
    }
}
