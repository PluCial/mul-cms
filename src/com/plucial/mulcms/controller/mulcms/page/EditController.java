package com.plucial.mulcms.controller.mulcms.page;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.service.assets.PageService;

public class EditController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        
        String keyString = asString("keyString");
        Page page = (Page) PageService.get(keyString);
        
        requestScope("page", page);
        requestScope("html", page.getHtmlString());
        
        return forward("edit.jsp");
    }
}
