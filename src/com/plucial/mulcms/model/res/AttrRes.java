package com.plucial.mulcms.model.res;

import java.io.Serializable;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slim3.datastore.Model;

import com.plucial.global.Lang;
import com.plucial.mulcms.model.RenderingItem;

@Model(schemaVersion = 1)
public class AttrRes extends Res implements Serializable, RenderingItem {

    private static final long serialVersionUID = 1L;
    
    private String attr;

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public void reenderingDoc(Document doc, Lang localeLang, String domainUrl, boolean isSigned) {
        Elements elements = doc.select(super.getCssQuery());
        if(elements == null) return;
        
        elements.attr(this.getAttr(), this.getValueString());
    }
}
