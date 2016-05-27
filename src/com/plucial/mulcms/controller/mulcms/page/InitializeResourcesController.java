package com.plucial.mulcms.controller.mulcms.page;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.plucial.global.Lang;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.res.TextResService;

public class InitializeResourcesController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        String keyString = asString("keyString");
        Page page = PageService.get(keyString);
        
        TextResService.initialize(page, Lang.ja);
        
        return forward("initializeResources.jsp");
    }
}
