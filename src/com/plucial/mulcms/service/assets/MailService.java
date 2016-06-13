package com.plucial.mulcms.service.assets;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.datastore.Transaction;
import com.plucial.global.Lang;
import com.plucial.mulcms.dao.assets.MailDao;
import com.plucial.mulcms.exception.TooManyException;
import com.plucial.mulcms.model.RenderingItem;
import com.plucial.mulcms.model.assets.Mail;
import com.plucial.mulcms.model.res.Res;
import com.plucial.mulcms.service.res.ResService;


public class MailService extends AssetsService {
    
    /** DAO */
    private static final MailDao dao = new MailDao();
    
    /**
     * 追加
     * @param keyString
     * @param lang
     * @param template
     * @return
     * @throws TooManyException
     */
    public static Mail add(String name, String html, Lang htmlLang) throws TooManyException {
        
        Document doc = Jsoup.parse(html);
        
        Mail model = new Mail();
        model.setKey(createKey());
        model.setName(name);
        model.setHtmlLang(htmlLang);
        model.setHtml(new Text(doc.outerHtml()));
        model.getLangList().add(htmlLang);
        
        Transaction tx = Datastore.beginTransaction();
        try {
            Datastore.put(tx, model);
            
            ResService.importByDoc(tx, model, model.getHtmlLang(), doc);
            
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
    public static List<Mail> getList() {
        return dao.getList();
    }
    

    
    /**
     * レンダリング後のドキュメントを取得
     * @param page
     * @param localeLang
     * @param gcsBucketName
     * @param domainUrl
     * @param isSigned
     * @return
     */
    public static Document getRenderedDoc(Mail mail, Lang localeLang, String gcsBucketName, String domainUrl, boolean isSigned) {
        
        Document doc = Jsoup.parse(mail.getHtmlString());
        
        // ----------------------------------------------------
        // BodyのLang属性を追加
        // ----------------------------------------------------
        doc.body().attr("lang", localeLang.toString());
        
        // ----------------------------------------------------
        // base URL を追加
        // ----------------------------------------------------
        doc.head().prepend("<base href='" + "https://storage.googleapis.com/" + gcsBucketName + "/'>");
        
        // ----------------------------------------------------
        // リソースのレンダリング
        // ----------------------------------------------------     
        List<? extends Res> textResList = ResService.getList(mail, localeLang);
        for(Res res: textResList) {
            RenderingItem item = (RenderingItem)res;
            item.reenderingDoc(doc, localeLang, domainUrl, isSigned);
        }
        
        return doc;
    }
}
