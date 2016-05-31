package com.plucial.mulcms.controller.mulcms.page;

import org.jsoup.nodes.Document;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.plucial.global.Lang;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.res.AppLangResService;
import com.plucial.mulcms.service.res.AppResService;
import com.plucial.mulcms.service.res.AssetsLangResService;
import com.plucial.mulcms.service.res.AssetsResService;
import com.plucial.mulcms.utils.HtmlUtils;

public class ViewController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        // Page
        Page targetPage = PageService.get(asString("keyString"));
        requestScope("targetPage", targetPage);
        
        Lang lang = Lang.valueOf(asString("lang"));
        
        Document pageDoc = PageService.getHtmlDocument(targetPage, "");
        requestScope("pageHtml", HtmlUtils.htmlEscape(pageDoc.outerHtml()));
        
        requestScope("appResList", AppResService.getList());
        requestScope("assetsResList", AssetsResService.getList(targetPage));
        requestScope("appLangResList", AppLangResService.getList(lang));
        requestScope("assetsLangResList", AssetsLangResService.getList(targetPage, lang));
        
        return forward("view.jsp");
    }
}
