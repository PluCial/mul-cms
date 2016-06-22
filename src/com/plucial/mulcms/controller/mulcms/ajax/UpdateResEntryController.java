package com.plucial.mulcms.controller.mulcms.ajax;

import java.util.Map;
import java.util.Properties;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.google.appengine.api.users.User;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.model.res.InnerTextRes;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.res.ResService;

public class UpdateResEntryController extends BaseController {

    @Override
    public Navigation execute(Map<String, String> appPropertyMap, User user,
            Properties userLocaleProp) throws Exception {
        
        // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/ajax_response.jsp");
        }
        
        String keyString = asString("keyString");
        String content = asString("content");
        
        Page page = (Page)PageService.get(asString("parentKeyString"));
        InnerTextRes model = (InnerTextRes)ResService.get(page, keyString);
        model.setStringToValue(content);
        ResService.update(model);
        
        requestScope("status", "OK");
        return forward("/ajax_response.jsp");
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);

        v.add("keyString", v.required());
        
        // コンテンツ
        v.add("content", v.required("内容を入力してください。"));
        
        return v.validate();
    }
}
