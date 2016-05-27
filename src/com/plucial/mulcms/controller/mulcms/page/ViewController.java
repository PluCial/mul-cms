package com.plucial.mulcms.controller.mulcms.page;

import java.util.List;

import org.jsoup.nodes.Document;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.plucial.global.Lang;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.TextRes;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.res.TextResService;
import com.plucial.mulcms.utils.HtmlUtils;

public class ViewController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        // Page
        Page targetPage = PageService.get(asString("keyString"));
        requestScope("targetPage", targetPage);
        
        Document pageDoc = PageService.getHtmlDocument(targetPage, "");
        requestScope("pageHtml", HtmlUtils.htmlEscape(pageDoc.outerHtml()));
        
        List<TextRes> appTextResList = TextResService.getAppResList(Lang.ja);
        requestScope("appTextResList", appTextResList);
        
        List<TextRes> pageTextResList = TextResService.getPageResList(targetPage, Lang.ja);
        requestScope("pageTextResList", pageTextResList);
        
        return forward("view.jsp");
    }
}
