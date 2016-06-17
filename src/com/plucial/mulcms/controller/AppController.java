package com.plucial.mulcms.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.ServletContextLocator;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.plucial.gae.global.exception.NoContentsException;
import com.plucial.gae.global.exception.NoLangParameterException;
import com.plucial.global.Lang;
import com.plucial.mulcms.enums.AppProperty;
import com.plucial.mulcms.exception.NoSignedException;
import com.plucial.mulcms.service.AppService;

/**
 * Base Controller
 * @author takahara
 *
 */
public abstract class AppController extends Controller {
	
	/**
     * Logger
     */
    private static final Logger logger = Logger.getLogger(AppController.class.getName());
    
    /**
     * デバイスがスマートフォンであるか判定
     * @param request
     * @return
     */
    public boolean isSmartPhone() {

        String userAgent = request.getHeader("User-Agent").toLowerCase();

        if(userAgent != null && (userAgent.indexOf("iphone") > 0 || userAgent.indexOf("android") > 0)) {
            return true;
        }

        return false;
    }
    
    /**
     * 開発環境かどうか
     * @return
     */
    public boolean isLocal(){
        return ServletContextLocator.get().getServerInfo().indexOf("Development") >= 0;
    }
    
    /**
     * エラーハンドリング
     */
    @Override
    public Navigation handleError(Throwable error) throws Throwable {
        
        // 404エラー
        if(error instanceof NoContentsException) {
            return forward("/404.html");
        }
        
        // 開発環境ではなく、404ではないエラーが発生した場合エラーログを出力
        logger.log(Level.WARNING, error.getMessage(), error);
        
        
        // 500エラー画面に遷移する
        return redirect("/500.html");
    }
    
    /**
     * パラメーターから言語を取得
     * @return
     * @throws NoLangParameterException 
     */
    public Lang getLocaleLang() throws NoContentsException {
        
    	Lang lang = null;
        try {
        	lang =  Lang.valueOf(asString("lang"));
            
        }catch(Exception e) {
            throw new NoContentsException();
        }
         
        return lang;
    }
    
    /**
     * アクセスしているドメインURLを取得
     * <pre>
     * ローカル： localhost:8888
     * 本番： xxxxx.com
     * </pre>
     * @return
     */
    public String getDomein() {
        String serverName = request.getServerName() != null ? request.getServerName() : "";
        String serverPort = request.getServerPort() > 8000 ? ":" + request.getServerPort() : "";
        return serverName + serverPort;
    }
    
    /**
     * アクセスしているドメインURLを取得
     * <pre>
     * ローカル： http://localhost:8888
     * 本番： https://xxxxx.com
     * </pre>
     * @return 
     */
    public String getDomainUrl() {
        String scheme = request.getScheme() != null ? request.getScheme() + "://" : "";
        return scheme + getDomein();
    }
    
    /**
     * リクエストURIを取得
     * <pre>
     * 例：/xxxx/xxxx
     * </pre>
     * @return
     */
    public String getRequestUri() {
    	return request.getRequestURI();
    }
    
    /**
     * プロパティを取得
     * @return
     * @throws IOException 
     */
    public Properties getAppProp(Lang lang) throws IOException {
        Properties prop = new Properties();
        InputStream in = this.getClass().getResourceAsStream("/application_" + lang.toString() + ".properties");
        try {
            prop.load(in);
        } finally {
            try {
                in.close();
            } catch (Exception e) {}
        }
        
        return prop;
    }
    
    /**
     * ログインチェック
     * @return
     * @throws NoSignedException 
     */
    public User getAdminUser() throws NoSignedException {
        
        UserService userService = UserServiceFactory.getUserService();
        
        // サインしていない場合
        if(!userService.isUserLoggedIn()) throw new NoSignedException();
        
        // 管理者の場合
        if(!userService.isUserAdmin()) throw new NoSignedException();
        
        return userService.getCurrentUser();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        Map<String, String> appPropertyMap = AppService.getPropertyMap();
        
        try {
            User user = getAdminUser();
            
            Properties userProp = getAppProp(Lang.ja);
            requestScope("userProp", userProp);
            
            Lang localeLang = null;
            try {
                localeLang = Lang.valueOf(appPropertyMap.get(AppProperty.APP_BASE_LANG.toString()));
            }catch(NullPointerException e) {
                localeLang = Lang.ja;
            }
            
            return signed(appPropertyMap, user, localeLang, userProp);
            
        }catch(NoSignedException e) {
            return notSigned(appPropertyMap, getLocaleLang());
        }
    }
    
    /**
     * リクエスト処理
     * @param appPropertyMap
     * @param localeLang
     * @return
     * @throws Exception
     */
    protected abstract Navigation notSigned(Map<String, String> appPropertyMap, Lang localeLang) throws Exception;

    /**
     * リクエスト処理
     * @param appPropertyMap
     * @param user
     * @param userLang
     * @param userLocaleProp
     * @return
     * @throws Exception
     */
    protected abstract Navigation signed(Map<String, String> appPropertyMap, User user, Lang userLang, Properties userLocaleProp) throws Exception;

}
