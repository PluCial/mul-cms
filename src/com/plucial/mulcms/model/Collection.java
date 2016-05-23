package com.plucial.mulcms.model;

import java.io.Serializable;

import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

@Model(schemaVersion = 1)
public class Collection extends Assets implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // ----------------------------------------------------------------------
    // 関連
    // ----------------------------------------------------------------------
    
    /** テンプレートとの関連 */
    private ModelRef<Template> itemTemplateRef = new ModelRef<Template>(Template.class);

    public ModelRef<Template> getItemTemplateRef() {
        return itemTemplateRef;
    }

}
