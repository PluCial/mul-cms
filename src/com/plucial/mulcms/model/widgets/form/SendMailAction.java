package com.plucial.mulcms.model.widgets.form;

import java.io.Serializable;

import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.plucial.mulcms.model.assets.Mail;

@Model(schemaVersion = 1)
public class SendMailAction extends MailAction implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // ----------------------------------------------------------------------
    // 関連
    // ----------------------------------------------------------------------
    /** テンプレートとの関連 */
    private ModelRef<Mail> mailRef = new ModelRef<Mail>(Mail.class);

    public ModelRef<Mail> getMailRef() {
        return mailRef;
    }
}
