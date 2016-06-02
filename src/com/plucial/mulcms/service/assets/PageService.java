package com.plucial.mulcms.service.assets;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Transaction;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.gae.global.exception.TransException;
import com.plucial.global.Lang;
import com.plucial.mulcms.App;
import com.plucial.mulcms.dao.PageDao;
import com.plucial.mulcms.exception.TooManyException;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.PageTemplate;
import com.plucial.mulcms.model.Template;
import com.plucial.mulcms.model.res.AssetsLangRes;
import com.plucial.mulcms.model.res.Res;
import com.plucial.mulcms.service.GoogleTransService;
import com.plucial.mulcms.service.JsoupService;
import com.plucial.mulcms.service.res.AssetsLangResService;
import com.plucial.mulcms.service.res.ResService;


public class PageService extends AssetsService {
    
    /** DAO */
    private static final PageDao dao = new PageDao();
    
    /**
     * 追加
     * @param keyString
     * @param lang
     * @param template
     * @return
     * @throws TooManyException
     */
    public static Page put(String keyString, PageTemplate template) throws TooManyException {
        
        // 重複チェック
        try {
            get(keyString);
            throw new TooManyException();
        } catch (ObjectNotExistException e) {
            // 重複してなければ登録可能
        }
        
        Page model = new Page();
        model.setKey(createKey(keyString));
        model.getTemplateRef().setModel(template);
        
        dao.put(model);
        
        return model;
    }
    
    /**
     * リソースの読み込み
     * @param page
     * @param lang
     */
    public static void extractionRes(Page page) {
        
        Template template = page.getTemplateRef().getModel();
        JsoupService JsoupService = new JsoupService(template.getHtmlString());
        
        Transaction tx = Datastore.beginTransaction();
        try {
            ResService.addResByDoc(tx, page, template.getLang(), JsoupService.getDoc());
            
            if(page.getLangList().indexOf(template.getLang()) < 0) {
                page.getLangList().add(template.getLang());
                Datastore.put(tx, page);
            }

            tx.commit();
        }finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }
    }
    
    /**
     * リストの取得
     * @return
     */
    public static List<Page> getList() {
        return dao.getList();
    }
    
    /**
     * 
     * @param template
     * @return
     */
    public static List<Page> getList(Template template) {
        return dao.getList(template);
    }
    
    /**
     * 取得
     * @param keyString
     * @return
     * @throws ObjectNotExistException
     */
    public static Page get(String keyString) throws ObjectNotExistException {
        Page model = dao.getOrNull(createKey(keyString));
        if(model == null) throw new ObjectNotExistException();
        
        return model;
    }
    
    /**
     * HTMLドキュメントの取得
     * @param page
     * @return
     * @throws ObjectNotExistException
     */
    public static Document getHtmlDocument(Page page, String packetName) throws ObjectNotExistException {
        // Pageテンプレートの取得
        Template template = page.getTemplateRef().getModel();
        JsoupService jsoupService = new JsoupService(template.getHtmlString());
//        
//        // Widget List の取得
//        List<Widget> widgetList = WidgetService.getList(page);
//        for(Widget widget: widgetList) {
//            jsoupService.renderingHTML(widget.getCssQuery(), widget.getHtmlString(), RenderingType.append);
//        }
        
        Element head = jsoupService.getDoc().head();
        head.prepend("<base href='" + "https://storage.googleapis.com/" + packetName + "/'>");
        
//        // CSS リンクの書き換え
//        Elements linkTags = jsoupService.getDoc().select("link");
//        for(Element linkTag: linkTags) {
//            String href = linkTag.attr("href");
//            href = href.replace("../", "");
//            href = "https://storage.googleapis.com/" + packetName + "/" + href;
//            
//            linkTag.attr("href", href);
//        }
//        
//        // script srcの書き換え
//        Elements scriptTags = jsoupService.getDoc().select("script");
//        for(Element scriptTag: scriptTags) {
//            String src = scriptTag.attr("src");
//            src = src.replace("../", "");
//            src = "https://storage.googleapis.com/" + packetName + "/" + src;
//            
//            scriptTag.attr("src", src);
//        }
//        
//        // img srcの書き換え
//        Elements imgTags = jsoupService.getDoc().select("img");
//        for(Element tag: imgTags) {
//            String src = tag.attr("src");
//            src = src.replace("../", "");
//            src = "https://storage.googleapis.com/" + packetName + "/" + src;
//            
//            tag.attr("src", src);
//        }
        
        return jsoupService.getDoc();
    }
    
    /**
     * 更新
     * @param model
     */
    public static void update(Page model) {
        dao.put(model);
    }
    
    /**
     * 翻訳
     * @param page
     * @param transSrcLang
     * @param transTargetLang
     * @throws TransException 
     * @throws ObjectNotExistException 
     */
    public static void trans(Page model, Lang transSrcLang, Lang transTargetLang) throws TransException, ObjectNotExistException {
        // ---------------------------------------------------
        // 翻訳元のコンテンツリスト
        // ---------------------------------------------------
        List<Res> transSrcList = ResService.getAssetsTransResList(model, transSrcLang);
        if(transSrcList.size() <= 0) throw new ObjectNotExistException();

        Transaction tx = Datastore.beginTransaction();
        try {
            
            GoogleTransService googleTransService = 
                    new GoogleTransService(App.GOOGLE_API_PUBLIC_SERVER_KEY, App.GOOGLE_API_APPLICATION_NAME);
            googleTransService.machineTrans(tx, model, transSrcLang, transTargetLang, transSrcList);

            if(model.getLangList().indexOf(transTargetLang) < 0) {
                model.getLangList().add(transTargetLang);
                Datastore.put(tx, model);
            }

            tx.commit();
        }finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }
    }
    
    /**
     * 削除
     * @param model
     */
    public static void delete(Page model) {
        
        List<Res> resList = ResService.getPageResList(model);
        
        Transaction tx = Datastore.beginTransaction();
        try {
            for(Res res: resList) {
                AssetsLangResService.delete(tx, res);
            }
            Datastore.delete(tx, model.getKey());

            tx.commit();

        }finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }
    }

    /**
     * 言語削除
     * @param model
     */
    public static void delete(Page model, Lang lang) {
        
        List<AssetsLangRes> resList = AssetsLangResService.getList(model, lang);
        
        Transaction tx = Datastore.beginTransaction();
        try {
            for(Res res: resList) {
                AssetsLangResService.delete(tx, res);
            }
            model.getLangList().remove(lang);
            Datastore.put(tx, model);

            tx.commit();

        }finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }
    }

}
