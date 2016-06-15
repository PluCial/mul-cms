package com.plucial.mulcms.controller.mulcms.setting;

import java.util.Map;
import java.util.Properties;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.google.appengine.api.users.User;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.enums.AppProperty;
import com.plucial.mulcms.service.AppService;
import com.plucial.mulcms.validator.NGValidator;

public class UpdateEntryController extends BaseController {

    @Override
    public Navigation execute(Map<String, String> appPropertyMap, User user,
            Properties userLocaleProp) throws Exception {
        
        // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/mulcms/setting/");
        }
        
        String propertyKey = asString("propertyKey");
        String value = asString("propertyValue");
        
        AppProperty appProperty = null;
        try {
            appProperty = AppProperty.valueOf(propertyKey);
        }catch(Exception e) {
            Validators v = new Validators(request);
            v.add("propertyKey",
                new NGValidator("この項目は設定できません。"));
            v.validate();
            return forward("/mulcms/setting/");
        }
        
        // 管理者メールチェック
        if(appProperty == AppProperty.APP_ADMIN_EMAIL) {
            if(!user.getEmail().equals(value)) {
                Validators v = new Validators(request);
                v.add("propertyKey",
                    new NGValidator("このメールアドレスは使用できません。"));
                v.validate();
                return forward("/mulcms/setting/");
            }
        }
        
        AppService.put(appProperty, value);
        
        return redirect("/mulcms/setting/");
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);

        v.add("propertyKey", v.required("キーがありません"));
        v.add("propertyValue", v.required("内容を入力してください。"));
        
        return v.validate();
    }
}
