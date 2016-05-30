package com.plucial.mulcms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.mulcms.dao.AppDao;
import com.plucial.mulcms.meta.AppMeta;
import com.plucial.mulcms.model.App;


public class AppService {

    /** DAO */
    private static final AppDao dao = new AppDao();
    
    /**
     * 取得
     * @param keyString
     * @return
     * @throws ObjectNotExistException 
     */
    public static App get(String keyString) throws ObjectNotExistException {
        App model = dao.getOrNull(createKey(keyString));
        if(model == null) throw new ObjectNotExistException();
        return model;
    }
    
    /**
     * リストの取得
     * @return
     */
    public static List<App> getList() {
        return dao.getList();
    }
    
    /**
     * App Map を取得
     * @return
     */
    public static Map<String, String> getMap() {
        List<App> list = getList();
        
        Map<String,String> map = new HashMap<String,String>();
        for (App app : list) {
            map.put(app.getKey().getName(),app.getValueString());
        }
        return map;
    }
    
    /**
     * PUT
     * @param keyString
     * @param value
     * @return
     */
    public static App put(String keyString, String value) {
        App model = new App();
        model.setKey(createKey(keyString));
        model.setValue(new Text(value));
        
        dao.put(model);
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
    protected static Key createKey(String keyString) {
        return Datastore.createKey(AppMeta.get(), keyString);
    }
}
