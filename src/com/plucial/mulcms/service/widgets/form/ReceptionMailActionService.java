package com.plucial.mulcms.service.widgets.form;

import java.util.List;

import com.google.appengine.api.datastore.Email;
import com.plucial.mulcms.dao.widgets.form.ReceptionMailActionDao;
import com.plucial.mulcms.model.widgets.form.Form;
import com.plucial.mulcms.model.widgets.form.ReceptionMailAction;


public class ReceptionMailActionService {
    
    /** DAO */
    private static final ReceptionMailActionDao dao = new ReceptionMailActionDao();
    
    /**
     * 追加
     * @param form
     * @param adminEmail
     * @param template
     */
    public static void add(Form form, String adminEmail, String sendEmail) {
        ReceptionMailAction model = new ReceptionMailAction();
        
        model.getFormRef().setModel(form);
        model.setAdminEmail(new Email(adminEmail));
        model.setSendEmail(new Email(sendEmail));
        
        // 保存
        dao.put(model);
    }
    
    /**
     * リストの取得
     * @return
     */
    public static List<ReceptionMailAction> getList(Form form) {
        return dao.getList(form);
    }
    
    /**
     * 更新
     * @param model
     */
    public static void update(ReceptionMailAction model) {
        dao.put(model);
    }

}
