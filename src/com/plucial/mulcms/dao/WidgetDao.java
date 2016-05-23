package com.plucial.mulcms.dao;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.Sort;

import com.plucial.mulcms.meta.WidgetMeta;
import com.plucial.mulcms.model.Widget;

public class WidgetDao extends DaoBase<Widget>{
    
    /** META */
    private static final WidgetMeta meta = WidgetMeta.get();
    
    /**
     * リストの取得
     * @return
     */
    public List<Widget> getList() {
        return  Datastore.query(meta)
                    .sort(new Sort(meta.sortOrder)).asList();
    }

}
