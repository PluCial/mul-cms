package com.plucial.mulcms.controller.mulcms.form.control;

import java.util.Map;
import java.util.Properties;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.util.StringUtil;

import com.google.appengine.api.users.User;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.model.widgets.form.Form;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.widgets.form.FormControlService;
import com.plucial.mulcms.service.widgets.form.FormService;

public class AddEntryController extends BaseController {

    @Override
    public Navigation execute(Map<String, String> appPropertyMap, User user,
            Properties userLocaleProp) throws Exception {
        
        String keyString = asString("keyString");
        
        // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/mulcms/form/setting?keyString=" + keyString);
        }
        
        Page page = (Page)PageService.get(asString("parentKeyString"));
        Form form = (Form)FormService.get(page, keyString);
        
        String controlName = asString("controlName");
        boolean required = !StringUtil.isEmpty(asString("required"));
        boolean transFlg = !StringUtil.isEmpty(asString("transFlg"));

        FormControlService.put(form, controlName, required, transFlg);
        
        return redirect("/mulcms/form/setting?keyString=" + keyString + "&parentKeyString=" + page.getKey().getName());
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);

        v.add("keyString", v.required());
        v.add("controlName", v.required());
        
        return v.validate();
    }
}
