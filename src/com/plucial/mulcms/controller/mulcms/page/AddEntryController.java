package com.plucial.mulcms.controller.mulcms.page;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.PageTemplate;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.template.PageTemplateService;
import com.plucial.mulcms.validator.NGValidator;

public class AddEntryController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        
        // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/mulcms/page/");
        }
        
        String url = asString("url");
        String templateKey = asString("template");
        
        String keyString = url.replace("../", "");
        if(!keyString.startsWith("/")) {
            keyString = "/" + keyString;
        }
        
        // 拡張子チェック
        if(!keyString.endsWith(".html")) {
            Validators v = new Validators(request);
            v.add("url",
                new NGValidator("URLは(.html)で終わる必要があります。"));
            v.validate();
            return forward("/mulcms/page/");
        }

        // 重複チェック
        try {
            PageService.get(keyString);
            Validators v = new Validators(request);
            v.add("url",
                new NGValidator("このURLはすでに存在するため、追加できません。"));
            v.validate();
            return forward("/mulcms/page/");
            
        }catch(ObjectNotExistException e) {}
        
        PageTemplate template = (PageTemplate)PageTemplateService.get(templateKey);
        
        PageService.put(keyString, template);
        
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
