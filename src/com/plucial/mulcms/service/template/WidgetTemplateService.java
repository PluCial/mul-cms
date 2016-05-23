package com.plucial.mulcms.service.template;

import java.util.List;

import org.jsoup.nodes.Document;

import com.plucial.mulcms.dao.WidgetTemplateDao;
import com.plucial.mulcms.model.WidgetTemplate;


public class WidgetTemplateService extends TemplateService {
    
    /** DAO */
    private static final WidgetTemplateDao dao = new WidgetTemplateDao();
    
    /**
     * 追加
     * @param name
     * @param content
     * @param cssQuery
     * @return
     */
    public static WidgetTemplate put(String name, Document doc, String cssQuery) {
        WidgetTemplate model = new WidgetTemplate();
        settingNewModel(model, name, doc.outerHtml());
        
        model.setCssQuery(cssQuery);
        
        dao.put(model);
        
        return model;
    }
    
    /**
     * リストの取得
     * @return
     */
    public static List<WidgetTemplate> getList() {
        return dao.getList();
    }

}
