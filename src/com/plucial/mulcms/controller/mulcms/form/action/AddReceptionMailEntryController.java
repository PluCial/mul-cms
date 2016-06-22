package com.plucial.mulcms.controller.mulcms.form.action;

import java.util.Map;
import java.util.Properties;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.google.appengine.api.users.User;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.model.widgets.form.Form;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.widgets.form.FormService;
import com.plucial.mulcms.service.widgets.form.ReceptionMailActionService;

public class AddReceptionMailEntryController extends BaseController {

    @Override
    public Navigation execute(Map<String, String> appPropertyMap, User user,
            Properties userLocaleProp) throws Exception {
        
        // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/mulcms/form/setting?keyString=" + asString("keyString"));
        }
        
        String formKeyString = asString("keyString");
        String sendEmail = asString("email");
        
        Page page = (Page)PageService.get(asString("parentKeyString"));
        Form form = (Form)FormService.get(page, formKeyString);
        
        ReceptionMailActionService.add(form, sendEmail);
        
        return redirect("/mulcms/form/setting?keyString=" + form.getKey().getName() + "&parentKeyString=" + page.getKey().getName());
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);

        v.add("keyString", v.required());
        // メール
        v.add("email", 
            v.required("メールアドレスを入力してください。"),
            v.maxlength(256, "メールアドレスが長すぎます。"), 
            v.minlength(6, "メールアドレスが短すぎます。"),
            v.regexp("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*([,;]\\s*\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*)*", "メールアドレスが正しくありません。"));
        
        return v.validate();
    }
}
