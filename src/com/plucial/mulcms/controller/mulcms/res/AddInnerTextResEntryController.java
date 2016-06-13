package com.plucial.mulcms.controller.mulcms.res;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.util.StringUtil;

import com.plucial.global.Lang;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.assets.Assets;
import com.plucial.mulcms.service.assets.AssetsService;
import com.plucial.mulcms.service.res.InnerTextResService;
import com.plucial.mulcms.validator.NGValidator;

public class AddInnerTextResEntryController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        
        String assetsKeyString = asString("assetsKeyString");
        Lang lang = Lang.valueOf(asString("lang"));
        
        String returnUrl = "/mulcms/page/resource?keyString=" + assetsKeyString + "&lang=" + lang.toString();
        
        // 入力チェック
        if (!isPost() || !validate()) {
            return forward(returnUrl);
        }

        String cssQuery = asString("cssQuery");
        boolean isEditMode = !StringUtil.isEmpty(asString("editMode"));
        boolean isLongText = !StringUtil.isEmpty(asString("longText"));
        
        Assets assets = AssetsService.get(assetsKeyString);
        Document doc = Jsoup.parse(assets.getHtmlString());
        Elements elements = doc.select(cssQuery);
        if(elements == null || elements.size() <= 0) {
            Validators v = new Validators(request);
            v.add("cssQuery",
                new NGValidator("Css Queryで指定した要素はHTMLに存在しません。"));
            v.validate();
            return forward(returnUrl);
        }
        
        InnerTextResService.add(assets, cssQuery, lang, elements.first().text(), isEditMode, isLongText);
        
        return redirect(returnUrl);
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);

        v.add("assetsKeyString", v.required());
        
        // コンテンツ
        v.add("cssQuery", v.required());
        
        return v.validate();
    }
}
