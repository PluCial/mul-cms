package com.plucial.mulcms.dao.res;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.Sort;

import com.plucial.global.Lang;
import com.plucial.mulcms.meta.res.AttrResMeta;
import com.plucial.mulcms.model.assets.Assets;
import com.plucial.mulcms.model.res.AttrRes;

public class AttrResDao extends DaoBase<AttrRes>{
    
    /** META */
    private static final  AttrResMeta meta =  AttrResMeta.get();
    
    /**
     * リソースの取得
     * @param assets
     * @param lang
     * @param cssQuery
     * @param attr
     * @return
     */
    public AttrRes get(Assets assets, Lang lang, String cssQuery, String attr) {
        return Datastore.query(meta).filter(
            meta.assetsRef.equal(assets.getKey()),
            meta.cssQuery.equal(cssQuery),
            meta.attr.equal(attr),
            meta.lang.equal(lang)
            ).asSingle();
    }
    
    /**
     * リストの取得
     * @param lang
     * @return
     */
    public List<AttrRes> getList(Lang lang) {
        return Datastore.query(meta).filter(
            meta.lang.equal(lang)
            ).sort(new Sort(meta.createDate)).asList();
    }
    
    /**
     * リストの取得
     * @param assets
     * @param lang
     * @return
     */
    public List<AttrRes> getList(Assets assets, Lang lang) {
        return Datastore.query(meta).filter(
            meta.assetsRef.equal(assets.getKey()),
            meta.lang.equal(lang)
            ).sort(new Sort(meta.createDate)).asList();
    }

}
