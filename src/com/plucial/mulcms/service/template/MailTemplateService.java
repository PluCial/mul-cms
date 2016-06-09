package com.plucial.mulcms.service.template;

import java.util.List;

import org.jsoup.nodes.Document;

import com.plucial.global.Lang;
import com.plucial.mulcms.dao.template.MailTemplateDao;
import com.plucial.mulcms.model.template.MailTemplate;


public class MailTemplateService extends TemplateService {
    
    /** DAO */
    private static final MailTemplateDao dao = new MailTemplateDao();
    
    /**
     * 追加
     * @param assetsType
     * @param name
     * @param content
     * @return
     */
    public static MailTemplate put(String name, Document doc, Lang lang) {
        MailTemplate model = new MailTemplate();
        settingNewModel(model, lang, name, doc.outerHtml());
        
        dao.put(model);
        
        return model;
    }
    
    /**
     * リストの取得
     * @return
     */
    public static List<MailTemplate> getList() {
        
        return dao.getList();
    }

}
