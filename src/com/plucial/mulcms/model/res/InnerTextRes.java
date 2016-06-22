package com.plucial.mulcms.model.res;

import java.io.Serializable;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.util.StringUtil;

import com.google.appengine.api.datastore.Text;
import com.plucial.global.Lang;
import com.plucial.mulcms.model.RenderingItem;
import com.plucial.mulcms.utils.HtmlUtils;

@Model(schemaVersion = 1)
public class InnerTextRes extends InnerRes implements Serializable, RenderingItem {

    private static final long serialVersionUID = 1L;
    
    @Attribute(unindexed = true)
    private boolean isLongText;
    
    /**
     * 文字列を適切に変換してコンテンツにセットする
     * @param str
     */
    public void setStringToValue(String content) {
        if(StringUtil.isEmpty(content.trim())) throw new NullPointerException();
        
        // 前後の空白を削除
        content = content.trim();

        // 改行をすべて統一
        super.setValue(new Text(com.plucial.gae.global.utils.StringUtil.unityIndention(content)));
    }

    public boolean isLongText() {
        return isLongText;
    }

    public void setLongText(boolean isLongText) {
        this.isLongText = isLongText;
    }

    public void reenderingDoc(Document doc, Lang localeLang, String domainUrl, boolean isSigned) {
        Elements elements = doc.select(super.getCssQuery());
        if(elements == null) return;
        
        for(Element elem: elements) {
            if(isSigned && super.isEditMode()) {
                elem.html(getEditModalTag());
                doc.body().append(getEditModalHtml());
                doc.body().append(getEditModalJs(domainUrl));
                
            }else {
                elem.html(HtmlUtils.getJspDisplayString(super.getValueString()));
            }
        }
    }
    
    /**
     * Text Res Modal Open Tag
     * @param res
     * @return
     */
    private String getEditModalTag() {
        String resValue = HtmlUtils.getJspDisplayString(super.getValueString());
        
        StringBuilder sb = new StringBuilder();
        sb.append("<span style='cursor: pointer;'");
        sb.append(" class='modal-open-tag'");
        sb.append(" data-toggle='modal'");
        sb.append(" data-backdrop='static'");
        sb.append(" data-target='#" + super.getKey().getName() + "-modal'");
        sb.append(" data-resources-key='" + super.getKey().getName() + "'>");
        sb.append("    <span id='" + super.getKey().getName() + "'>");
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
    private String getEditModalHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class='modal fade text-res-modal' id='" + super.getKey().getName() +"-modal' tabindex='-1' role='dialog' aria-labelledby='exampleModalLabel'>");
        sb.append(" <div class='modal-dialog' role='document'>");
        sb.append("     <div class='modal-content'>");
        sb.append("         <div class='modal-header'>");
        sb.append("             <button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>");
        sb.append("             <h4 class='modal-title' id='exampleModalLabel'>文言の修正</h4>");
        sb.append("         </div>");
        sb.append("         <form id='" + super.getKey().getName() + "-form'>");
        sb.append("             <div class='modal-body'>");
        sb.append("                 <p><i class='fa fa-info-circle'></i> リソースの言語に合わせて修正してください。</p>");
        sb.append("                     <div class='form-group'>");
        sb.append("                         <label class='control-label validate-error' for='inputError' style='color:#dd4b39;display:none;'><i class='fa fa-times-circle-o'></i> <span class='validate-error-msg'></span></label>");
        if(isLongText) {
            sb.append("                         <textarea name='content' class='form-control' rows='7'>" + super.getValueString() + "</textarea>");
        }else {
            sb.append("                         <input type='text' name='content' class='form-control' value='" + super.getValueString() +"' />");
        }
        sb.append("                     </div>");
        sb.append("                     <input type='hidden' name='parentKeyString' value='" + super.getAssetsRef().getKey().getName() + "' />");
        sb.append("                     <input type='hidden' name='keyString' value='" + super.getKey().getName() + "' />");
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
    
    /**
     * 編集用JS
     * @param domainUrl
     * @return
     */
    private String getEditModalJs(String domainUrl) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("<script>");
        sb.append("jQuery(function() {");
        sb.append("   $('#" + super.getKey().getName() + "-form').on('submit', function(event){");
        sb.append("     var modal = $('#" + super.getKey().getName() + "-modal');");
        sb.append("     var submitform = $(this);");
        sb.append("     var submitData = submitform.serialize();");
        sb.append("     var errorLabel = submitform.find('.validate-error');");
                
        sb.append("     var changeTarget = $('#" + super.getKey().getName() + "');");
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
}
