package com.plucial.mulcms.controller.mulcms.page;

import org.slim3.controller.Navigation;

import com.plucial.global.Lang;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.res.Res;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.res.ResService;

public class DeleteResEntryController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        String keyString = asString("keyString");
        Lang lang = Lang.valueOf(asString("lang"));
        
        Res model = ResService.get(keyString);
        
        Page page = PageService.get(asString("pageKey"));
        
        ResService.delete(model);
        
        return redirect("/mulcms/page/setting?keyString=" + page.getKey().getName() + "&lang=" + lang.toString());
    }
}
