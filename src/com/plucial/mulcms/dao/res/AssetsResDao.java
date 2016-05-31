package com.plucial.mulcms.dao.res;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;

import com.plucial.mulcms.meta.res.AssetsResMeta;
import com.plucial.mulcms.model.Assets;
import com.plucial.mulcms.model.res.AssetsRes;

public class AssetsResDao extends DaoBase<AssetsRes>{
    
    /** META */
    private static final  AssetsResMeta meta =  AssetsResMeta.get();
    
    /**
     * テキストリソースのリストを取得
     * @param resId
     * @param assets
     * @return
     */
    public AssetsRes get(String resId, Assets assets) {
        return Datastore.query(meta).filter(
            meta.resId.equal(resId),
            meta.assetsRef.equal(assets.getKey())
            ).asSingle();
    }
    
    /**
     * リストの取得
     * @param assets
     * @return
     */
    public List<AssetsRes> getList(Assets assets) {
        return Datastore.query(meta).filter(
            meta.assetsRef.equal(assets.getKey())
            ).asList();
    }

}
