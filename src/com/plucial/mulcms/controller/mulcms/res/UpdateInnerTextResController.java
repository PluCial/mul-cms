package com.plucial.mulcms.controller.mulcms.res;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.res.InnerTextRes;
import com.plucial.mulcms.service.res.ResService;

public class UpdateInnerTextResController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        
        InnerTextRes res = (InnerTextRes)ResService.get(asString("keyString"));
        requestScope("res", res);
        
        requestScope("cssQuery", res.getCssQuery());
        requestScope("editMode", res.isEditMode());
        requestScope("longText", res.isLongText());
        requestScope("content", res.getValueString());
        
        return forward("updateInnerTextRes.jsp");
    }
}
