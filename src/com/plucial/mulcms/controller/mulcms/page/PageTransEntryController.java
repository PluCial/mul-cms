package com.plucial.mulcms.controller.mulcms.page;

import java.util.Map;

import org.slim3.controller.Navigation;

import com.plucial.global.Lang;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.enums.AppProperty;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.service.assets.PageService;

public class PageTransEntryController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        
        Lang srcLang = Lang.valueOf(asString("srcLang"));
        Lang targetLang = Lang.valueOf(asString("targetLang"));
        
        Page page = PageService.get(asString("keyString"));
        
        // App Property 取得
        Map<String, String> appPropertyMap = super.getAppPropertyMap();
        String googleApiPublicServerKey = appPropertyMap.get(AppProperty.GOOGLE_API_PUBLIC_SERVER_KEY.toString());
        String googleApiApplicationName = appPropertyMap.get(AppProperty.APP_ID.toString());
        
        // 翻訳
        PageService.trans(googleApiPublicServerKey, googleApiApplicationName, page, srcLang, targetLang);
        // 項目コピー
        PageService.copyNotTransRes(page, srcLang, targetLang);
        
        return redirect("/mulcms/page/setting?keyString=" + page.getKey().getName() + "&lang=" + targetLang.toString());
    }
}
