package com.plucial.mulcms.model.res;

import java.io.Serializable;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Text;
import com.plucial.global.Lang;
import com.plucial.mulcms.model.Rendering;

@Model(schemaVersion = 1)
public class Res extends Rendering implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /** 言語 */
    private Lang lang;
    
    @Attribute(unindexed = true)
    private Text value;
    
    /**
     * コンテンツの文字列を取得
     * @return
     */
    public String getValueString() {
        return this.getValue() == null ? null : this.getValue().getValue();
    }

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }

    public Text getValue() {
        return value;
    }

    public void setValue(Text value) {
        this.value = value;
    }
    
}
