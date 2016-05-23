package com.plucial.mulcms.dao;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.Sort;

import com.plucial.mulcms.meta.PageTemplateMeta;
import com.plucial.mulcms.model.PageTemplate;

public class PageTemplateDao extends DaoBase<PageTemplate>{
    
    /** META */
    private static final PageTemplateMeta meta = PageTemplateMeta.get();
    
    /**
     * リストの取得
     * @return
     */
    public List<PageTemplate> getList() {
        return  Datastore.query(meta)
                    .sort(new Sort(meta.createDate)).asList();
    }

}
