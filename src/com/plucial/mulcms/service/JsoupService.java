package com.plucial.mulcms.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.plucial.mulcms.enums.RenderingAction;


public class JsoupService {
    
    /**
     * Base ドキュメント
     */
    private Document doc = null;
    
    /**
     * コンストラクター
     * @param html
     */
    public JsoupService(String html) {
        doc = Jsoup.parse(html);
    }
    
    /**
     * ゲットドッグ
     * @return
     */
    public Document getDoc() {
        return doc;
    }
    
    
    /**
     * 要素のHTMLを設定
     * @param baseHtml
     * @param cssQuery
     * @param insertValue
     * @param action
     * @return
     */
    public Document rendering(String cssQuery, RenderingAction action, String arg) {
        
        Elements targetElems = doc.select(cssQuery);
        
        // html,text,prepend,append,wrap,addClass,removeClass
        if(action == RenderingAction.html) targetElems.html(arg);
        
        if(action == RenderingAction.prepend) targetElems.prepend(arg);
        if(action == RenderingAction.append) targetElems.append(arg);
        if(action == RenderingAction.wrap) targetElems.append(arg);
        
        if(action == RenderingAction.addClass) targetElems.addClass(arg);
        if(action == RenderingAction.removeClass) targetElems.removeClass(arg);
        if(action == RenderingAction.removeAttr) targetElems.removeAttr(arg);
        
        if(action == RenderingAction.text) {
            for(Element elem: targetElems) {
                elem.text(arg);
            }
        }
        
        return doc;
    }
    
    /**
     * 属性の追加
     * @param doc
     * @param cssQuery
     * @param attr
     * @param value
     */
    public void addAttr(String cssQuery, String attr, String value) {
        Elements targetElems = doc.select(cssQuery);
        targetElems.attr(attr, value);
    }

}
