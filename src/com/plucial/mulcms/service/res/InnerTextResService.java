package com.plucial.mulcms.service.res;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Transaction;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.global.Lang;
import com.plucial.mulcms.dao.res.InnerTextResDao;
import com.plucial.mulcms.model.assets.Assets;
import com.plucial.mulcms.model.res.InnerTextRes;


public class InnerTextResService extends ResService {
    
    /** DAO */
    private static final InnerTextResDao dao = new InnerTextResDao();
    
    /**
     * リソースの取得(チェック用)
     * @param resId
     * @param lang
     * @return
     * @throws ObjectNotExistException
     */
    public static InnerTextRes get(Assets assets, String cssQuery, Lang lang) throws ObjectNotExistException {
        InnerTextRes model =  dao.get(assets, cssQuery, lang);
        if(model == null) throw new ObjectNotExistException();
        return model;
    }
    
    /**
     * リストの取得
     * @param assets
     * @param lang
     * @return
     */
    public static List<InnerTextRes> getList(Assets assets, Lang lang) {
        return dao.getList(assets, lang);
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
    public static InnerTextRes add(Assets assets, String cssQuery, Lang lang, String value, boolean editMode, boolean isLongText) {

        InnerTextRes model = null;
        Transaction tx = Datastore.beginTransaction();
        try {
            model = add(
                tx, 
                assets, 
                cssQuery, 
                lang, 
                value,
                editMode,
                isLongText);
            
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
    public static InnerTextRes add(Transaction tx, Assets assets, String cssQuery, Lang lang, String value, boolean editMode, boolean isLongText) {
        InnerTextRes model = new InnerTextRes();
        model.setKey(createKey(assets));
        model.setCssQuery(cssQuery);
        model.setStringToValue(value);
        model.setEditMode(editMode);
        model.setLongText(isLongText);
        model.getAssetsRef().setModel(assets);
        model.setLang(lang);
        
        // 保存
        Datastore.put(tx, model);
        
        return model;
    }

}
