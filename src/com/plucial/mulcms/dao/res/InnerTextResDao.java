package com.plucial.mulcms.dao.res;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.Sort;

import com.plucial.global.Lang;
import com.plucial.mulcms.meta.res.InnerTextResMeta;
import com.plucial.mulcms.model.assets.Assets;
import com.plucial.mulcms.model.res.InnerTextRes;

public class InnerTextResDao extends DaoBase<InnerTextRes>{
    
    /** META */
    private static final  InnerTextResMeta meta =  InnerTextResMeta.get();
    
    /**
     * 取得
     * @param resId
     * @param assets
     * @param lang
     * @return
     */
    public InnerTextRes get(Assets assets, String cssQuery, Lang lang) {
        List<InnerTextRes> resList = Datastore.query(meta).filter(
            meta.assetsRef.equal(assets.getKey()),
            meta.cssQuery.equal(cssQuery),
            meta.lang.equal(lang)
            ).asList();
        
        return resList.size() > 0 ? resList.get(0) :  null;
    }
    
    /**
     * リストの取得
     * @param assets
     * @param lang
     * @return
     */
    public List<InnerTextRes> getList(Assets assets, Lang lang) {
        return Datastore.query(meta).filter(
            meta.assetsRef.equal(assets.getKey()),
            meta.lang.equal(lang)
            ).sort(new Sort(meta.createDate)).asList();
    }

}
