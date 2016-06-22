package com.plucial.mulcms.service.widgets.form;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Transaction;
import com.plucial.mulcms.dao.widgets.form.FormDao;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.model.widgets.form.Form;
import com.plucial.mulcms.service.RenderingService;


public class FormService extends RenderingService {

    /** DAO */
    private static final FormDao dao = new FormDao();
    
    /**
     * 追加
     * @param name
     * @param page
     * @param transitionPage
     * @return
     */
    public static Form add(String cssQuery, String name, Page page, Page transitionPage) {
        
        Transaction tx = Datastore.beginTransaction();
        Form model = null;
        try {
            model = new Form();
            model.setKey(createKey(page));
            model.setCssQuery(cssQuery);
            model.setName(name);
            model.getAssetsRef().setModel(page);
            model.getTransitionPageRef().setModel(transitionPage);
            
            // 保存
            Datastore.put(tx, model);
            
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
    public static List<Form> getList(Page page) {
        return dao.getList(page);
    }
}
