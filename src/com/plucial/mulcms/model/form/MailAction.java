package com.plucial.mulcms.model.form;

import java.io.Serializable;

import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Email;

@Model(schemaVersion = 1)
public class MailAction extends FormAction implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Email adminEmail;

    public Email getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(Email adminEmail) {
        this.adminEmail = adminEmail;
    }

    
}
