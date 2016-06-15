package com.plucial.mulcms.service.widgets.form;

import java.util.List;
import java.util.UUID;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.mulcms.dao.widgets.form.FormActionDao;
import com.plucial.mulcms.meta.widgets.form.FormActionMeta;
import com.plucial.mulcms.model.widgets.form.Form;
import com.plucial.mulcms.model.widgets.form.FormAction;



public class FormActionService {
    
    /** DAO */
    private static final FormActionDao dao = new FormActionDao();
    
    /**
     * リソースの取得
     * @param resId
     * @param lang
     * @return
     * @throws ObjectNotExistException
     */
    public static FormAction get(String keyString) throws ObjectNotExistException {
        FormAction model =  dao.getOrNull(createKey(keyString));
        if(model == null) throw new ObjectNotExistException();
        return model;
    }
    
    /**
     * リストの取得
     * @param assets
     * @return
     */
    public static List<? extends FormAction> getList(Form form) {
        return dao.getList(form);
    }
    
    /**
     * 更新
     * @param model
     */
    public static void update(FormAction model) {
        dao.put(model);
    }

    /**
     * 更新
     * @param model
     */
    public static void update(Transaction tx, FormAction model) {
        Datastore.put(tx, model);
    }

    /**
     * 削除
     * @param tx
     * @param model
     */
    public static void delete(Transaction tx, FormAction model) {
        Datastore.delete(tx, model.getKey());
    }

    /**
     * 削除
     * @param model
     */
    public static void delete(FormAction model) {
        dao.delete(model.getKey());
    }
    
    /**
     * 削除
     * @param keyString
     */
    public static void delete(String keyString) {
        dao.delete(createKey(keyString));
    }
    
    // ----------------------------------------------------------------------
    // キーの作成
    // ----------------------------------------------------------------------
    /**
     * キーの作成
     * @param keyString
     * @return
     */
    protected static Key createKey(String keyString) {
        return Datastore.createKey(FormActionMeta.get(), keyString);
    }

    /**
     * キーの作成
     * @return
     */
    protected static Key createKey() {
        UUID uuid = UUID.randomUUID();
        return Datastore.createKey(FormActionMeta.get(), uuid.toString());
    }
    
    

}
