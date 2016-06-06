package com.plucial.mulcms.controller.mulcms.form;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.form.Form;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.form.FormService;

public class UpdateEntryController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        
        String keyString = asString("keyString");
        Form model = FormService.get(keyString);
        
        // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/mulcms/form/setting?keyString=" + keyString);
        }
        
        String name = asString("name");
        Page page = PageService.get(asString("pageKey"));
        Page transitionPage = PageService.get(asString("transitionPageKey"));
        
        model.setName(name);
        model.getPageRef().setModel(page);
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
        v.add("pageKey", v.required());
        v.add("transitionPageKey", v.required());
        
        return v.validate();
    }
}
