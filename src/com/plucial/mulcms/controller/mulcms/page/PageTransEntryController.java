package com.plucial.mulcms.controller.mulcms.page;

import java.util.Map;
import java.util.Properties;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.util.StringUtil;

import com.google.appengine.api.users.User;
import com.plucial.global.Lang;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.enums.AppProperty;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.service.assets.PageService;

public class PageTransEntryController extends BaseController {

    @Override
    public Navigation execute(Map<String, String> appPropertyMap, User user,
            Properties userLocaleProp) throws Exception {
        
        // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/mulcms/page/resource?keyString=" + asString("keyString") + "&lang=" + asString("targetLang"));
        }
        
        Lang srcLang = Lang.valueOf(asString("srcLang"));
        Lang targetLang = Lang.valueOf(asString("targetLang"));
        Page page = (Page)PageService.get(asString("keyString"));
        boolean transAll = !StringUtil.isEmpty(asString("transAll"));
        
        // App Property 取得
        String googleApiPublicServerKey = appPropertyMap.get(AppProperty.GOOGLE_API_PUBLIC_SERVER_KEY.toString());
        String googleApiApplicationName = appPropertyMap.get(AppProperty.APP_ID.toString());
        
        // 翻訳 & 言語ページの作成
        PageService.trans(googleApiPublicServerKey, googleApiApplicationName, page, srcLang, targetLang, transAll);
        
        return redirect("/mulcms/page/resource?keyString=" + page.getKey().getName() + "&lang=" + targetLang.toString());
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);

        v.add("keyString", v.required());
        v.add("srcLang", v.required("翻訳元の言語を選択してください"));
        v.add("targetLang", v.required());
        
        return v.validate();
    }
}
