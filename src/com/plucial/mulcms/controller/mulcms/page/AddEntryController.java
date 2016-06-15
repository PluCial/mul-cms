package com.plucial.mulcms.controller.mulcms.page;

import java.util.Map;
import java.util.Properties;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.google.appengine.api.users.User;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.global.Lang;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.validator.NGValidator;

public class AddEntryController extends BaseController {

    @Override
    public Navigation execute(Map<String, String> appPropertyMap, User user,
            Properties userLocaleProp) throws Exception {
        
        // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/mulcms/page/add");
        }
        
        String url = asString("url");
        
        String keyString = url.replace("../", "");
        if(!keyString.startsWith("/")) {
            keyString = "/" + keyString;
        }
        
        // 拡張子チェック
        if(!keyString.endsWith(".html")) {
            Validators v = new Validators(request);
            v.add("url",
                new NGValidator("URLは(.html)で終わる必要があります。"));
            v.validate();
            return forward("/mulcms/page/");
        }

        // 重複チェック
        try {
            PageService.get(keyString);
            Validators v = new Validators(request);
            v.add("url",
                new NGValidator("このURLはすでに存在するため、追加できません。"));
            v.validate();
            return forward("/mulcms/page/");
            
        }catch(ObjectNotExistException e) {}
        
        Lang lang = Lang.valueOf(asString("lang"));
        String html = asString("html");
        
        PageService.add(keyString, lang, html);
        
        return redirect("/mulcms/");
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);

        v.add("url", v.required());
        v.add("html", v.required());
        v.add("lang", v.required());
        
        return v.validate();
    }
}
