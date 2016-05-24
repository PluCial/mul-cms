package com.plucial.mulcms.controller.mulcms.page.widget;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.WidgetTemplate;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.assets.WidgetService;
import com.plucial.mulcms.service.template.TemplateService;

public class AddEntryController extends Controller {

    @Override
    public Navigation run() throws Exception {
     // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/mulcms/page/page/view");
        }
        
        String keyString = asString("keyString");
        String templateKey = asString("template");
        String cssQuery = asString("cssQuery");
        
        Page page = PageService.get(keyString);
        WidgetTemplate template = (WidgetTemplate)TemplateService.get(templateKey);
        
        WidgetService.put(page, template, cssQuery);
        
        return redirect("/mulcms/page/page/view?keyString=" + keyString);
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);

        v.add("keyString", v.required());
        v.add("template", v.required());
        v.add("cssQuery", v.required());
        
        return v.validate();
    }
}
