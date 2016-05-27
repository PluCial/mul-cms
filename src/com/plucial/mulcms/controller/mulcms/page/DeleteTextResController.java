package com.plucial.mulcms.controller.mulcms.page;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.plucial.mulcms.model.TextRes;
import com.plucial.mulcms.service.res.TextResService;

public class DeleteTextResController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        String keyString = asString("keyString");
        TextRes textRes = TextResService.get(keyString);
        
        requestScope("textRes", textRes);
        
        return forward("deleteTextRes.jsp");
    }
}
