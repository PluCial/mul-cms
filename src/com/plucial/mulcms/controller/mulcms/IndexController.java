package com.plucial.mulcms.controller.mulcms;

import java.util.List;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.service.assets.PageService;

public class IndexController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        
        List<Page> pageList = PageService.getList();
        requestScope("pageList", pageList);
        
        return forward("index.jsp");
    }
}
