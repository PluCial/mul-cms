package com.plucial.mulcms.dao;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.Sort;

import com.plucial.mulcms.meta.PageMeta;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.Template;

public class PageDao extends DaoBase<Page>{
    
    /** META */
    private static final PageMeta meta = PageMeta.get();
    
    /**
     * リストの取得
     * @return
     */
    public List<Page> getList() {
        return  Datastore.query(meta)
                    .sort(new Sort(meta.createDate)).asList();
    }
    
    /**
     * リストの取得
     * @return
     */
    public List<Page> getList(Template template) {
        return  Datastore.query(meta).filter(
            meta.templateRef.equal(template.getKey())
            ).asList();
    }

}
