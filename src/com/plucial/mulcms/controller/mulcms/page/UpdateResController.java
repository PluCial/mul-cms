package com.plucial.mulcms.controller.mulcms.page;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.plucial.mulcms.model.res.Res;
import com.plucial.mulcms.service.res.ResService;

public class UpdateResController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        Res res = ResService.get(asString("keyString"));
        requestScope("res", res);
        
        requestScope("content", res.getValueString());
        requestScope("editMode", res.isEditMode());
        
        return forward("updateRes.jsp");
    }
}
