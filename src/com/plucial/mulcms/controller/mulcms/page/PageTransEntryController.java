package com.plucial.mulcms.controller.mulcms.page;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.plucial.global.Lang;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.service.assets.PageService;

public class PageTransEntryController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        Lang srcLang = Lang.valueOf(asString("srcLang"));
        Lang targetLang = Lang.valueOf(asString("targetLang"));
        
        Page page = PageService.get(asString("keyString"));
        
        PageService.trans(page, srcLang, targetLang);
        
        return redirect("/mulcms/page/setting?keyString=" + page.getKey().getName() + "&lang=" + targetLang.toString());
    }
}
