package com.plucial.mulcms.controller.mulcms.page;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.datastore.Text;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.TextRes;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.res.TextResService;

public class UpdateTextResEntryController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        String keyString = asString("keyString");
        String content = asString("content");
        String pageKey = asString("pageKey");
        
        Page page = PageService.get(pageKey);
        TextRes model = TextResService.get(keyString);
        model.setContent(new Text(content));
        
        TextResService.update(model);
        
        return redirect("/mulcms/page/view?keyString=" + page.getKey().getName());
    }
}
