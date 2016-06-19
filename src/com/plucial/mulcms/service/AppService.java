package com.plucial.mulcms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.appidentity.AppIdentityServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import com.google.apphosting.api.ApiProxy;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.mulcms.dao.AppDao;
import com.plucial.mulcms.enums.AppProperty;
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
    public static Map<String, String> getPropertyMap() {
        List<App> list = getList();
        
        Map<String,String> map = new HashMap<String,String>();
        for (App app : list) {
            map.put(app.getKey().getName(),app.getValueString());
        }
        
        return map;
    }
    
    /**
     * App Id
     * @param isLocal
     * @return
     */
    public static String getAppId(boolean isLocal) {
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
    public static String getAppDefaultHostName(boolean isLocal) {
        if(isLocal) return "localhost:8888";
        
        ApiProxy.Environment env = ApiProxy.getCurrentEnvironment();
        return env.getAttributes().get("com.google.appengine.runtime.default_version_hostname").toString();
    }
    
    /**
     * デフォルトバケット名
     * @param isLocal
     * @return
     */
    public static String getDefaultGcsBucketName(boolean isLocal) {
        if(isLocal) return "localhost:8888";
        return AppIdentityServiceFactory.getAppIdentityService().getDefaultGcsBucketName();
    }
    
    /**
     * PUT
     * @param keyString
     * @param value
     * @return
     */
    public static App put(AppProperty appProperty, String value) {
        App model = new App();
        model.setKey(createKey(appProperty.toString()));
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
