package com.plucial.mulcms.service.res;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Transaction;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.mulcms.dao.res.AppResDao;
import com.plucial.mulcms.enums.RenderingType;
import com.plucial.mulcms.enums.ResDataType;
import com.plucial.mulcms.model.res.AppRes;


public class AppResService extends ResService {
    
    /** DAO */
    private static final AppResDao dao = new AppResDao();
    
    /**
     * リソースの取得
     * @param resId
     * @param lang
     * @return
     * @throws ObjectNotExistException
     */
    public static AppRes get(String resId) throws ObjectNotExistException {
        AppRes model =  dao.get(resId);
        if(model == null) throw new ObjectNotExistException();
        return model;
    }
    
    /**
     * リストの取得
     * @return
     */
    public static List<AppRes> getList() {
        return dao.getList();
    }
    
    /**
     * 追加
     * @param resId
     * @param cssQuery
     * @param resDataType
     * @param renderingType
     * @param value
     * @return
     */
    public static AppRes add(String resId, String cssQuery, ResDataType resDataType, RenderingType renderingType, String value) {

        AppRes model = null;
        Transaction tx = Datastore.beginTransaction();
        try {
            model = add(
                tx, 
                resId, 
                cssQuery, 
                resDataType, 
                renderingType, 
                value);
            
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
     * @return
     */
    public static AppRes add(Transaction tx, String resId, String cssQuery, ResDataType resDataType, RenderingType renderingType, String value) {
        AppRes model = new AppRes();
        initNewResModel(
            model, 
            resId, 
            cssQuery, 
            resDataType, 
            renderingType, 
            value);
        
        // 保存
        Datastore.put(tx, model);
        
        return model;
    }

}
