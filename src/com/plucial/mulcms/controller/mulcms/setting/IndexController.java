package com.plucial.mulcms.controller.mulcms.setting;

import java.util.Map;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.controller.AppController;

public class IndexController extends AppController {

    @Override
    public Navigation run() throws Exception {
        
        Map<String, String> appPropertyMap = super.getAppPropertyMap();
        requestScope("appPropertyMap", appPropertyMap);
        
        return forward("index.jsp");
    }
}
