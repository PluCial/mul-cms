package com.plucial.mulcms.service.assets;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Transaction;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.gae.global.exception.TransException;
import com.plucial.global.Lang;
import com.plucial.mulcms.dao.PageDao;
import com.plucial.mulcms.enums.MulAttrType;
import com.plucial.mulcms.enums.RenderingAction;
import com.plucial.mulcms.enums.RenderingType;
import com.plucial.mulcms.exception.TooManyException;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.PageTemplate;
import com.plucial.mulcms.model.Template;
import com.plucial.mulcms.model.form.Form;
import com.plucial.mulcms.model.res.AppLangRes;
import com.plucial.mulcms.model.res.AssetsLangRes;
import com.plucial.mulcms.model.res.Res;
import com.plucial.mulcms.service.GoogleTransService;
import com.plucial.mulcms.service.JsoupService;
import com.plucial.mulcms.service.form.FormService;
import com.plucial.mulcms.service.res.AppLangResService;
import com.plucial.mulcms.service.res.AssetsLangResService;
import com.plucial.mulcms.service.res.ResService;
import com.plucial.mulcms.utils.HtmlUtils;


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
     * @param googleApiPublicServerKey
     * @param googleApiApplicationName
     * @param model
     * @param transSrcLang
     * @param transTargetLang
     * @throws TransException
     * @throws ObjectNotExistException
     */
    public static void trans(String googleApiPublicServerKey, String googleApiApplicationName, Page model, Lang transSrcLang, Lang transTargetLang) throws TransException, ObjectNotExistException {
        // ---------------------------------------------------
        // 翻訳元のコンテンツリスト
        // ---------------------------------------------------
        List<Res> transSrcList = ResService.getAssetsResList(model, transSrcLang, true);
        if(transSrcList.size() <= 0) return;

        Transaction tx = Datastore.beginTransaction();
        try {
            
            GoogleTransService googleTransService = 
                    new GoogleTransService(googleApiPublicServerKey, googleApiApplicationName);
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
     * 翻訳しないコンテンツのコピー
     * @param model
     * @param srcLang
     * @param targetLang
     */
    public static void copyNotTransRes(Page model, Lang srcLang, Lang targetLang) {
        // ---------------------------------------------------
        // 翻訳しないコンテンツをコピー
        // ---------------------------------------------------
        List<Res> srcResList = ResService.getAssetsResList(model, srcLang, false);
        
        Transaction tx = Datastore.beginTransaction();
        try {
            for(Res srcRes: srcResList) {
                if(srcRes instanceof AppLangRes) {
                    try {
                        AppLangResService.get(srcRes.getResId(), srcRes.getRenderingType(), srcRes.getRenderingAttr(), targetLang);
                        
                    }catch(ObjectNotExistException e) {
                        AppLangResService.add(
                            tx, 
                            srcRes.getResId(), 
                            srcRes.getCssQuery(), 
                            srcRes.getRenderingType(), 
                            srcRes.getValueString(), 
                            srcRes.getRenderingAttr(), 
                            srcRes.isEditMode(), 
                            targetLang);
                    }
                    
                }else if(srcRes instanceof AssetsLangRes) {
                    try {
                        AssetsLangResService.get(srcRes.getResId(), model, srcRes.getRenderingType(), srcRes.getRenderingAttr(),targetLang);
                    }catch(ObjectNotExistException e) {
                        AssetsLangResService.add(
                            tx, 
                            srcRes.getResId(), 
                            srcRes.getCssQuery(), 
                            srcRes.getRenderingType(), 
                            srcRes.getValueString(), 
                            srcRes.getRenderingAttr(), 
                            model, 
                            srcRes.isEditMode(), 
                            targetLang);
                    }
                }
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
        for(Res res: textResList) {

            RenderingType renderingType = res.getRenderingType();

            if(renderingType == RenderingType.text || renderingType == RenderingType.long_text) {

                // ----------------------------------------------------
                // テキストリソース
                // ----------------------------------------------------
                if(isSigned && res.isEditMode()) {

                    // Modal Open Tag
                    jsoupService.rendering(
                        res.getCssQuery(), 
                        RenderingAction.html, 
                        getTextResEditModalOpenTagHtml(res));

                    // Modal Html
                    jsoupService.rendering(
                        "body", 
                        RenderingAction.append, 
                        getTextResEditModalHtml(res.getKey().getName(), res.getValueString(), renderingType == RenderingType.long_text));

                    // JS
                    jsoupService.rendering(
                        "body", 
                        RenderingAction.append, 
                        getTextResEditJsHtml(domainUrl, res.getKey().getName(), renderingType == RenderingType.long_text));
                }else {
                    jsoupService.rendering(res.getCssQuery(), RenderingAction.html, HtmlUtils.getJspDisplayString(res.getValueString()));
                }
                
            }else if(renderingType == RenderingType.attr) {
                jsoupService.addAttr(res.getCssQuery(), res.getRenderingAttr(), res.getValueString());
            }
        }
        
        return jsoupService.getDoc();
    }
    
    
    /**
     * Text Res Modal Open Tag
     * @param res
     * @return
     */
    private static String getTextResEditModalOpenTagHtml(Res res) {
        String resValue = HtmlUtils.getJspDisplayString(res.getValueString());
        
        StringBuilder sb = new StringBuilder();
        sb.append("<span style='cursor: pointer;'");
        sb.append(" class='modal-open-tag'");
        sb.append(" data-toggle='modal'");
        sb.append(" data-backdrop='static'");
        sb.append(" data-target='#" + res.getKey().getName() + "-modal'");
        sb.append(" data-resources-key='" + res.getKey().getName() + "'>");
        sb.append("    <span id='" + res.getKey().getName() + "'>");
        sb.append(HtmlUtils.changeIndentionToHtml(resValue));
        sb.append("    </span>");
        sb.append("</span>");
        
        return sb.toString();
    }
    
    /**
     * 編集Model
     * @param resourcesKey
     * @param resValue
     * @param isLongText
     * @return
     */
    private static String getTextResEditModalHtml(String resourcesKey,String resValue, boolean isLongText) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class='modal fade text-res-modal' id='" + resourcesKey +"-modal' tabindex='-1' role='dialog' aria-labelledby='exampleModalLabel'>");
        sb.append(" <div class='modal-dialog' role='document'>");
        sb.append("     <div class='modal-content'>");
        sb.append("         <div class='modal-header'>");
        sb.append("             <button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>");
        sb.append("             <h4 class='modal-title' id='exampleModalLabel'>文言の修正</h4>");
        sb.append("         </div>");
        sb.append("         <form id='" + resourcesKey + "-form'>");
        sb.append("             <div class='modal-body'>");
        sb.append("                 <p><i class='fa fa-info-circle'></i> リソースの言語に合わせて修正してください。</p>");
        sb.append("                     <div class='form-group'>");
        sb.append("                         <label class='control-label validate-error' for='inputError' style='color:#dd4b39'><i class='fa fa-times-circle-o'></i> <span class='validate-error-msg'></span></label>");
        if(isLongText) {
            sb.append("                         <textarea name='content' class='form-control' rows='7'>" + resValue + "</textarea>");
        }else {
            sb.append("                         <input type='text' name='content' class='form-control' value='" + resValue +"' />");
        }
        sb.append("                     </div>");
        sb.append("                     <input type='hidden' name='keyString' value='" + resourcesKey + "' />");
        sb.append("             </div>");
        sb.append("             <div class='modal-footer'>");
        sb.append("                 <button type='button' class='btn btn-default' data-dismiss='modal'>キャンセル</button>");
        sb.append("                 <button type='submit' class='btn btn-primary'>保存</button>");
        sb.append("             </div>");
        sb.append("         </form>");
        sb.append("     </div>");
        sb.append(" </div>");
        sb.append("</div>");
        
        return sb.toString();
    }
    
    private static String getTextResEditJsHtml(String domainUrl, String resourcesKey, boolean isLongText) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("<script>");
        sb.append("jQuery(function() {");
        sb.append("   $('#" + resourcesKey + "-form').on('submit', function(event){");
        sb.append("     var modal = $('#" + resourcesKey + "-modal');");
        sb.append("     var submitform = $(this);");
        sb.append("     var submitData = submitform.serialize();");
        sb.append("     var errorLabel = submitform.find('.validate-error');");
                
        sb.append("     var changeTarget = $('#" + resourcesKey + "');");
        sb.append("     var newContent = submitform.find('[name=content]').val();");
                
        sb.append("     $.ajax({");
        sb.append("            url: '" + domainUrl + "/mulcms/page/ajax/updateResEntry',");
        sb.append("            type: 'POST',");
        sb.append("            data: submitData,");
        sb.append("            dataType: 'json',");
        sb.append("            success: function(data) {");
        sb.append("             if(data.status == 'success') {");
        sb.append("                 modal.modal('hide');");
        sb.append("                 errorLabel.css({'display':'none'});");                            
        sb.append("                 changeTarget.css({'display':'none'});");
        sb.append("                 changeTarget.html(newContent.replace(/\\r?\\n/g, '<br>'));");
        sb.append("                 changeTarget.animate({ opacity: 'show'},{ duration: 1500, easing: 'swing'});");                            
        sb.append("             }else {");
        sb.append("                 var errorMsgSpan = errorLabel.find('.validate-error-msg');");                
        sb.append("                 errorMsgSpan.html(data.errorMessage);");
        sb.append("                 errorLabel.css({'display':'block'});");
        sb.append("             }");
        sb.append("            },");
        sb.append("         complete: function(data) {");
        sb.append("             console.log(data);");
        sb.append("         }");
        sb.append("        });");
                
        sb.append("     event.preventDefault();");
        sb.append(" });");
        sb.append("});");
        sb.append("</script>");
        
        return sb.toString();
    }

}
