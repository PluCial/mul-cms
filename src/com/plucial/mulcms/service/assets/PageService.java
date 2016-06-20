package com.plucial.mulcms.service.assets;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.datastore.Transaction;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.global.Lang;
import com.plucial.mulcms.dao.assets.PageDao;
import com.plucial.mulcms.exception.TooManyException;
import com.plucial.mulcms.model.RenderingItem;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.model.res.Res;
import com.plucial.mulcms.model.widgets.form.Form;
import com.plucial.mulcms.service.res.ResService;
import com.plucial.mulcms.service.widgets.form.FormService;


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
    public static Page add(String keyString, Lang htmlLang, String html) throws TooManyException {
     // 重複チェック
      try {
          get(keyString);
          throw new TooManyException();
      } catch (ObjectNotExistException e) {
          // 重複してなければ登録可能
      }
      
      Document doc = Jsoup.parse(html);
      
      Page model = new Page();
      model.setKey(createKey(keyString));
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
    public static List<Page> getList() {
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
    public static Document getRenderedDoc(Page page, Lang localeLang, String gcsBucketName, String domainUrl, boolean isSigned) {
        Document doc = Jsoup.parse(page.getHtmlString());
        
        // ----------------------------------------------------
        // base URL を追加
        // ----------------------------------------------------
        Element head = doc.head();
        doc.head().prepend("<base href='" + "https://storage.googleapis.com/" + gcsBucketName + "/'>");
        
        // ----------------------------------------------------
        // 言語Alternate の追加
        // ----------------------------------------------------
        for(Lang lang: page.getLangList()) {
            if(localeLang != lang) {
                head.append("<link rel='alternate' hreflang='" + lang.toString() + "' href='" + domainUrl + "/" + lang.toString() + page.getKey().getName() + "' />");
            }
        }
        
        // ----------------------------------------------------
        // BodyのLang属性を追加
        // ----------------------------------------------------
        doc.body().attr("lang", localeLang.toString());
        
        // ----------------------------------------------------
        // リンクの書き換え
        // ----------------------------------------------------
        Elements links = doc.select("a");
        for(Element link: links) {
            if(!link.hasAttr("href")) continue;
            String linkHref = link.attr("href");
            if(linkHref.startsWith("http")) continue;

            linkHref = linkHref.replace("../", "");
            if(linkHref.startsWith("/")) {
                linkHref = domainUrl + "/" + localeLang.toString() + linkHref;
            }else {
                linkHref = domainUrl + "/" + localeLang.toString() + "/" + linkHref;
            }
            
            link.attr("href", linkHref);
        }
        
        // ----------------------------------------------------
        // 言語切り替え
        // ----------------------------------------------------
        Element langSelectElm = doc.getElementById("lang-select");
        if(langSelectElm != null) {
            for(Lang lang: page.getLangList()) {
                if(lang == localeLang) {
                    langSelectElm.append("<option value='" + lang.toString() + "' selected>" + lang.getName() + "</option>");
                }else {
                    langSelectElm.append("<option value='" + lang.toString() + "'>" + lang.getName() + "</option>");
                }
            }
        }
        doc.body().append("<script>jQuery(function() {$('select#lang-select').change(function() {var selectedval = $(this).val();location.href = $('link[hreflang=' + selectedval + ']').eq(0).attr('href');});});</script>");
        
        // ----------------------------------------------------
        // テキストリソースのレンダーリング
        // ----------------------------------------------------
        List<? extends Res> textResList = ResService.getList(page, localeLang);
        for(Res res: textResList) {
            RenderingItem item = (RenderingItem)res;
            item.reenderingDoc(doc, localeLang, domainUrl, isSigned);
        }
        
        // ----------------------------------------------------
        // Form のレンダリング
        // ----------------------------------------------------
        List<Form> fromList = FormService.getList(page);
        for(Form form: fromList) {
            RenderingItem item = (RenderingItem)form;
            item.reenderingDoc(doc, localeLang, domainUrl, isSigned);
        }
        
        return doc;
    }
    
    

}
