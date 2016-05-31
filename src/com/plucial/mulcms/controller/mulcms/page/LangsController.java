package com.plucial.mulcms.controller.mulcms.page;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.service.assets.PageService;

public class LangsController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        // Page
        Page targetPage = PageService.get(asString("keyString"));
        requestScope("targetPage", targetPage);
        
        return forward("langs.jsp");
    }
}
