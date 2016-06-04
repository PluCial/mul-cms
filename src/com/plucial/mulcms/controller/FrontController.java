package com.plucial.mulcms.controller;

import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slim3.controller.Navigation;

import com.plucial.gae.global.exception.NoContentsException;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.mulcms.enums.AppProperty;
import com.plucial.mulcms.enums.RenderingAction;
import com.plucial.mulcms.enums.RenderingType;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.Template;
import com.plucial.mulcms.model.res.Res;
import com.plucial.mulcms.service.JsoupService;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.res.ResService;
import com.plucial.mulcms.utils.HtmlUtils;

public class FrontController extends AppController {

    @Override
    protected Navigation run() throws Exception {
        
        boolean isSigned = true;
        String domainUrl = getDomainUrl();
        
        Map<String, String> appPropertyMap = super.getAppPropertyMap();
        String gcsBucketName = appPropertyMap.get(AppProperty.APP_GCS_BUCKET_NAME.toString());
        
        try {
            // ----------------------------------------------------
            // Page 情報
            // ----------------------------------------------------
            Page page = PageService.get(asString("path"));
            if(page.getLangList().indexOf(super.getLocaleLang()) < 0) {
                throw new NoContentsException();
            }
            
            
            Template template = page.getTemplateRef().getModel();
            JsoupService jsoupService = new JsoupService(template.getHtmlString());
            
            // ----------------------------------------------------
            // base URL を追加
            // ----------------------------------------------------
            Element head = jsoupService.getDoc().head();
            head.prepend("<base href='" + "https://storage.googleapis.com/" + gcsBucketName + "/'>");
            
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
                    linkHref = domainUrl + "/" + super.getLocaleLang().toString() + linkHref;
                }else {
                    linkHref = domainUrl + "/" + super.getLocaleLang().toString() + "/" + linkHref;
                }
                
                link.attr("href", linkHref);
            }
            
            // ----------------------------------------------------
            // テキストリソースを取得
            // ----------------------------------------------------
            List<Res> textResList = ResService.getAssetsAllResList(page, super.getLocaleLang());
            requestScope("textResList", textResList);
            
            // ----------------------------------------------------
            // リソースの挿入
            // ----------------------------------------------------            
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
                            getTextResEditJsHtml(domainUrl, res.getKey().getName(), res.getValueString(), renderingType == RenderingType.long_text));
                    }else {
                        jsoupService.rendering(res.getCssQuery(), RenderingAction.text, res.getValueString());
                    }
                    
                }else if(renderingType == RenderingType.attr) {
                    jsoupService.addAttr(res.getCssQuery(), res.getRenderingAttr(), res.getValueString());
                }
            }
            
            requestScope("pageHtml", jsoupService.getDoc().outerHtml());
            
            
        }catch(ObjectNotExistException e) {
            throw new NoContentsException();
        }
        
        return forward("front.jsp");
    }
    
    /**
     * Text Res Modal Open Tag
     * @param res
     * @return
     */
    private String getTextResEditModalOpenTagHtml(Res res) {
        String resValue = HtmlUtils.htmlEscape(res.getValueString());
        
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
    private String getTextResEditModalHtml(String resourcesKey,String resValue, boolean isLongText) {
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
    
    private String getTextResEditJsHtml(String domainUrl, String resourcesKey,String resValue, boolean isLongText) {
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
