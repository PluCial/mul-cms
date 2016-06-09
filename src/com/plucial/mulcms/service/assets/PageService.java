package com.plucial.mulcms.service.assets;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.global.Lang;
import com.plucial.mulcms.dao.assets.PageDao;
import com.plucial.mulcms.enums.MulAttrType;
import com.plucial.mulcms.exception.TooManyException;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.model.form.Form;
import com.plucial.mulcms.model.res.Res;
import com.plucial.mulcms.model.template.PageTemplate;
import com.plucial.mulcms.model.template.Template;
import com.plucial.mulcms.service.JsoupService;
import com.plucial.mulcms.service.form.FormService;
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
     * リストの取得
     * @return
     */
    public static List<Page> getList() {
        return dao.getList();
    }
    
    /**
     * 使用しているテンプレートからリストを取得
     * @param template
     * @return
     */
    public static List<Page> getList(Template template) {
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
    public static Document getRenderedDoc(Page page, Lang localeLang, String gcsBucketName, String domainUrl, boolean isSigned) {
        
        Template template = page.getTemplateRef().getModel();
        JsoupService jsoupService = new JsoupService(template.getHtmlString());
        
        // ----------------------------------------------------
        // base URL を追加
        // ----------------------------------------------------
        Element head = jsoupService.getDoc().head();
        head.prepend("<base href='" + "https://storage.googleapis.com/" + gcsBucketName + "/'>");
        
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
        jsoupService.getDoc().body().attr("lang", localeLang.toString());
        
        // ----------------------------------------------------
        // Form
        // ----------------------------------------------------
        List<Form> formList = FormService.getList(page);
        for(Form form: formList) {
            Element formElem = jsoupService.getDoc().select("[" + MulAttrType.formId.getAttr() + "=" + form.getKey().getName() + "]").first();
            if(formElem != null) {
                formElem.attr("action", domainUrl + "/mulcms/form/action");
                formElem.attr("method", "post");
                formElem.append("<input type='hidden' name='lang' value='" + localeLang.toString() + "'>");
                formElem.append("<input type='hidden' name='formId' value='" + form.getKey().getName() + "'>");
            }
            formElem.removeAttr(MulAttrType.formId.getAttr());
        }
        
        // ----------------------------------------------------
        // リンクの書き換え
        // ----------------------------------------------------
        Elements links = jsoupService.getDoc().select("a");
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
        Element langSelectElm = jsoupService.getDoc().getElementById("lang-select");
        if(langSelectElm != null) {
            for(Lang lang: page.getLangList()) {
                if(lang == localeLang) {
                    langSelectElm.append("<option value='" + lang.toString() + "' selected>" + lang.getName() + "</option>");
                }else {
                    langSelectElm.append("<option value='" + lang.toString() + "'>" + lang.getName() + "</option>");
                }
            }
        }
        jsoupService.getDoc().body().append("<script>jQuery(function() {$('select#lang-select').change(function() {var selectedval = $(this).val();location.href = $('link[hreflang=' + selectedval + ']').eq(0).attr('href');});});</script>");
        
        // ----------------------------------------------------
        // テキストリソースの挿入
        // ----------------------------------------------------     
        List<Res> textResList = ResService.getAssetsAllResList(page, localeLang);
        renderedRes(jsoupService, textResList, domainUrl, isSigned);
        
        return jsoupService.getDoc();
    }
    
    

}
