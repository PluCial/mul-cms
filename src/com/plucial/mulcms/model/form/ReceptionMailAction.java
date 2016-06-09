package com.plucial.mulcms.model.form;

import java.io.Serializable;

import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.plucial.mulcms.model.template.MailTemplate;

@Model(schemaVersion = 1)
public class ReceptionMailAction extends MailAction implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // ----------------------------------------------------------------------
    // 関連
    // ----------------------------------------------------------------------
    /** テンプレートとの関連 */
    private ModelRef<MailTemplate> templateRef = new ModelRef<MailTemplate>(MailTemplate.class);
    
    public ModelRef<MailTemplate> getTemplateRef() {
        return templateRef;
    }

    
}
