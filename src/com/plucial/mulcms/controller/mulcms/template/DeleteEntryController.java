package com.plucial.mulcms.controller.mulcms.template;

import java.util.List;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.model.template.Template;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.template.PageTemplateService;
import com.plucial.mulcms.service.template.TemplateService;

public class DeleteEntryController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        
        String keyString = asString("keyString");
        String type = asString("type");
        Template template = PageTemplateService.get(keyString);
        
        List<Page> pageList = PageService.getList(template);
        if(pageList != null && pageList.size() > 0) {
            return forward("deleteEntry.jsp");
        }
        
        // 削除
        TemplateService.delete(keyString);
        
        
        return redirect("/mulcms/template/" + type + "/");
    }
}
