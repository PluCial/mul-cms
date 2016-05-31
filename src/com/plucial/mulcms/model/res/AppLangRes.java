package com.plucial.mulcms.model.res;

import java.io.Serializable;

import org.slim3.datastore.Model;

import com.plucial.global.Lang;

@Model(schemaVersion = 1)
public class AppLangRes extends Res implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /** 言語 */
    private Lang lang;

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }
}
