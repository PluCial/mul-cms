package com.plucial.mulcms.service.res;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Transaction;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.mulcms.dao.res.AssetsResDao;
import com.plucial.mulcms.enums.RenderingType;
import com.plucial.mulcms.enums.ResDataType;
import com.plucial.mulcms.model.Assets;
import com.plucial.mulcms.model.res.AssetsRes;


public class AssetsResService extends ResService {
    
    /** DAO */
    private static final AssetsResDao dao = new AssetsResDao();
    
    /**
     * リソースの取得
     * @param resId
     * @param lang
     * @return
     * @throws ObjectNotExistException
     */
    public static AssetsRes get(String resId, Assets assets) throws ObjectNotExistException {
        AssetsRes model =  dao.get(resId, assets);
        if(model == null) throw new ObjectNotExistException();
        return model;
    }
    
    /**
     * リストの取得
     * @param assets
     * @return
     */
    public static List<AssetsRes> getList(Assets assets) {
        return dao.getList(assets);
    }
    
    /**
     * 追加
     * @param resId
     * @param cssQuery
     * @param resDataType
     * @param renderingType
     * @param value
     * @param page
     * @return
     */
    public static AssetsRes add(String resId, String cssQuery, ResDataType resDataType, RenderingType renderingType, String value, Assets assets) {

        AssetsRes model = null;
        Transaction tx = Datastore.beginTransaction();
        try {
            model = add(
                tx, 
                resId, 
                cssQuery, 
                resDataType, 
                renderingType, 
                value,
                assets);
            
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
     * @return
     */
    public static AssetsRes add(Transaction tx, String resId, String cssQuery, ResDataType resDataType, RenderingType renderingType, String value, Assets assets) {
        AssetsRes model = new AssetsRes();
        initNewResModel(
            model, 
            resId, 
            cssQuery, 
            resDataType, 
            renderingType, 
            value);
        
        model.getAssetsRef().setModel(assets);
        
        // 保存
        Datastore.put(tx, model);
        
        return model;
    }

}
