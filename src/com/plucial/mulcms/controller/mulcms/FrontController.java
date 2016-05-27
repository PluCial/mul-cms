package com.plucial.mulcms.controller.mulcms;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.apphosting.api.ApiProxy;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.global.Lang;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.TextRes;
import com.plucial.mulcms.service.JsoupService;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.res.TextResService;

public class FrontController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        System.out.println(asString("lang") + ": " + asString("path"));
        
        ApiProxy.Environment env = ApiProxy.getCurrentEnvironment();
        String packetName = env.getAttributes().get("com.google.appengine.runtime.default_version_hostname").toString();
        
        Lang lang = Lang.valueOf(asString("lang"));
        
        try {
            Page page = PageService.get(asString("path"));
            JsoupService jsoupService = new JsoupService(page.getHtmlString());
            
            // base URL を追加
            Element head = jsoupService.getDoc().head();
            head.prepend("<base href='" + "https://storage.googleapis.com/" + packetName + "/'>");
            
            // テキストリソースを取得と合成
            List<TextRes> textResList = new ArrayList<TextRes>();
            textResList.addAll(TextResService.getAppResList(lang));
            textResList.addAll(TextResService.getPageResList(page, lang));
            
            // リソースの挿入
            for(TextRes res: textResList) {
                jsoupService.renderingHTML(res.getCssQuery(), res.getContentString(), res.getRenderingType());
            }
            
            requestScope("pageHtml", jsoupService.getDoc().outerHtml());
            
            
        }catch(ObjectNotExistException e) {
            
        }
        
        return forward("front.jsp");
    }
}
