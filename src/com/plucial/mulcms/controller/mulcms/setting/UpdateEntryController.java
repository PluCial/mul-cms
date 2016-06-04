package com.plucial.mulcms.controller.mulcms.setting;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.plucial.mulcms.enums.AppProperty;
import com.plucial.mulcms.service.AppService;
import com.plucial.mulcms.validator.NGValidator;

public class UpdateEntryController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
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
        
        AppService.put(appProperty, value);
        
        return redirect("/mulcms/setting/");
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);

        v.add("propertyKey", v.required());
        v.add("propertyValue", v.required());
        
        return v.validate();
    }
}
