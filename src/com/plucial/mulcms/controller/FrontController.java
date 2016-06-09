package com.plucial.mulcms.controller;

import java.util.Map;

import org.jsoup.nodes.Document;
import org.slim3.controller.Navigation;

import com.plucial.gae.global.exception.NoContentsException;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.mulcms.enums.AppProperty;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.service.assets.PageService;

public class FrontController extends AppController {

    @Override
    protected Navigation run() throws Exception {
        
        // ----------------------------------------------------
        // App 設定情報の取得
        // ----------------------------------------------------
        Map<String, String> appPropertyMap = super.getAppPropertyMap();
        String gcsBucketName = appPropertyMap.get(AppProperty.APP_GCS_BUCKET_NAME.toString());
        
        try {
            // ----------------------------------------------------
            // Page 情報
            // ----------------------------------------------------
            Page page = (Page)PageService.get(asString("path"));
            if(page.getLangList().indexOf(super.getLocaleLang()) < 0) {
                throw new NoContentsException();
            }

            // ----------------------------------------------------
            // Page 生成
            // ----------------------------------------------------
            Document pageDoc = PageService.getRenderedDoc(page, super.getLocaleLang(), gcsBucketName, super.getDomainUrl(), isSigned());
            requestScope("pageHtml", pageDoc.outerHtml());
            
            
        }catch(ObjectNotExistException e) {
            throw new NoContentsException();
        }
        
        return forward("/front.jsp");
    }
    
    
}
