package com.plucial.mulcms.service.form;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.users.User;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.mulcms.dao.form.FormDao;
import com.plucial.mulcms.meta.form.FormMeta;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.model.form.Form;
import com.plucial.mulcms.model.template.MailTemplate;


public class FormService {

    /** DAO */
    private static final FormDao dao = new FormDao();
    
    /**
     * 取得
     * @param keyString
     * @return
     * @throws ObjectNotExistException 
     */
    public static Form get(String keyString) throws ObjectNotExistException {
        Form model = dao.getOrNull(createKey(keyString));
        if(model == null) throw new ObjectNotExistException();
        return model;
    }
    
    /**
     * 追加
     * @param formId
     * @param name
     * @param page
     * @param transitionPage
     * @return
     */
    public static Form add(String formId, String name, Page page, Page transitionPage, MailTemplate mailTemplate, User user) {
        
        Transaction tx = Datastore.beginTransaction();
        Form model = null;
        try {
            model = new Form();
            model.setKey(createKey(formId));
            model.setName(name);
            model.getPageRef().setModel(page);
            model.getTransitionPageRef().setModel(transitionPage);
            
            // 保存
            Datastore.put(tx, model);
            
            ReceptionMailActionService.add(tx, model, user.getEmail(), mailTemplate);
            
            tx.commit();
        }finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }
        
        return model;
    }
    
    /**
     * リストの取得
     * @return
     */
    public static List<Form> getList() {
        return dao.getList();
    }
    
    /**
     * リストの取得
     * @return
     */
    public static List<Form> getList(Page page) {
        return dao.getList(page);
    }
    
    /**
     * 更新
     * @param model
     */
    public static void update(Form model) {
        dao.put(model);
    }
    
    /**
     * 削除
     * @param keyString
     */
    public static void delete(String keyString) {
        dao.delete(createKey(keyString));
    }
    
    // ----------------------------------------------------------------------
    // キーの作成
    // ----------------------------------------------------------------------
    /**
     * キーの作成
     * @param keyString
     * @return
     */
    protected static Key createKey(String keyString) {
        return Datastore.createKey(FormMeta.get(), keyString);
    }
}
