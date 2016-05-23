package com.plucial.mulcms.controller.mulcms;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        return redirect("/mulcms/page/page/");
    }
}
