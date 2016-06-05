package com.plucial.mulcms.controller.mulcms;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.plucial.mulcms.controller.AppController;

public abstract class BaseController extends AppController {
    
    /**
     * ユーザーの取得
     * @return
     */
    public User getAdminUser() {
        UserService us = UserServiceFactory.getUserService();
        return us.getCurrentUser();
    }

}
