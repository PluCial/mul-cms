package com.plucial.mulcms.service.res;

import java.util.List;
import java.util.UUID;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.global.Lang;
import com.plucial.mulcms.dao.TextResDao;
import com.plucial.mulcms.meta.ResMeta;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.TextRes;


public class TextResService extends ResService {
    
    /** DAO */
    private static final TextResDao dao = new TextResDao();
    
    /**
     * リソースの取得
     * @param resId
     * @param lang
     * @return
     * @throws ObjectNotExistException
     */
    public static TextRes get(String resId, Lang lang) throws ObjectNotExistException {
        TextRes model =  dao.getOrNull(createKey(resId, lang));
        if(model == null) throw new ObjectNotExistException();
        return model;
    }
    
    /**
     * App リソースリストを取得
     * @param lang
     * @return
     */
    public static List<TextRes> getAppResList(Lang lang) {
        return dao.getAppResList(lang);
    }
    
    /**
     * Page リソースリストを取得
     * @param page
     * @param lang
     * @return
     */
    public static List<TextRes> getPageResList(Page page, Lang lang) {
        return dao.getPageResList(page, lang);
    }
    
    /**
     * 追加(Scope: App)
     * @param resId
     * @param lang
     * @param content
     * @return
     */
    public static TextRes put(String resId, Lang lang, String content) {

        TextRes model = null;
        Transaction tx = Datastore.beginTransaction();
        try {
            model = put(tx, resId, lang, content);
            tx.commit();
        }finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }
        
        return model;
    }
    
    /**
     * 追加(Scope: App)
     * @param tx
     * @param resId
     * @param lang
     * @param content
     * @return
     */
    public static TextRes put(Transaction tx, String resId, Lang lang, String content) {
        TextRes model = new TextRes();
        model.setKey(createKey(resId, lang));
        model.setResId(resId);
        model.setAppScope(true);
        model.setStringToContent(content);
        
        // 保存
        Datastore.put(tx, model);
        
        return model;
    }
    
    /**
     * 追加(Scope: Page)
     * @param page
     * @param keyString
     * @param lang
     * @param content
     * @return
     */
    public static TextRes put(Page page, Lang lang, String content) {

        TextRes model = null;
        Transaction tx = Datastore.beginTransaction();
        try {
            model = put(tx, page, lang, content);
            tx.commit();
        }finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }
        
        return model;
    }
    
    /**
     * 追加(Scope: Page)
     * @param tx
     * @param page
     * @param keyString
     * @param lang
     * @param content
     * @return
     */
    public static TextRes put(Transaction tx, Page page, Lang lang, String content) {
        TextRes model = new TextRes();
        // キーを乱数にする
        UUID resId = UUID.randomUUID();
        
        model.setKey(createKey(resId.toString(), lang));
        model.setResId(resId.toString());
        model.setStringToContent(content);
        model.getAssetsRef().setModel(page);
        
        // 保存
        Datastore.put(tx, model);
        
        return model;
    }
    
    // ----------------------------------------------------------------------
    // キーの作成
    // ----------------------------------------------------------------------
    /**
     * キーの作成
     * @param keyString
     * @return
     */
    protected static Key createKey(String keyString, Lang lang) {
        return Datastore.createKey(ResMeta.get(), keyString + "_" + lang.toString());
    }

}
