package com.plucial.mulcms.controller.mulcms.form;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.service.form.FormService;

public class DeleteEntryController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        
        String keyString = asString("keyString");
        
        // 存在チェック
        FormService.get(keyString);
        
        // 削除
        FormService.delete(keyString);
        
        
        return redirect("/mulcms/form/");
    }
}
