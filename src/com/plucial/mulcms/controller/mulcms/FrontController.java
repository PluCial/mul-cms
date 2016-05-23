package com.plucial.mulcms.controller.mulcms;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.global.Lang;
import com.plucial.mulcms.service.assets.PageService;

public class FrontController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        System.out.println(asString("lang") + ": " + asString("path"));
        
        Lang lang = Lang.valueOf(asString("lang"));
        
        try {
            String pageHtml = PageService.getHtml(asString("path"), lang);
            requestScope("pageHtml", pageHtml);
        }catch(ObjectNotExistException e) {
            
        }
        
        return forward("front.jsp");
    }
}
