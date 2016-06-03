package com.plucial.mulcms.controller.mulcms.page;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.google.appengine.api.datastore.Text;
import com.plucial.mulcms.model.res.Res;
import com.plucial.mulcms.service.res.ResService;

public class UpdateResEntryController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
     // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/ajax_response.jsp");
        }
        
        String keyString = asString("keyString");
        String content = asString("content");
        
        
        Res model = ResService.get(keyString);

        model.setValue(new Text(content));        
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
