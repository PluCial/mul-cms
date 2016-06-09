package com.plucial.mulcms.service.assets;

import java.util.List;

import org.jsoup.nodes.Document;

import com.plucial.global.Lang;
import com.plucial.mulcms.dao.assets.MailDao;
import com.plucial.mulcms.exception.TooManyException;
import com.plucial.mulcms.model.assets.Mail;
import com.plucial.mulcms.model.res.Res;
import com.plucial.mulcms.model.template.MailTemplate;
import com.plucial.mulcms.model.template.PageTemplate;
import com.plucial.mulcms.model.template.Template;
import com.plucial.mulcms.service.JsoupService;
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
    public static Mail put(String name, PageTemplate template) throws TooManyException {
        
        Mail model = new Mail();
        model.setKey(createKey());
        model.setName(name);
        model.getTemplateRef().setModel(template);
        
        dao.put(model);
        
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
     * 使用しているテンプレートからリストを取得
     * @param template
     * @return
     */
    public static List<Mail> getList(MailTemplate template) {
        return dao.getList(template);
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
        
        Template template = mail.getTemplateRef().getModel();
        JsoupService jsoupService = new JsoupService(template.getHtmlString());
        
        // ----------------------------------------------------
        // BodyのLang属性を追加
        // ----------------------------------------------------
        jsoupService.getDoc().body().attr("lang", localeLang.toString());
        
        // ----------------------------------------------------
        // テキストリソースの挿入
        // ----------------------------------------------------     
        List<Res> textResList = ResService.getAssetsAllResList(mail, localeLang);
        renderedRes(jsoupService, textResList, domainUrl, isSigned);
        
        return jsoupService.getDoc();
    }
}
