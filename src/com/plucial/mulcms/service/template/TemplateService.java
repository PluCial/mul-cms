package com.plucial.mulcms.service.template;

import java.util.List;
import java.util.UUID;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.mulcms.dao.TemplateDao;
import com.plucial.mulcms.meta.TemplateMeta;
import com.plucial.mulcms.model.Template;


public class TemplateService {
    
    /** DAO */
    private static final TemplateDao dao = new TemplateDao();
    
    /**
     * 新しいモデルの設定
     * @param model
     * @param name
     * @param html
     */
    protected static void settingNewModel(Template model, String name, String html) {
        model.setKey(createKey());
        model.setName(name);
        model.setHtml(new Text(html));
    }
    
    /**
     * 取得
     * @param keyString
     * @return
     * @throws ObjectNotExistException 
     */
    public static Template get(String keyString) throws ObjectNotExistException {
        Template model = dao.get(createKey(keyString));
        if(model == null) throw new ObjectNotExistException();
        return model;
    }
    
    /**
     * 更新
     * @param model
     */
    public static void update(Template model) {
        dao.put(model);
    }
    
    /**
     * リストを取得
     * @return
     */
    public static List<? extends Template> getList() {
        return dao.getList();
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
        return Datastore.createKey(TemplateMeta.get(), keyString);
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
