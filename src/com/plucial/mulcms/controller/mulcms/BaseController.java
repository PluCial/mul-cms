package com.plucial.mulcms.controller.mulcms;

import java.util.Map;
import java.util.Properties;

import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;
import com.plucial.global.Lang;
import com.plucial.mulcms.controller.AppController;

public abstract class BaseController extends AppController {

    @Override
    protected Navigation notSigned(Map<String, String> appPropertyMap,
            Lang localeLang) throws Exception {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    protected Navigation signed(Map<String, String> appPropertyMap, User user,
            Lang localeLang, Properties userLocaleProp) throws Exception {
        return execute(appPropertyMap, user, userLocaleProp);
    }

    /**
     * リクエスト処理
     * @param appPropertyMap
     * @param user
     * @param userLocaleProp
     * @return
     * @throws Exception
     */
    public abstract Navigation execute(Map<String, String> appPropertyMap, User user, Properties userLocaleProp) throws Exception;

}
