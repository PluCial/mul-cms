package com.plucial.mulcms.controller.mulcms.template.page;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.google.appengine.api.datastore.Text;
import com.plucial.mulcms.enums.HtmlDataAttrType;
import com.plucial.mulcms.model.PageTemplate;
import com.plucial.mulcms.service.template.TemplateService;

public class EditEntryController extends Controller {

    @Override
    public Navigation run() throws Exception {
     // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/mulcms/template/page/edit");
        }
        
        String keyString = asString("keyString");
        String name = asString("name");
        String html = asString("html");
        
        PageTemplate modal = (PageTemplate)TemplateService.get(keyString);
        modal.setName(name);
        modal.setHtml(new Text(html));
        
        TemplateService.update(modal);
        
        getData(html);
        
        return redirect("/mulcms/template/page/");
    }
    
    private void getData(String html) {
        Document doc = Jsoup.parse(html);
        Elements elms = doc.select("[" + HtmlDataAttrType.mulCms.getAttr() + "]");
        
        for(Element elem: elms) {
            if(elem.hasAttr(HtmlDataAttrType.textResId.getAttr())) {
                System.out.println(elem.attr(HtmlDataAttrType.textResId.getAttr()));
            }else {
                elem.attr(HtmlDataAttrType.textResId.getAttr(), "new res id");
            }
        }
        

        System.out.println(doc.outerHtml());
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);

        v.add("keyString", v.required());
        v.add("name", v.required());
        v.add("html", v.required());
        
        return v.validate();
    }
}
