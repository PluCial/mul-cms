package com.plucial.mulcms.service.assets;

import java.util.List;

import org.jsoup.nodes.Document;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.datastore.Transaction;
import com.plucial.global.Lang;
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
     * @param lang
     * @param template
     * @param cssQuery
     * @return
     */
    public static Widget put(Page page, Lang lang, WidgetTemplate template, String cssQuery) {
        Widget model = new Widget();
        String html = template.getHtmlString();
        settingNewModel(model, template, html);
        
        model.setKey(createKey());
        model.getParentRef().setModel(page);
        model.setSortOrder(page.getChildSortOrderMax() + 1);
        model.setCssQuery(cssQuery);
        
        Transaction tx = Datastore.beginTransaction();
        try {
            Document doc = settingTextRes(tx, page, lang, template);
            
            model.setHtml(new Text(doc.outerHtml()));
            
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
     * 削除
     * @param model
     */
    public static void delete(Widget model) {
        dao.delete(model.getKey());
    }
    
    /**
     * リストの取得
     * @param page
     * @return
     */
    public static List<Widget> getList(Page page) {
        return dao.getList(page);
    }

}
