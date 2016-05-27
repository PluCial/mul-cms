package com.plucial.mulcms.controller.mulcms.page;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.TextRes;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.res.TextResService;

public class DeleteTextResEntryController extends Controller {

    @Override
    public Navigation run() throws Exception {
        String keyString = asString("keyString");
        TextRes model = TextResService.get(keyString);
        
        Page page = PageService.get(asString("pageKey"));
        
        TextResService.delete(model);
        
        return redirect("/mulcms/page/view?keyString=" + page.getKey().getName());
    }
}
