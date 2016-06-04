package com.plucial.mulcms.service.res;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Transaction;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.global.Lang;
import com.plucial.mulcms.dao.res.AppLangResDao;
import com.plucial.mulcms.enums.RenderingType;
import com.plucial.mulcms.model.res.AppLangRes;


public class AppLangResService extends ResService {
    
    /** DAO */
    private static final AppLangResDao dao = new AppLangResDao();
    
    /**
     * リソースの取得
     * @param resId
     * @param lang
     * @return
     * @throws ObjectNotExistException
     */
    public static AppLangRes get(String resId, RenderingType renderingType, String renderingAttr, Lang lang) throws ObjectNotExistException {
        AppLangRes model =  dao.get(resId, renderingType, renderingAttr, lang);
        if(model == null) throw new ObjectNotExistException();
        return model;
    }
    
    /**
     * リストの取得
     * @param lang
     * @return
     */
    public static List<AppLangRes> getList(Lang lang) {
        return dao.getList(lang);
    }
    
    /**
     * 翻訳対象の取得
     * @param lang
     * @return
     */
    public static List<AppLangRes> getList(Lang lang, boolean isTransTraget) {
        return dao.getList(lang, isTransTraget);
    }
    
    /**
     * 
     * @param resId
     * @param cssQuery
     * @param renderingType
     * @param value
     * @param lang
     * @return
     */
    public static AppLangRes add(String resId, String cssQuery, RenderingType renderingType, String value, String renderingAttr, boolean editMode, Lang lang) {

        AppLangRes model = null;
        Transaction tx = Datastore.beginTransaction();
        try {
            model = add(
                tx, 
                resId, 
                cssQuery, 
                renderingType, 
                value,
                renderingAttr,
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
     * @param renderingType
     * @param value
     * @param renderingAttr
     * @param lang
     * @return
     */
    public static AppLangRes add(Transaction tx, String resId, String cssQuery, RenderingType renderingType, String value, String renderingAttr, boolean editMode, Lang lang) {
        AppLangRes model = new AppLangRes();
        initNewResModel(
            model, 
            resId, 
            cssQuery, 
            renderingType,
            value,
            renderingAttr,
            editMode);
        
        model.setLang(lang);
        
        // 保存
        Datastore.put(tx, model);
        
        return model;
    }

}
