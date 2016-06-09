package com.plucial.mulcms.service.template;

import java.util.List;

import com.plucial.global.Lang;
import com.plucial.mulcms.dao.template.WidgetTemplateDao;
import com.plucial.mulcms.model.template.WidgetTemplate;


public class WidgetTemplateService extends TemplateService {
    
    /** DAO */
    private static final WidgetTemplateDao dao = new WidgetTemplateDao();
    
    /**
     * 追加
     * @param name
     * @param html
     * @param lang
     * @return
     */
    public static WidgetTemplate put(String name, String html, Lang lang) {
        WidgetTemplate model = new WidgetTemplate();
        settingNewModel(model, lang, name, html);
        
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
