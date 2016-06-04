package com.plucial.mulcms.controller.mulcms.page;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.plucial.global.Lang;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.service.assets.PageService;

public class ExtractionResEntryController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        String keyString = asString("keyString");
        Page page = PageService.get(keyString);
        
        Lang pageLang = Lang.valueOf(asString("lang"));
        
        PageService.extractionRes(page);
        
        return redirect("/mulcms/page/setting?keyString=" + page.getKey().getName() + "&lang=" + pageLang.toString());
    }
}
