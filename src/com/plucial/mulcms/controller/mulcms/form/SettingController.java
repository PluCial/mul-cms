package com.plucial.mulcms.controller.mulcms.form;

import java.util.List;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.form.Form;
import com.plucial.mulcms.model.form.FormControl;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.form.FormControlService;
import com.plucial.mulcms.service.form.FormService;

public class SettingController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        
        String keyString = asString("keyString");
        Form form = FormService.get(keyString);
        requestScope("form", form);
        
        requestScope("name", form.getName());
        
        List<Page> pageList = PageService.getList();
        requestScope("pageList", pageList);
        
        List<FormControl> controlList = FormControlService.getList();
        requestScope("controlList", controlList);
        
        return forward("setting.jsp");
    }
}
