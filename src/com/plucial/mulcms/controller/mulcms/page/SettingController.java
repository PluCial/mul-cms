package com.plucial.mulcms.controller.mulcms.page;

import org.jsoup.nodes.Document;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.plucial.global.Lang;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.Template;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.res.AppLangResService;
import com.plucial.mulcms.service.res.AppResService;
import com.plucial.mulcms.service.res.AssetsLangResService;
import com.plucial.mulcms.service.res.AssetsResService;
import com.plucial.mulcms.utils.HtmlUtils;

public class SettingController extends Controller {

    @Override
    public Navigation run() throws Exception {

        // Page
        Page targetPage = PageService.get(asString("keyString"));
        requestScope("targetPage", targetPage);

        Lang lang = null;
        try {
            lang = Lang.valueOf(asString("lang"));
        }catch(Exception e) {
            Template template = targetPage.getTemplateRef().getModel();
            lang = template.getLang();
        }

        Document pageDoc = PageService.getHtmlDocument(targetPage, "");
        requestScope("pageHtml", HtmlUtils.htmlEscape(pageDoc.outerHtml()));

        requestScope("appResList", AppResService.getList());
        requestScope("assetsResList", AssetsResService.getList(targetPage));
        requestScope("appLangResList", AppLangResService.getList(lang));
        requestScope("assetsLangResList", AssetsLangResService.getList(targetPage, lang));

        return forward("setting.jsp");
    }
}
