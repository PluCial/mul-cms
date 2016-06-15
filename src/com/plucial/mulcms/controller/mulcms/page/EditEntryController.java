package com.plucial.mulcms.controller.mulcms.page;

import java.util.Map;
import java.util.Properties;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.google.appengine.api.users.User;
import com.plucial.global.Lang;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.service.assets.PageService;

public class EditEntryController extends BaseController {

    @Override
    public Navigation execute(Map<String, String> appPropertyMap, User user,
            Properties userLocaleProp) throws Exception {
     // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/mulcms/page/edit");
        }
        
        String keyString = asString("keyString");
        String html = asString("html");
        Lang lang = Lang.valueOf(asString("lang"));
        
        Page model = (Page)PageService.get(keyString);
        PageService.updateHtml(model, lang, html);
        
        return redirect("/mulcms/");
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);

        v.add("keyString", v.required());
        v.add("html", v.required());
        v.add("lang", v.required());
        
        return v.validate();
    }
}
