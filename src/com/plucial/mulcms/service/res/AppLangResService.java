package com.plucial.mulcms.service.res;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Transaction;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.global.Lang;
import com.plucial.mulcms.dao.res.AppLangResDao;
import com.plucial.mulcms.enums.RenderingType;
import com.plucial.mulcms.enums.ResDataType;
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
    public static AppLangRes get(String resId, Lang lang) throws ObjectNotExistException {
        AppLangRes model =  dao.get(resId, lang);
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
    public static List<AppLangRes> getTransSrcList(Lang lang) {
        return dao.getTransSrcList(lang);
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
    public static AppLangRes add(String resId, String cssQuery, ResDataType resDataType, RenderingType renderingType, String value, Lang lang) {

        AppLangRes model = null;
        Transaction tx = Datastore.beginTransaction();
        try {
            model = add(
                tx, 
                resId, 
                cssQuery, 
                resDataType, 
                renderingType, 
                value,
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
    public static AppLangRes add(Transaction tx, String resId, String cssQuery, ResDataType resDataType, RenderingType renderingType, String value, Lang lang) {
        AppLangRes model = new AppLangRes();
        initNewResModel(
            model, 
            resId, 
            cssQuery, 
            resDataType, 
            renderingType, 
            value);
        
        model.setLang(lang);
        
        // 保存
        Datastore.put(tx, model);
        
        return model;
    }

}
