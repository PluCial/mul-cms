package com.plucial.mulcms.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.plucial.mulcms.enums.RenderingType;


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
    public Document renderingHTML(String cssQuery, String arg, RenderingType action) {
        
        Elements targetElems = doc.select(cssQuery);
        
        // html,text,prepend,append,wrap,addClass,removeClass
        if(action == RenderingType.html) targetElems.html(arg);
        
        if(action == RenderingType.prepend) targetElems.prepend(arg);
        if(action == RenderingType.append) targetElems.append(arg);
        if(action == RenderingType.wrap) targetElems.append(arg);
        
        if(action == RenderingType.addClass) targetElems.addClass(arg);
        if(action == RenderingType.removeClass) targetElems.removeClass(arg);
        if(action == RenderingType.removeClass) targetElems.removeAttr(arg);
        
        if(action == RenderingType.text) {
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
