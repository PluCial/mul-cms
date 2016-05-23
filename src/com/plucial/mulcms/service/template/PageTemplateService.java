package com.plucial.mulcms.service.template;

import java.util.List;

import org.jsoup.nodes.Document;

import com.plucial.mulcms.dao.PageTemplateDao;
import com.plucial.mulcms.model.PageTemplate;


public class PageTemplateService extends TemplateService {
    
    /** DAO */
    private static final PageTemplateDao dao = new PageTemplateDao();
    
    /**
     * 追加
     * @param assetsType
     * @param name
     * @param content
     * @return
     */
    public static PageTemplate put(String name, Document doc) {
        PageTemplate model = new PageTemplate();
        settingNewModel(model, name, doc.outerHtml());
        
        dao.put(model);
        
        return model;
    }
    
    /**
     * リストの取得
     * @return
     */
    public static List<PageTemplate> getList() {
        
        return dao.getList();
    }

}
