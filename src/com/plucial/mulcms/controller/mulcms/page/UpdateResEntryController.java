package com.plucial.mulcms.controller.mulcms.page;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.util.StringUtil;

import com.google.appengine.api.datastore.Text;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.res.Res;
import com.plucial.mulcms.service.res.ResService;

public class UpdateResEntryController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        
     // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/mulcms/page/updateRes");
        }
        
        String keyString = asString("keyString");
        String content = asString("content");
        boolean editMode = !StringUtil.isEmpty(asString("editMode"));
        String pageKey = asString("pageKey");
        String lang = asString("lang");
        
        
        Res model = ResService.get(keyString);
        model.setEditMode(editMode);
        model.setValue(new Text(content));        
        ResService.update(model);
        
        return redirect("/mulcms/page/setting?keyString=" + pageKey + "&lang=" + lang);
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
