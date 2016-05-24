package com.plucial.mulcms.controller.mulcms.template.widget;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.google.appengine.api.datastore.Text;
import com.plucial.mulcms.model.WidgetTemplate;
import com.plucial.mulcms.service.template.TemplateService;

public class EditEntryController extends Controller {

    @Override
    public Navigation run() throws Exception {
     // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/mulcms/template/widget/edit");
        }
        
        String keyString = asString("keyString");
        String name = asString("name");
        String html = asString("html");
        
        WidgetTemplate modal = (WidgetTemplate)TemplateService.get(keyString);
        modal.setName(name);
        modal.setHtml(new Text(html));
        
        TemplateService.update(modal);
        
        
        return redirect("/mulcms/template/widget/");
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
        
        return v.validate();
    }
}
