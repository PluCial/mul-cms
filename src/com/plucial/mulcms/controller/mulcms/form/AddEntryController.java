package com.plucial.mulcms.controller.mulcms.form;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.form.FormService;

public class AddEntryController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        
        // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/mulcms/form/");
        }
        
        String formId = asString("formId");
        String name = asString("name");
        Page page = PageService.get(asString("pageKey"));
        Page transitionPage = PageService.get(asString("transitionPageKey"));
        
        FormService.put(formId, name, page, transitionPage);
        
        
        return redirect("/mulcms/form/");
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);

        v.add("formId", v.required());
        v.add("name", v.required());
        v.add("pageKey", v.required());
        v.add("transitionPageKey", v.required());
        
        return v.validate();
    }
}
