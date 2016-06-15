package com.plucial.mulcms.service.widgets.form;

import java.util.List;

import com.plucial.mulcms.dao.widgets.form.MailActionDao;
import com.plucial.mulcms.model.widgets.form.Form;
import com.plucial.mulcms.model.widgets.form.MailAction;



public class MailActionService extends FormActionService {
    
    /** DAO */
    private static final MailActionDao dao = new MailActionDao();
    
    /**
     * リストの取得
     * @param assets
     * @return
     */
    public static List<MailAction> getList(Form form) {
        return dao.getList(form);
    }

}
