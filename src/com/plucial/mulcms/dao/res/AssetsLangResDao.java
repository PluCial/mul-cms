package com.plucial.mulcms.dao.res;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;

import com.plucial.global.Lang;
import com.plucial.mulcms.meta.res.AssetsLangResMeta;
import com.plucial.mulcms.model.Assets;
import com.plucial.mulcms.model.res.AssetsLangRes;

public class AssetsLangResDao extends DaoBase<AssetsLangRes>{
    
    /** META */
    private static final  AssetsLangResMeta meta =  AssetsLangResMeta.get();
    
    /**
     * 取得
     * @param resId
     * @param assets
     * @param lang
     * @return
     */
    public AssetsLangRes get(String resId, Assets assets, Lang lang) {
        return Datastore.query(meta).filter(
            meta.resId.equal(resId),
            meta.assetsRef.equal(assets.getKey()),
            meta.lang.equal(lang)
            ).asSingle();
    }
    
    /**
     * リストの取得
     * @param assets
     * @param lang
     * @return
     */
    public List<AssetsLangRes> getList(Assets assets, Lang lang) {
        return Datastore.query(meta).filter(
            meta.assetsRef.equal(assets.getKey()),
            meta.lang.equal(lang)
            ).asList();
    }
    
    /**
     * 翻訳対象の取得
     * @param assets
     * @param lang
     * @return
     */
    public List<AssetsLangRes> getTransSrcList(Assets assets, Lang lang) {
        return Datastore.query(meta).filter(
            meta.assetsRef.equal(assets.getKey()),
            meta.lang.equal(lang),
            meta.transTarget.equal(true)
            ).asList();
    }

}
