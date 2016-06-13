package com.plucial.mulcms.service.form;

import java.util.List;

import com.plucial.mulcms.dao.form.ReceptionMailActionDao;
import com.plucial.mulcms.model.form.Form;
import com.plucial.mulcms.model.form.ReceptionMailAction;


public class ReceptionMailActionService {
    
    /** DAO */
    private static final ReceptionMailActionDao dao = new ReceptionMailActionDao();
    
    /**
     * 追加
     * @param form
     * @param adminEmail
     * @param template
     */
//    public static void add(Transaction tx, Form form, String adminEmail, MailTemplate template) {
//        ReceptionMailAction model = new ReceptionMailAction();
//        
//        model.getFormRef().setModel(form);
//        model.setAdminEmail(new Email(adminEmail));
//        model.getTemplateRef().setModel(template);
//        
//        // 保存
//        Datastore.put(tx, model);
//    }
    
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
