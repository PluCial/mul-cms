package com.plucial.mulcms.dao.res;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;

import com.plucial.mulcms.meta.res.AppResMeta;
import com.plucial.mulcms.model.res.AppRes;

public class AppResDao extends DaoBase<AppRes>{
    
    /** META */
    private static final  AppResMeta meta =  AppResMeta.get();

    /**
     * テキストリソースのリストを取得
     * @param langUnit
     * @return
     */
    public AppRes get(String resId) {
        return Datastore.query(meta).filter(
            meta.resId.equal(resId)
            ).asSingle();
    }
    
    /**
     * リストの取得
     * @return
     */
    public List<AppRes> getList() {
        return Datastore.query(meta).asList();
    }
}
