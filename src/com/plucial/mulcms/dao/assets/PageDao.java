package com.plucial.mulcms.dao.assets;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.Sort;

import com.plucial.mulcms.meta.assets.PageMeta;
import com.plucial.mulcms.model.assets.Page;

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

}
