package com.plucial.mulcms.controller.mulcms.form;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.model.widgets.form.Form;
import com.plucial.mulcms.service.widgets.form.FormService;

public class DeleteEntryController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        
        String keyString = asString("keyString");

        Form form = (Form)FormService.get(keyString);
        Page page = (Page)form.getAssetsRef().getModel();
        FormService.delete(form);
        
        
        return redirect("/mulcms/form/?keyString=" + page.getKey().getName());
    }
}
