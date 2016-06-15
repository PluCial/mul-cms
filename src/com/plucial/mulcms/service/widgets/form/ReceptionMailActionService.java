package com.plucial.mulcms.service.widgets.form;

import com.google.appengine.api.datastore.Email;
import com.plucial.mulcms.dao.widgets.form.ReceptionMailActionDao;
import com.plucial.mulcms.model.widgets.form.Form;
import com.plucial.mulcms.model.widgets.form.ReceptionMailAction;


public class ReceptionMailActionService extends MailActionService {
    
    /** DAO */
    private static final ReceptionMailActionDao dao = new ReceptionMailActionDao();
    
    /**
     * 追加
     * @param form
     * @param adminEmail
     * @param template
     */
    public static void add(Form form, String sendEmail) {
        ReceptionMailAction model = new ReceptionMailAction();
        model.setKey(createKey());
        model.getFormRef().setModel(form);
        model.setSendEmail(new Email(sendEmail));
        
        // 保存
        dao.put(model);
    }

}
