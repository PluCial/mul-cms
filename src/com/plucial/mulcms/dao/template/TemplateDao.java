package com.plucial.mulcms.dao.template;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.Sort;

import com.plucial.mulcms.meta.template.TemplateMeta;
import com.plucial.mulcms.model.template.Template;

public class TemplateDao extends DaoBase<Template>{

    /** META */
    private static final TemplateMeta meta = TemplateMeta.get();
    
    /**
     * リストの取得
     * @return
     */
    public List<Template> getList() {
        return  Datastore.query(meta)
                    .sort(new Sort(meta.createDate)).asList();
    }
}
