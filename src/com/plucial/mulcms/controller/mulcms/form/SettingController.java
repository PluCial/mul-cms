package com.plucial.mulcms.controller.mulcms.form;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.model.widgets.form.Form;
import com.plucial.mulcms.model.widgets.form.FormControl;
import com.plucial.mulcms.model.widgets.form.MailAction;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.widgets.form.FormControlService;
import com.plucial.mulcms.service.widgets.form.FormService;
import com.plucial.mulcms.service.widgets.form.MailActionService;

public class SettingController extends BaseController {

    @Override
    public Navigation execute(Map<String, String> appPropertyMap, User user,
            Properties userLocaleProp) throws Exception {
        
        String keyString = asString("keyString");
        Page page = (Page)PageService.get(asString("parentKeyString"));
        
        Form form = (Form)FormService.get(page, keyString);
        requestScope("form", form);
        
        requestScope("name", form.getName());
        requestScope("cssQuery", form.getCssQuery());
        
        List<Page> pageList = PageService.getList();
        requestScope("pageList", pageList);
        
        List<FormControl> controlList = FormControlService.getList(form);
        requestScope("controlList", controlList);
        
        List<MailAction> mailActionList = MailActionService.getList(form);
        requestScope("mailActionList", mailActionList);
        
        return forward("setting.jsp");
    }
}
