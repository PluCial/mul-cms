package com.plucial.mulcms.model.res;

import java.io.Serializable;

import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.plucial.global.Lang;
import com.plucial.mulcms.model.Assets;

@Model(schemaVersion = 1)
public class AssetsLangRes extends Res implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Assetsとの関連 */
    private ModelRef<Assets> assetsRef = new ModelRef<Assets>(Assets.class);
    
    /** 言語 */
    private Lang lang;

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }

    public ModelRef<Assets> getAssetsRef() {
        return assetsRef;
    }
}
