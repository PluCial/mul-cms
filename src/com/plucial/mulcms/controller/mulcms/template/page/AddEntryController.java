package com.plucial.mulcms.controller.mulcms.template.page;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.plucial.global.Lang;
import com.plucial.mulcms.service.JsoupService;
import com.plucial.mulcms.service.template.PageTemplateService;

public class AddEntryController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/mulcms/template/page/add.jsp");
        }
        
        String name = asString("name");
        String html = asString("html");
        Lang lang = Lang.valueOf(asString("lang"));
        
        // HTML補完(htmlタグ、bodyタグなど自動追加)
        JsoupService JsoupService = new JsoupService(html);
        
        PageTemplateService.put(name, JsoupService.getDoc(), lang);
        
        
        return redirect("/mulcms/template/page/");
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);

        v.add("name", v.required());
        v.add("html", v.required());
        v.add("lang", v.required());
        
        return v.validate();
    }
}
