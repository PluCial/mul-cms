package com.plucial.mulcms.controller.mulcms.page;

import org.slim3.controller.Navigation;

import com.plucial.global.Lang;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.model.template.Template;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.res.AppLangResService;
import com.plucial.mulcms.service.res.AppResService;
import com.plucial.mulcms.service.res.AssetsLangResService;
import com.plucial.mulcms.service.res.AssetsResService;

public class SettingController extends BaseController {

    @Override
    public Navigation run() throws Exception {

        // Page
        Page targetPage = (Page)PageService.get(asString("keyString"));
        requestScope("targetPage", targetPage);

        Lang lang = null;
        try {
            lang = Lang.valueOf(asString("lang"));
        }catch(Exception e) {
            Template template = targetPage.getTemplateRef().getModel();
            lang = template.getLang();
        }

        requestScope("appResList", AppResService.getList());
        requestScope("assetsResList", AssetsResService.getList(targetPage));
        requestScope("appLangResList", AppLangResService.getList(lang));
        requestScope("assetsLangResList", AssetsLangResService.getList(targetPage, lang));

        return forward("setting.jsp");
    }
}
