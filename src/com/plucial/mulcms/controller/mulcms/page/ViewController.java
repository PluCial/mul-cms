package com.plucial.mulcms.controller.mulcms.page;

import java.util.List;

import org.jsoup.nodes.Document;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.Template;
import com.plucial.mulcms.model.Widget;
import com.plucial.mulcms.model.WidgetTemplate;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.assets.WidgetService;
import com.plucial.mulcms.service.template.PageTemplateService;
import com.plucial.mulcms.service.template.WidgetTemplateService;
import com.plucial.mulcms.utils.HtmlUtils;

public class ViewController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        // Template List
        List<? extends Template> templateList = PageTemplateService.getList();
        requestScope("templateList", templateList);
        
        // Page
        Page targetPage = PageService.get(asString("keyString"));
        requestScope("targetPage", targetPage);
        
        Document pageDoc = PageService.getHtmlDocument(targetPage, "");
        requestScope("pageHtml", HtmlUtils.htmlEscape(pageDoc.outerHtml()));
        
        List<Widget> widgetList = WidgetService.getList(targetPage);
        for(Widget widget: widgetList) {
            WidgetService.settingTemplate(widget);
        }
        requestScope("widgetList", widgetList);
        
        List<WidgetTemplate> widgetTemplateList = WidgetTemplateService.getList();
        requestScope("widgetTemplateList", widgetTemplateList);
        
        return forward("view.jsp");
    }
}
