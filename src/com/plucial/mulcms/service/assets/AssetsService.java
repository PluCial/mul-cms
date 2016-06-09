package com.plucial.mulcms.service.assets;

import java.util.List;
import java.util.UUID;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.gae.global.exception.TransException;
import com.plucial.global.Lang;
import com.plucial.mulcms.dao.assets.AssetsDao;
import com.plucial.mulcms.enums.RenderingAction;
import com.plucial.mulcms.enums.RenderingType;
import com.plucial.mulcms.meta.assets.AssetsMeta;
import com.plucial.mulcms.model.assets.Assets;
import com.plucial.mulcms.model.res.AppLangRes;
import com.plucial.mulcms.model.res.AssetsLangRes;
import com.plucial.mulcms.model.res.Res;
import com.plucial.mulcms.model.template.Template;
import com.plucial.mulcms.service.GoogleTransService;
import com.plucial.mulcms.service.JsoupService;
import com.plucial.mulcms.service.res.AppLangResService;
import com.plucial.mulcms.service.res.AssetsLangResService;
import com.plucial.mulcms.service.res.ResService;
import com.plucial.mulcms.utils.HtmlUtils;


public class AssetsService {
    
    /** DAO */
    private static final AssetsDao dao = new AssetsDao();

    
    /**
     * 取得
     * @param keyString
     * @return
     * @throws ObjectNotExistException
     */
    public static Assets get(String keyString) throws ObjectNotExistException {
        Assets model = dao.getOrNull(createKey(keyString));
        if(model == null) throw new ObjectNotExistException();
        return model;
    }
    
    /**
     * テンプレートの設定
     * @param model
     */
    public static void settingTemplate(Assets model) {
        Template template = (Template)model.getTemplateRef().getModel();
        model.setTemplate(template);
    }
    
    /**
     * 更新
     * @param model
     */
    public static void update(Assets model) {
        dao.put(model);
    }
    
    /**
     * 削除
     * @param keyString
     */
    protected static void delete(String keyString) {
        dao.delete(createKey(keyString));
    }
    
    /**
     * リソースの読み込み
     * @param assets
     */
    public static void extractionRes(Assets assets) {
        
        Template template = assets.getTemplateRef().getModel();
        JsoupService JsoupService = new JsoupService(template.getHtmlString());
        
        Transaction tx = Datastore.beginTransaction();
        try {
            ResService.addResByDoc(tx, assets, template.getLang(), JsoupService.getDoc());
            
            if(assets.getLangList().indexOf(template.getLang()) < 0) {
                assets.getLangList().add(template.getLang());
                Datastore.put(tx, assets);
            }

            tx.commit();
        }finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }
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
    public static void trans(String googleApiPublicServerKey, String googleApiApplicationName, Assets model, Lang transSrcLang, Lang transTargetLang) throws TransException, ObjectNotExistException {
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
    public static void copyNotTransRes(Assets model, Lang srcLang, Lang targetLang) {
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
    public static void delete(Assets model) {
        
        List<Res> resList = ResService.getAssetsResList(model);
        
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
    public static void delete(Assets model, Lang lang) {
        
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
    
    /**
     * リソースのレンダリング
     * @param jsoupService
     * @param textResList
     * @param domainUrl
     * @param editMode
     */
    public static void renderedRes(JsoupService jsoupService, List<Res> textResList, String domainUrl, boolean editMode) {
        for(Res res: textResList) {

            RenderingType renderingType = res.getRenderingType();

            if(renderingType == RenderingType.text || renderingType == RenderingType.long_text) {

                // ----------------------------------------------------
                // テキストリソース
                // ----------------------------------------------------
                if(editMode && res.isEditMode()) {

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
        sb.append("            url: '" + domainUrl + "/mulcms/ajax/updateResEntry',");
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
    
    // ----------------------------------------------------------------------
    // キーの作成
    // ----------------------------------------------------------------------
    /**
     * キーの作成
     * @param keyString
     * @return
     */
    protected static Key createKey(String keyString) {
        return Datastore.createKey(AssetsMeta.get(), keyString);
    }
    
    
    /**
     * キーの作成
     * @return
     */
    protected static Key createKey() {
        // キーを乱数にする
        UUID uniqueKey = UUID.randomUUID();
        return createKey(uniqueKey.toString());
    }

}
