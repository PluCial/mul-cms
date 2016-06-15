package com.plucial.mulcms.controller.mulcms.res;

import java.util.Map;
import java.util.Properties;

import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.res.Res;
import com.plucial.mulcms.service.res.ResService;

public class DeleteResController extends BaseController {

    @Override
    public Navigation execute(Map<String, String> appPropertyMap, User user,
            Properties userLocaleProp) throws Exception {
        
        String keyString = asString("keyString");
        Res res = (Res)ResService.get(keyString);
        
        requestScope("res", res);
        
        return forward("deleteRes.jsp");
    }
}
