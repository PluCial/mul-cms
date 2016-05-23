package com.plucial.mulcms.service.assets;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Transaction;
import com.plucial.mulcms.dao.WidgetDao;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.Widget;
import com.plucial.mulcms.model.WidgetTemplate;


public class WidgetService extends AssetsService {
    
    /** DAO */
    private static final WidgetDao dao = new WidgetDao();
    
    /**
     * 追加
     * @param page
     * @param template
     * @return
     */
    public static Widget put(Page page, WidgetTemplate template) {
        Widget model = new Widget();
        settingNewModel(model, template);
        
        model.setKey(createKey());
        model.getParentRef().setModel(page);
        model.setSortOrder(page.getChildSortOrderMax() + 1);
        
        Transaction tx = Datastore.beginTransaction();
        try {
            
            page.setChildSortOrderMax(page.getChildSortOrderMax() + 1);
            Datastore.put(tx, page, model);
            
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
     * @param page
     * @return
     */
    public static List<Widget> getList(Page page) {
        return dao.getList();
    }

}
