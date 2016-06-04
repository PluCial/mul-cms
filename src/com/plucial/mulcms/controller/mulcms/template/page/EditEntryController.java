package com.plucial.mulcms.controller.mulcms.template.page;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.google.appengine.api.datastore.Text;
import com.plucial.global.Lang;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.PageTemplate;
import com.plucial.mulcms.service.template.TemplateService;

public class EditEntryController extends BaseController {

    @Override
    public Navigation run() throws Exception {
     // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/mulcms/template/page/edit");
        }
        
        String keyString = asString("keyString");
        String name = asString("name");
        String html = asString("html");
        Lang lang = Lang.valueOf(asString("lang"));
        
        PageTemplate modal = (PageTemplate)TemplateService.get(keyString);
        modal.setName(name);
        modal.setHtml(new Text(html));
        modal.setLang(lang);
        
        TemplateService.update(modal);
        
        return redirect("/mulcms/template/page/");
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);

        v.add("keyString", v.required());
        v.add("name", v.required());
        v.add("html", v.required());
        v.add("lang", v.required());
        
        return v.validate();
    }
}
