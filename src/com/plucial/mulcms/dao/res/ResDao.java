package com.plucial.mulcms.dao.res;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.Sort;

import com.plucial.global.Lang;
import com.plucial.mulcms.meta.res.ResMeta;
import com.plucial.mulcms.model.assets.Assets;
import com.plucial.mulcms.model.res.Res;

public class ResDao extends DaoBase<Res>{
    
    /** META */
    private static final  ResMeta meta =  ResMeta.get();
    
    /**
     * リストの取得
     * @param assets
     * @param lang
     * @return
     */
    public List<Res> getList(Assets assets) {
        return Datastore.query(meta).filter(
            meta.assetsRef.equal(assets.getKey())
            ).sort(new Sort(meta.createDate)).asList();
    }
    
    /**
     * リストの取得
     * @param assets
     * @param lang
     * @return
     */
    public List<Res> getList(Assets assets, Lang lang) {
        return Datastore.query(meta).filter(
            meta.assetsRef.equal(assets.getKey()),
            meta.lang.equal(lang)
            ).sort(new Sort(meta.createDate)).asList();
    }

}
