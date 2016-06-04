package com.plucial.mulcms.controller.mulcms.template.page;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.controller.mulcms.BaseController;

public class AddController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        return forward("add.jsp");
    }
}
