package com.plucial.mulcms.controller.mulcms.page;

import org.slim3.controller.Navigation;

import com.plucial.global.Lang;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.service.assets.PageService;

public class DeleteLangEntryController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        
        String keyString = asString("keyString");
        Page page = PageService.get(keyString);
        
        Lang lang = Lang.valueOf(asString("lang"));
        
        PageService.delete(page, lang);
        
        return redirect("/mulcms/page/");
    }
}
