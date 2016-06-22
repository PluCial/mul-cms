package com.plucial.mulcms.service.res;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.datastore.Transaction;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.global.Lang;
import com.plucial.mulcms.dao.res.AttrResDao;
import com.plucial.mulcms.model.assets.Assets;
import com.plucial.mulcms.model.res.AttrRes;


public class AttrResService extends ResService {
    
    /** DAO */
    private static final AttrResDao dao = new AttrResDao();
    
    /**
     * リソースの取得
     * @param assets
     * @param lang
     * @param cssQuery
     * @param attr
     * @return
     * @throws ObjectNotExistException
     */
    public static AttrRes get(Assets assets, Lang lang, String cssQuery, String attr) throws ObjectNotExistException {
        AttrRes model =  dao.get(assets, lang, cssQuery, attr);
        if(model == null) throw new ObjectNotExistException();
        return model;
    }
    
    /**
     * リストの取得
     * @param assets
     * @param lang
     * @return
     */
    public static List<AttrRes> getList(Assets assets, Lang lang) {
        return dao.getList(assets, lang);
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
    public static AttrRes add(Assets assets, String cssQuery, Lang lang, String attr, String attrValue) {

        AttrRes model = null;
        Transaction tx = Datastore.beginTransaction();
        try {
            model = add(
                tx, 
                assets, 
                cssQuery, 
                lang, 
                attr,
                attrValue);
            
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
    public static AttrRes add(Transaction tx, Assets assets, String cssQuery, Lang lang, String attr, String attrValue) {
        AttrRes model = new AttrRes();
        model.setKey(createKey(assets));
        model.setCssQuery(cssQuery);
        model.setLang(lang);
        model.setAttr(attr);
        model.setValue(new Text(attrValue));
        model.getAssetsRef().setModel(assets);
        
        // 保存
        Datastore.put(tx, model);
        
        return model;
    }

}
