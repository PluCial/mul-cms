package com.plucial.mulcms.controller.mulcms.form;

import java.util.Map;
import java.util.Properties;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.google.appengine.api.users.User;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.model.widgets.form.Form;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.widgets.form.FormService;

public class UpdateEntryController extends BaseController {

    @Override
    public Navigation execute(Map<String, String> appPropertyMap, User user,
            Properties userLocaleProp) throws Exception {
        
        String keyString = asString("keyString");
        Page page = (Page)PageService.get(asString("parentKeyString"));
        
        Form model = (Form)FormService.get(page, keyString);
        
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
        
        
        return redirect("/mulcms/form/setting?keyString=" + keyString + "&parentKeyString=" + page.getKey().getName());
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
