package com.plucial.mulcms.service.res;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Transaction;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.global.Lang;
import com.plucial.mulcms.dao.res.AssetsLangResDao;
import com.plucial.mulcms.enums.RenderingType;
import com.plucial.mulcms.model.Assets;
import com.plucial.mulcms.model.res.AssetsLangRes;


public class AssetsLangResService extends ResService {
    
    /** DAO */
    private static final AssetsLangResDao dao = new AssetsLangResDao();
    
    /**
     * リソースの取得(チェック用)
     * @param resId
     * @param lang
     * @return
     * @throws ObjectNotExistException
     */
    public static AssetsLangRes get(String resId, Assets assets, RenderingType renderingType, String renderingAttr, Lang lang) throws ObjectNotExistException {
        AssetsLangRes model =  dao.get(resId, assets, renderingType, renderingAttr, lang);
        if(model == null) throw new ObjectNotExistException();
        return model;
    }
    
    /**
     * リストの取得
     * @param assets
     * @param lang
     * @return
     */
    public static List<AssetsLangRes> getList(Assets assets, Lang lang) {
        return dao.getList(assets, lang);
    }
    
    /**
     * 翻訳対象の取得
     * @param assets
     * @param lang
     * @return
     */
    public static List<AssetsLangRes> getTransSrcList(Assets assets, Lang lang) {
        return dao.getTransSrcList(assets, lang);
    }
    
    /**
     * 追加
     * @param resId
     * @param cssQuery
     * @param resDataType
     * @param renderingType
     * @param value
     * @param page
     * @param lang
     * @return
     */
    public static AssetsLangRes add(String resId, String cssQuery, RenderingType renderingType, String value, String renderingAttr, Assets assets, boolean editMode, Lang lang) {

        AssetsLangRes model = null;
        Transaction tx = Datastore.beginTransaction();
        try {
            model = add(
                tx, 
                resId, 
                cssQuery, 
                renderingType, 
                value,
                renderingAttr,
                assets,
                editMode,
                lang);
            
            tx.commit();
        }finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }
        
        return model;
    }
    
    /**
     * 追加
     * @param tx
     * @param resId
     * @param cssQuery
     * @param resDataType
     * @param renderingType
     * @param value
     * @param page
     * @param lang
     * @return
     */
    public static AssetsLangRes add(Transaction tx, String resId, String cssQuery, RenderingType renderingType, String value, String renderingAttr, Assets assets, boolean editMode, Lang lang) {
        AssetsLangRes model = new AssetsLangRes();
        initNewResModel(
            model, 
            resId, 
            cssQuery, 
            renderingType, 
            value,
            renderingAttr,
            editMode);
        
        model.getAssetsRef().setModel(assets);
        model.setLang(lang);
        
        // 保存
        Datastore.put(tx, model);
        
        return model;
    }

}
