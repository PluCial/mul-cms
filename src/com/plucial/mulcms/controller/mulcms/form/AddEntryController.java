package com.plucial.mulcms.controller.mulcms.form;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.widgets.form.FormService;

public class AddEntryController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        
        // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/mulcms/form/");
        }
        
        String name = asString("name");
        String cssQuery = asString("cssQuery");
        Page page = (Page)PageService.get(asString("pageKeyString"));
        Page transitionPage = (Page)PageService.get(asString("transitionPageKey"));
        
        FormService.add(cssQuery, name, page, transitionPage);
        
        
        return redirect("/mulcms/form/?keyString=" + page.getKey().getName());
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);

        v.add("name", v.required());
        v.add("cssQuery", v.required());
        v.add("pageKeyString", v.required());
        v.add("transitionPageKey", v.required());
        
        return v.validate();
    }
}
