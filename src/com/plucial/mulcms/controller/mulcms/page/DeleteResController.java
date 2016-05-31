package com.plucial.mulcms.controller.mulcms.page;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.plucial.mulcms.model.res.Res;
import com.plucial.mulcms.service.res.ResService;

public class DeleteResController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        String keyString = asString("keyString");
        Res res = ResService.get(keyString);
        
        requestScope("res", res);
        
        return forward("deleteRes.jsp");
    }
}
