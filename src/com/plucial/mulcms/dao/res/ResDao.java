package com.plucial.mulcms.dao.res;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;

import com.plucial.mulcms.meta.res.ResMeta;
import com.plucial.mulcms.model.res.Res;

public class ResDao extends DaoBase<Res>{
    
    /** META */
    private static final  ResMeta meta =  ResMeta.get();
    
    /**
     * リストの取得
     * @return
     */
    public List<Res> getList() {
        return Datastore.query(meta).asList();
    }

}
