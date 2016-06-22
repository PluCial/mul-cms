package com.plucial.mulcms.controller.mulcms.form;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.jsoup.nodes.Document;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.util.StringUtil;

import com.google.appengine.api.users.User;
import com.plucial.gae.global.exception.NoContentsException;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.global.Lang;
import com.plucial.mulcms.controller.AppController;
import com.plucial.mulcms.enums.AppProperty;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.model.widgets.form.Form;
import com.plucial.mulcms.model.widgets.form.FormControl;
import com.plucial.mulcms.model.widgets.form.MailAction;
import com.plucial.mulcms.service.EMailService;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.widgets.form.FormControlService;
import com.plucial.mulcms.service.widgets.form.FormService;
import com.plucial.mulcms.service.widgets.form.ReceptionMailActionService;

public class ActionController extends AppController {
    
    @Override
    protected Navigation notSigned(Map<String, String> appPropertyMap,
            Lang localeLang) throws Exception {
        
        Lang userLang = Lang.valueOf(appPropertyMap.get(AppProperty.APP_BASE_LANG.toString()));
        Properties userLocaleProp = super.getAppProp(userLang);
        return execute(appPropertyMap, localeLang, userLang, userLocaleProp, false);
    }

    @Override
    protected Navigation signed(Map<String, String> appPropertyMap, User user,
            Lang userLang, Properties userLocaleProp) throws Exception {
        Lang localeLang = super.getLocaleLang();
        
        return execute(appPropertyMap, localeLang, userLang, userLocaleProp, true);
    }

    /**
     * リクエスト処理
     * @param appPropertyMap
     * @param isSigned
     * @return
     * @throws Exception
     */
    private Navigation execute(Map<String, String> appPropertyMap, Lang localeLang, Lang userLang, Properties userLocaleProp, boolean isSigned) throws Exception {

        // ----------------------------------------------------
        // Formの取得
        // ----------------------------------------------------
        String keyString = asString("formId");
        if(StringUtil.isEmpty(keyString)) throw new NoContentsException();

        Form form = null;
        try {
            Page page = (Page)PageService.get(asString("parentKeyString"));
            form = (Form)FormService.get(page, keyString);
        }catch(ObjectNotExistException e) {
            throw new NoContentsException();
        }
        
        // ----------------------------------------------------
        // コントローラーリストを取得
        // ----------------------------------------------------
        List<FormControl> controlList = FormControlService.getList(form);
        
        // ----------------------------------------------------
        // App 設定情報の取得
        // ----------------------------------------------------
        String gcsBucketName = appPropertyMap.get(AppProperty.APP_GCS_BUCKET_NAME.toString());
        
        // ----------------------------------------------------
        // 入力チェック
        // ----------------------------------------------------
        if (!isPost() || !validate(controlList)) {
            
            // ----------------------------------------------------
            // Page 生成
            // ----------------------------------------------------
            Document pageDoc = PageService.getRenderedDoc((Page)form.getAssetsRef().getModel(), super.getLocaleLang(), gcsBucketName, super.getDomainUrl(), isSigned);
            requestScope("pageHtml", pageDoc.outerHtml());
            
            return forward("/front.jsp");
        }
        
        // ----------------------------------------------------
        // アクション
        // ----------------------------------------------------
        for(FormControl control: controlList) {
            String postValue = asString(control.getControlName());
            control.setPostValue(postValue);
        }
        String googleApiPublicServerKey = appPropertyMap.get(AppProperty.GOOGLE_API_PUBLIC_SERVER_KEY.toString());
        String googleApiApplicationName = appPropertyMap.get(AppProperty.APP_ID.toString());
        
        String mailBody = ReceptionMailActionService.getMailBody(userLang, localeLang, controlList, userLocaleProp, googleApiPublicServerKey, googleApiApplicationName);
        
        List<MailAction> mailActionList = ReceptionMailActionService.getList(form);
        String adminEmail = appPropertyMap.get(AppProperty.APP_ADMIN_EMAIL.toString());
        
        // 送信
        EMailService.receptionMail(adminEmail, form.getName(), mailBody, super.isLocal(), mailActionList);
        
        return redirect("/" + super.getLocaleLang() + form.getTransitionPageRef().getKey().getName());
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate(List<FormControl> controlList) {
        Validators v = new Validators(request);

        
        for(FormControl control: controlList) {
            if(control.isRequired()) {
                v.add(control.getControlName(), v.required());
            }
            
            if(control.isEmail()) {
                v.add(control.getControlName(), 
                    v.maxlength(256), 
                    v.minlength(6),
                    v.regexp("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*([,;]\\s*\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*)*", "メールアドレスが正しくありません。"));
            }
        }
        
        return v.validate();
    }
}
