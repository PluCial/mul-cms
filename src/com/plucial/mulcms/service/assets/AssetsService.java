package com.plucial.mulcms.service.assets;

import java.util.UUID;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.mulcms.dao.AssetsDao;
import com.plucial.mulcms.meta.AssetsMeta;
import com.plucial.mulcms.model.Assets;
import com.plucial.mulcms.model.Template;


public class AssetsService {
    
    /** DAO */
    private static final AssetsDao dao = new AssetsDao();
    
    /**
     * 新しいモデルの設定
     * @param model
     * @param template
     */
//    protected static void settingNewModel(Assets model, Template template, String html) {
//        model.getTemplateRef().setModel(template);
//        model.setHtml(new Text(html));
//    }
    
    /**
     * 取得
     * @param keyString
     * @return
     * @throws ObjectNotExistException
     */
    public static Assets get(String keyString) throws ObjectNotExistException {
        Assets model = dao.getOrNull(createKey(keyString));
        if(model == null) throw new ObjectNotExistException();
        return model;
    }
    
    /**
     * テンプレートの設定
     * @param model
     */
    public static void settingTemplate(Assets model) {
        Template template = (Template)model.getTemplateRef().getModel();
        model.setTemplate(template);
    }
    
    /**
     * 削除
     * @param keyString
     */
    protected static void delete(String keyString) {
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
        return Datastore.createKey(AssetsMeta.get(), keyString);
    }
    
    
    /**
     * キーの作成
     * @return
     */
    protected static Key createKey() {
        // キーを乱数にする
        UUID uniqueKey = UUID.randomUUID();
        return createKey(uniqueKey.toString());
    }

}
