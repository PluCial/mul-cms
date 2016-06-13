package com.plucial.mulcms.controller.mulcms.res;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.google.appengine.api.datastore.Text;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.res.AttrRes;
import com.plucial.mulcms.service.res.ResService;

public class UpdateAttrResEntryController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        
        // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/mulcms/res/updateAttrRes");
        }
        
        String keyString = asString("keyString");
        String cssQuery = asString("cssQuery");
        String attr = asString("attr");
        String content = asString("content");
        
        String assetsKeyString = asString("assetsKeyString");
        String lang = asString("lang");
        
        
        AttrRes model = (AttrRes)ResService.get(keyString);
        model.setValue(new Text(content));
        model.setAttr(attr);
        model.setCssQuery(cssQuery);
        ResService.update(model);
        
        return redirect("/mulcms/page/resource?keyString=" + assetsKeyString + "&lang=" + lang);
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
        v.add("cssQuery", v.required("内容を入力してください。"));
        v.add("attr", v.required("内容を入力してください。"));
        
        return v.validate();
    }
}
