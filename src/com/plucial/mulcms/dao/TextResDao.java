package com.plucial.mulcms.dao;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;

import com.plucial.global.Lang;
import com.plucial.mulcms.meta.TextResMeta;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.TextRes;

public class TextResDao extends DaoBase<TextRes>{
    
    /** META */
    private static final  TextResMeta meta =  TextResMeta.get();
    
    
    /**
     * テキストリソースのリストを取得
     * @param langUnit
     * @return
     */
    public List<TextRes> getAppResList(Lang lang) {
        return Datastore.query(meta).filter(
            meta.appScope.equal(true),
            meta.lang.equal(lang)
            ).asList();
    }
    
    /**
     * テキストリソースのリストを取得
     * @param langUnit
     * @return
     */
    public List<TextRes> getPageResList(Page page, Lang lang) {
        return Datastore.query(meta).filter(
            meta.assetsRef.equal(page.getKey()),
            meta.lang.equal(lang)
            ).asList();
    }

}
