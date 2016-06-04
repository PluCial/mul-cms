package com.plucial.mulcms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import com.google.apphosting.api.ApiProxy;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.mulcms.dao.AppDao;
import com.plucial.mulcms.enums.AppProperty;
import com.plucial.mulcms.enums.Provider;
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
     * 
     * @return
     */
    public static List<App> getList(Provider provider) {
        return dao.getList(provider);
    }
    
    /**
     * App Map を取得
     * @return
     */
    public static Map<String, String> getPropertyMap(boolean isLocal) {
        List<App> list = getList();
        
        Map<String,String> map = new HashMap<String,String>();
        for (App app : list) {
            map.put(app.getKey().getName(),app.getValueString());
        }
        
        if(!map.containsKey(AppProperty.APP_ID.toString())) {
            String value = getAppId(isLocal);
            put(AppProperty.APP_ID, Provider.App, value);
            map.put(AppProperty.APP_ID.toString(), value);
        }
        
        if(!map.containsKey(AppProperty.APP_BASE_LANG.toString())) {
            put(AppProperty.APP_BASE_LANG, Provider.App, com.plucial.mulcms.App.APP_BASE_LANG.toString());
            map.put(AppProperty.APP_BASE_LANG.toString(), com.plucial.mulcms.App.APP_BASE_LANG.toString());
        }

        if(!map.containsKey(AppProperty.APP_DEFAULT_HOST_NAME.toString())) {
            String value = getAppDefaultHostName(isLocal);
            put(AppProperty.APP_DEFAULT_HOST_NAME, Provider.App, value);
            map.put(AppProperty.APP_DEFAULT_HOST_NAME.toString(), value);
        }

        if(!map.containsKey(AppProperty.APP_GCS_BUCKET_NAME.toString())) {
            String value = getAppDefaultHostName(isLocal);
            put(AppProperty.APP_GCS_BUCKET_NAME, Provider.Google, value);
            map.put(AppProperty.APP_GCS_BUCKET_NAME.toString(), value);
        }
        
        return map;
    }
    
    /**
     * App Id
     * @param isLocal
     * @return
     */
    private static String getAppId(boolean isLocal) {
        String hostName = getAppDefaultHostName(isLocal);
        String[] strArray = hostName.split("\\.");
        
        if(strArray == null || strArray.length <= 0) return null;
        
        return strArray[0];
    }
    
    /**
     * デフォルト GAE アプリドメイン(GCS デフォルトバゲット)
     * <app_id>.appspot.com
     * @return
     */
    private static String getAppDefaultHostName(boolean isLocal) {
        if(isLocal) return "localhost:8888";
        
        ApiProxy.Environment env = ApiProxy.getCurrentEnvironment();
        return env.getAttributes().get("com.google.appengine.runtime.default_version_hostname").toString();
    }
    
    /**
     * PUT
     * @param keyString
     * @param value
     * @return
     */
    public static App put(AppProperty appProperty, Provider provider, String value) {
        App model = new App();
        model.setKey(createKey(appProperty.toString()));
        model.setProvider(provider);
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
