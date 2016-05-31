package com.plucial.mulcms.controller;

import java.util.List;

import org.jsoup.nodes.Element;
import org.slim3.controller.Navigation;

import com.google.apphosting.api.ApiProxy;
import com.plucial.gae.global.exception.NoContentsException;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.global.Lang;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.Template;
import com.plucial.mulcms.model.res.Res;
import com.plucial.mulcms.service.JsoupService;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.res.ResService;

public class FrontController extends AppController {

    @Override
    public Navigation execute(Lang localeLang) throws Exception {
        
        System.out.println(asString("lang") + ": " + asString("path"));
        
        ApiProxy.Environment env = ApiProxy.getCurrentEnvironment();
        String packetName = env.getAttributes().get("com.google.appengine.runtime.default_version_hostname").toString();
        
        try {
            Page page = PageService.get(asString("path"));
            Template template = page.getTemplateRef().getModel();
            JsoupService jsoupService = new JsoupService(template.getHtmlString());
            
            // base URL を追加
            Element head = jsoupService.getDoc().head();
            head.prepend("<base href='" + "https://storage.googleapis.com/" + packetName + "/'>");
            
            // テキストリソースを取得と合成
            List<Res> textResList = ResService.getAssetsAllResList(page, localeLang);
            
            // リソースの挿入
            for(Res res: textResList) {
                jsoupService.renderingHTML(res.getCssQuery(), res.getValueString(), res.getRenderingType());
            }
            
            requestScope("pageHtml", jsoupService.getDoc().outerHtml());
            
            
        }catch(ObjectNotExistException e) {
            throw new NoContentsException();
        }
        
        return forward("front.jsp");
    }
}
