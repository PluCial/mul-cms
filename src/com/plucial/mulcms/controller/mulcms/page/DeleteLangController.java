package com.plucial.mulcms.controller.mulcms.page;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.service.assets.PageService;

public class DeleteLangController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        String keyString = asString("keyString");
        Page page = PageService.get(keyString);
        
        requestScope("page", page);
        
        return forward("deleteLang.jsp");
    }
}
