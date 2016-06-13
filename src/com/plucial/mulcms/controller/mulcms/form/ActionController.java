package com.plucial.mulcms.controller.mulcms.form;

import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.util.StringUtil;

import com.plucial.gae.global.exception.NoContentsException;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.mulcms.controller.AppController;
import com.plucial.mulcms.enums.AppProperty;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.model.form.Form;
import com.plucial.mulcms.model.form.FormControl;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.form.FormControlService;
import com.plucial.mulcms.service.form.FormService;

public class ActionController extends AppController {

    @Override
    public Navigation run() throws Exception {

        // ----------------------------------------------------
        // Formの取得
        // ----------------------------------------------------
        String keyString = asString("keyString");
        if(StringUtil.isEmpty(keyString)) throw new NoContentsException();

        Form form = null;
        try {
            form = (Form)FormService.get(keyString);
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
        Map<String, String> appPropertyMap = super.getAppPropertyMap();
        String gcsBucketName = appPropertyMap.get(AppProperty.APP_GCS_BUCKET_NAME.toString());
        
        // ----------------------------------------------------
        // 入力チェック
        // ----------------------------------------------------
        if (!isPost() || !validate(controlList)) {
            
            // ----------------------------------------------------
            // Page 生成
            // ----------------------------------------------------
            Document pageDoc = PageService.getRenderedDoc((Page)form.getAssetsRef().getModel(), super.getLocaleLang(), gcsBucketName, super.getDomainUrl(), isSigned());
            requestScope("pageHtml", pageDoc.outerHtml());
            
            return forward("/front.jsp");
        }
        
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
