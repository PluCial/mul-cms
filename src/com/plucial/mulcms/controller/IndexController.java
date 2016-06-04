package com.plucial.mulcms.controller;

import java.util.Map;

import org.slim3.controller.Navigation;

import com.plucial.global.Lang;
import com.plucial.mulcms.enums.AppProperty;

public class IndexController extends AppController {

    @Override
    public Navigation run() throws Exception {
        
        Map<String, String> appPropertyMap = super.getAppPropertyMap();
        Lang lang = Lang.valueOf(appPropertyMap.get(AppProperty.APP_BASE_LANG.toString()));
        
        
        return redirect("/" + lang.toString() + "/");
    }
}
