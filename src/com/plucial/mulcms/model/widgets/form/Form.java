package com.plucial.mulcms.model.widgets.form;

import java.io.Serializable;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.plucial.global.Lang;
import com.plucial.mulcms.enums.MulAttrType;
import com.plucial.mulcms.model.RenderingItem;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.model.widgets.Widget;

@Model(schemaVersion = 1)
public class Form extends Widget implements Serializable, RenderingItem {

    private static final long serialVersionUID = 1L;
    
    private String name;
    
    /** 成功時に遷移するPage */
    private ModelRef<Page> transitionPageRef = new ModelRef<Page>(Page.class);

    public ModelRef<Page> getTransitionPageRef() {
        return transitionPageRef;
    }

    public void reenderingDoc(Document doc, Lang localeLang, String domainUrl, boolean isSigned) {

        Element elements = doc.select(super.getCssQuery()).first();
        if(elements == null) return;

        elements.attr("action", domainUrl + "/formAction");
        elements.attr("method", "post");
        elements.append("<input type='hidden' name='lang' value='" + localeLang.toString() + "'>");
        elements.append("<input type='hidden' name='parentKeyString' value='" + super.getAssetsRef().getKey().getName() + "'>");
        elements.append("<input type='hidden' name='formId' value='" + super.getKey().getName() + "'>");

        elements.removeAttr(MulAttrType.formId.getAttr());

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
