package com.plucial.mulcms.service;

import java.util.List;
import java.util.UUID;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.mulcms.dao.RenderingDao;
import com.plucial.mulcms.meta.RenderingMeta;
import com.plucial.mulcms.model.Rendering;
import com.plucial.mulcms.model.assets.Assets;


public class RenderingService {

    /** DAO */
    private static final RenderingDao dao = new RenderingDao();
    
    /**
     * リソースの取得
     * @param resId
     * @param lang
     * @return
     * @throws ObjectNotExistException
     */
    public static Rendering get(Assets assets, String keyString) throws ObjectNotExistException {
        Rendering model =  dao.getOrNull(createKey(assets, keyString));
        if(model == null) throw new ObjectNotExistException();
        return model;
    }
    
    /**
     * リストの取得
     * @param assets
     * @return
     */
    public static List<Rendering> getList(Assets assets) {
        return dao.getList(assets);
    }
    
    /**
     * 更新
     * @param model
     */
    public static void update(Rendering model) {
        dao.put(model);
    }

    /**
     * 更新
     * @param model
     */
    public static void update(Transaction tx, Rendering model) {
        Datastore.put(tx, model);
    }

    /**
     * 削除
     * @param tx
     * @param model
     */
    public static void delete(Transaction tx, Rendering model) {
        Datastore.delete(tx, model.getKey());
    }

    /**
     * 削除
     * @param model
     */
    public static void delete(Rendering model) {
        dao.delete(model.getKey());
    }
    
    /**
     * 削除
     * @param keyString
     */
    public static void delete(Assets assets, String keyString) {
        dao.delete(createKey(assets, keyString));
    }
    
    // ----------------------------------------------------------------------
    // キーの作成
    // ----------------------------------------------------------------------
    /**
     * キーの作成
     * @param keyString
     * @return
     */
    protected static Key createKey(Assets assets, String keyString) {
        return Datastore.createKey(assets.getKey(), RenderingMeta.get(), keyString);
    }

    /**
     * キーの作成
     * @return
     */
    protected static Key createKey(Assets assets) {
        UUID uuid = UUID.randomUUID();
        return Datastore.createKey(assets.getKey(), RenderingMeta.get(), uuid.toString());
    }
}
