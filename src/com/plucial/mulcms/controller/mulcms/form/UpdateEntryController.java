package com.plucial.mulcms.controller.mulcms.form;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.model.widgets.form.Form;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.widgets.form.FormService;

public class UpdateEntryController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        
        String keyString = asString("keyString");
        Form model = (Form)FormService.get(keyString);
        
        // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/mulcms/form/setting?keyString=" + keyString);
        }
        
        String name = asString("name");
        String cssQuery = asString("cssQuery");
        
        Page transitionPage = (Page)PageService.get(asString("transitionPageKey"));
        
        model.setName(name);
        model.setCssQuery(cssQuery);
        model.getTransitionPageRef().setModel(transitionPage);
        
        FormService.update(model);
        
        
        return redirect("/mulcms/form/setting?keyString=" + keyString);
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);
        v.add("name", v.required());
        v.add("cssQuery", v.required());
        v.add("keyString", v.required());
        v.add("transitionPageKey", v.required());
        
        return v.validate();
    }
}
