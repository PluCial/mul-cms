package com.plucial.mulcms.controller.mulcms.page.page;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.PageTemplate;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.template.PageTemplateService;

public class EditEntryController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
     // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/mulcms/page/page/view");
        }
        
        String keyString = asString("keyString");
        String templateKey = asString("template");
        
        Page page = PageService.get(keyString);
        PageTemplate template = (PageTemplate)PageTemplateService.get(templateKey);
        
        page.getTemplateRef().setKey(template.getKey());
        
        PageService.update(page);
        
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
        
        return v.validate();
    }
}
