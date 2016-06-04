package com.plucial.mulcms.controller.mulcms.template.widget;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.plucial.global.Lang;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.service.template.WidgetTemplateService;

public class AddEntryController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        
     // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/mulcms/template/widget/add.jsp");
        }
        
        String name = asString("name");
        String html = asString("html");

        Document doc = Jsoup.parseBodyFragment(html);
        WidgetTemplateService.put(name, doc.body().html(), Lang.ja);
        
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
        
        return v.validate();
    }
}
