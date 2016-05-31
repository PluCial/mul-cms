package com.plucial.mulcms.controller.mulcms.page;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.plucial.mulcms.model.PageTemplate;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.template.PageTemplateService;

public class AddEntryController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/mulcms/page/");
        }
        
        String url = asString("url");
        String templateKey = asString("template");
        
        PageTemplate template = (PageTemplate)PageTemplateService.get(templateKey);
        
        PageService.put(url, template);
        
        return redirect("/mulcms/page/");
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);

        v.add("url", v.required());
        v.add("template", v.required());
        
        return v.validate();
    }
}
