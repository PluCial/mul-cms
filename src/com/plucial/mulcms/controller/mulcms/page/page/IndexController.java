package com.plucial.mulcms.controller.mulcms.page.page;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.service.assets.PageService;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        List<Page> pageList = PageService.getList();
        requestScope("pageList", pageList);
        
        return forward("index.jsp");
    }
}
