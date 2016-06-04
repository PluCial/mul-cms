package com.plucial.mulcms.dao;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;

import com.plucial.mulcms.meta.AppMeta;
import com.plucial.mulcms.model.App;

public class AppDao extends DaoBase<App>{

    /** META */
    private static final AppMeta meta = AppMeta.get();
    
    /**
     * リストの取得
     * @return
     */
    public List<App> getList() {
        return  Datastore.query(meta).asList();
    }
    
}
