package com.plucial.mulcms.service.assets;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.datastore.Transaction;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.global.Lang;
import com.plucial.mulcms.dao.PageDao;
import com.plucial.mulcms.exception.TooManyException;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.PageTemplate;
import com.plucial.mulcms.model.Template;
import com.plucial.mulcms.model.TextRes;
import com.plucial.mulcms.service.JsoupService;
import com.plucial.mulcms.service.res.TextResService;


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
    public static Page put(String keyString, Lang lang, PageTemplate template) throws TooManyException {
        
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
        
        Transaction tx = Datastore.beginTransaction();
        try {
            Document doc = Jsoup.parse(template.getHtmlString());
            TextResService.addTextResByPage(tx, model, lang, doc);
            
            model.setHtml(new Text(doc.outerHtml()));

            Datastore.put(tx, model);

            tx.commit();

        }finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }
        
        return model;
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
        JsoupService jsoupService = new JsoupService(page.getHtmlString());
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
     * HTML 取得
     * @param keyString
     * @param lang
     * @return
     * @throws ObjectNotExistException
     */
    public static String getHtml(String keyString, Lang lang, String packetName) throws ObjectNotExistException {
        
        // ページの取得
        Page model = get(keyString);
        
        return getHtmlDocument(model, packetName).outerHtml();
    }
    
    /**
     * 更新
     * @param model
     */
    public static void update(Page model) {
        dao.put(model);
    }
    
    /**
     * テンプレート再読み込み
     * @param page
     * @param lang
     */
    public static void templateReread(Page model, Lang lang) {
        Transaction tx = Datastore.beginTransaction();
        try {
            
            // テンプレートの取得
            Template template = model.getTemplateRef().getModel();
            Document doc = Jsoup.parse(template.getHtmlString());
            
            // リソースの初期化
            TextResService.initialize(tx, model, lang, doc);
            
            // Page Html の更新
            model.setHtml(new Text(doc.outerHtml()));
            Datastore.put(tx, model);
            
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
    public static void delete(Page model, Lang lang) {
        
        List<TextRes> resList = TextResService.getPageResList(model, lang);
        
        Transaction tx = Datastore.beginTransaction();
        try {
            for(TextRes res: resList) {
                TextResService.delete(tx, res);
            }
            Datastore.delete(tx, model.getKey());

            tx.commit();

        }finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }
    }

}
