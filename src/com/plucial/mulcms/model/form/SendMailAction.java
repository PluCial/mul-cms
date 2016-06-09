package com.plucial.mulcms.model.form;

import java.io.Serializable;

import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Email;
import com.plucial.mulcms.model.assets.Mail;

@Model(schemaVersion = 1)
public class SendMailAction extends MailAction implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Email sendEmail;
    
    // ----------------------------------------------------------------------
    // 関連
    // ----------------------------------------------------------------------
    /** テンプレートとの関連 */
    private ModelRef<Mail> mailRef = new ModelRef<Mail>(Mail.class);

    public Email getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(Email sendEmail) {
        this.sendEmail = sendEmail;
    }

    public ModelRef<Mail> getMailRef() {
        return mailRef;
    }

}
