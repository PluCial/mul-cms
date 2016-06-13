package com.plucial.mulcms.controller.mulcms.form.control;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.util.StringUtil;

import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.form.Form;
import com.plucial.mulcms.service.form.FormControlService;
import com.plucial.mulcms.service.form.FormService;

public class AddEntryController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        
        String keyString = asString("keyString");
        
        // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/mulcms/form/setting?keyString=" + keyString);
        }
        
        Form form = (Form)FormService.get(keyString);
        
        String controlName = asString("controlName");
        boolean required = !StringUtil.isEmpty(asString("required"));
        boolean transFlg = !StringUtil.isEmpty(asString("transFlg"));

        FormControlService.put(form, controlName, required, transFlg);
        
        return redirect("/mulcms/form/setting?keyString=" + keyString);
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
