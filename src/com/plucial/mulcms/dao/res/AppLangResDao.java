package com.plucial.mulcms.dao.res;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;

import com.plucial.global.Lang;
import com.plucial.mulcms.enums.RenderingType;
import com.plucial.mulcms.meta.res.AppLangResMeta;
import com.plucial.mulcms.model.res.AppLangRes;

public class AppLangResDao extends DaoBase<AppLangRes>{
    
    /** META */
    private static final  AppLangResMeta meta =  AppLangResMeta.get();
    
    /**
     * テキストリソースのリストを取得
     * @param langUnit
     * @return
     */
    public AppLangRes get(String resId, Lang lang) {
        return Datastore.query(meta).filter(
            meta.resId.equal(resId),
            meta.lang.equal(lang)
            ).asSingle();
    }
    
    /**
     * リストの取得
     * @param lang
     * @return
     */
    public List<AppLangRes> getList(Lang lang) {
        return Datastore.query(meta).filter(
            meta.lang.equal(lang)
            ).asList();
    }
    
    /**
     * 翻訳対象の取得
     * @param lang
     * @return
     */
    public List<AppLangRes> getTransSrcList(Lang lang) {
        return Datastore.query(meta).filter(
            meta.lang.equal(lang),
            meta.renderingType.equal(RenderingType.text)
            ).asList();
    }

}
