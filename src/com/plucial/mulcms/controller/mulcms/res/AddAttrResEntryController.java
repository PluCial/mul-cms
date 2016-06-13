package com.plucial.mulcms.controller.mulcms.res;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.plucial.global.Lang;
import com.plucial.mulcms.controller.mulcms.BaseController;
import com.plucial.mulcms.model.assets.Assets;
import com.plucial.mulcms.service.assets.AssetsService;
import com.plucial.mulcms.service.res.AttrResService;
import com.plucial.mulcms.validator.NGValidator;

public class AddAttrResEntryController extends BaseController {

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
        String attr = asString("attr");
        
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
//        
//        if(!elements.first().hasAttr(attr)) {
//            Validators v = new Validators(request);
//            v.add("attr",
//                new NGValidator("要素の属性が存在しません。"));
//            v.validate();
//            return forward(returnUrl);
//        }
        
        AttrResService.add(assets, cssQuery, lang, attr, elements.first().attr(attr));
        
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
        v.add("attr", v.required());
        
        return v.validate();
    }
}
