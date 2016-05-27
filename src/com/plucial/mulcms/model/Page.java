package com.plucial.mulcms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.plucial.global.Lang;

@Model(schemaVersion = 1)
public class Page extends Assets implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Attribute(unindexed = true)
    private List<Lang> langList = new ArrayList<Lang>();

    public List<Lang> getLangList() {
        return langList;
    }

    public void setLangList(List<Lang> langList) {
        this.langList = langList;
    }
}
