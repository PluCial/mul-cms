package com.plucial.mulcms.controller.mulcms.page;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.datastore.Text;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.res.Res;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.res.ResService;

public class UpdateResEntryController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        String keyString = asString("keyString");
        String content = asString("content");
        String pageKey = asString("pageKey");
        
        Page page = PageService.get(pageKey);
        Res model = ResService.get(keyString);
        model.setValue(new Text(content));
        
        ResService.update(model);
        
        return redirect("/mulcms/page/view?keyString=" + page.getKey().getName());
    }
}
