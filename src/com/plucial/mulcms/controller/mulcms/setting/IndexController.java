package com.plucial.mulcms.controller.mulcms.setting;

import java.util.Map;
import java.util.Properties;

import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;
import com.plucial.mulcms.controller.mulcms.BaseController;

public class IndexController extends BaseController {

    @Override
    public Navigation execute(Map<String, String> appPropertyMap, User user,
            Properties userLocaleProp) throws Exception {
        
        requestScope("appPropertyMap", appPropertyMap);
        
        return forward("index.jsp");
    }
}
