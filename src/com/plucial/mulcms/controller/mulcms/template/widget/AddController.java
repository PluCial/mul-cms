package com.plucial.mulcms.controller.mulcms.template.widget;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class AddController extends Controller {

    @Override
    public Navigation run() throws Exception {
        return forward("add.jsp");
    }
}
