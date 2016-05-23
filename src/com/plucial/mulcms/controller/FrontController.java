package com.plucial.mulcms.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class FrontController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        System.out.println(asString("lang") + ": " + asString("path"));
        
        return forward("front.jsp");
    }
}
