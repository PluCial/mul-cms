package com.plucial.mulcms.controller.mulcms.page;

import org.slim3.controller.Navigation;

import com.plucial.global.Lang;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.service.assets.PageService;

public class ExtractionResEntryController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        
        String keyString = asString("keyString");
        Page page = (Page)PageService.get(keyString);
        
        Lang pageLang = Lang.valueOf(asString("lang"));
        
        PageService.extractionRes(page);
        
        return redirect("/mulcms/page/setting?keyString=" + page.getKey().getName() + "&lang=" + pageLang.toString());
    }
}
