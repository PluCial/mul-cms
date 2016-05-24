package com.plucial.mulcms.controller.mulcms.page.widget;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.Widget;
import com.plucial.mulcms.service.assets.WidgetService;

public class DeleteEntryController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        String keyString = asString("keyString");

        Widget model = (Widget)WidgetService.get(keyString);
        WidgetService.delete(model);
        
        Page page = (Page)model.getParentRef().getModel();
        
        return redirect("/mulcms/page/page/view?keyString=" + page.getKey().getName());
    }
}
