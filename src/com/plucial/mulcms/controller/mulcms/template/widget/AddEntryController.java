package com.plucial.mulcms.controller.mulcms.template.widget;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.plucial.mulcms.service.template.WidgetTemplateService;

public class AddEntryController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
     // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/mulcms/template/widget/add.jsp");
        }
        
        String name = asString("name");
        String cssQuery = asString("cssQuery");
        String html = asString("html");
        
//        JsoupService JsoupService = new JsoupService(html);
        WidgetTemplateService.put(name, html, cssQuery);
        
        return redirect("/mulcms/template/widget/");
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);

        v.add("name", v.required());
        v.add("html", v.required());
        v.add("cssQuery", v.required());
        
        return v.validate();
    }
}
