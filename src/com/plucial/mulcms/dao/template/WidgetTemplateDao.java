package com.plucial.mulcms.dao.template;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.Sort;

import com.plucial.mulcms.meta.template.WidgetTemplateMeta;
import com.plucial.mulcms.model.template.WidgetTemplate;

public class WidgetTemplateDao extends DaoBase<WidgetTemplate>{
    
    /** META */
    private static final WidgetTemplateMeta meta = WidgetTemplateMeta.get();
    
    /**
     * リストの取得
     * @return
     */
    public List<WidgetTemplate> getList() {
        return  Datastore.query(meta)
                    .sort(new Sort(meta.createDate)).asList();
    }

}
