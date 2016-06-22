package com.plucial.mulcms.controller.mulcms.res;

import java.util.Map;
import java.util.Properties;

import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.model.res.AttrRes;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.res.ResService;

public class UpdateAttrResController extends BaseController {

    @Override
    public Navigation execute(Map<String, String> appPropertyMap, User user,
            Properties userLocaleProp) throws Exception {
        
        Page page = (Page)PageService.get(asString("parentKeyString"));
        AttrRes res = (AttrRes)ResService.get(page, asString("keyString"));
        requestScope("res", res);
        
        requestScope("cssQuery", res.getCssQuery());
        requestScope("attr", res.getAttr());
        requestScope("content", res.getValueString());
        
        return forward("updateAttrRes.jsp");
    }
}
