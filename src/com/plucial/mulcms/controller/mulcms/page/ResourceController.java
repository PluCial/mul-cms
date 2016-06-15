package com.plucial.mulcms.controller.mulcms.page;

import java.util.Map;
import java.util.Properties;

import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;
import com.plucial.global.Lang;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.res.AttrResService;
import com.plucial.mulcms.service.res.InnerTextResService;

public class ResourceController extends BaseController {

    @Override
    public Navigation execute(Map<String, String> appPropertyMap, User user,
            Properties userLocaleProp) throws Exception {

        // Page
        Page page = (Page)PageService.get(asString("keyString"));
        requestScope("page", page);

        Lang lang = null;
        try {
            lang = Lang.valueOf(asString("lang"));
        }catch(Exception e) {
            lang = page.getHtmlLang();
        }

        requestScope("attrResList", AttrResService.getList(page, lang));
        requestScope("innerTextResList", InnerTextResService.getList(page, lang));

        return forward("resource.jsp");
    }
}
