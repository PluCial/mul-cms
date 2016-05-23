package com.plucial.mulcms.model;

import java.io.Serializable;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

@Model(schemaVersion = 1)
public class WidgetTemplate extends Template implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 
     * selector-syntax
     * <pre>ex. (h3.r > a)</pre>
     */
    @Attribute(unindexed = true)
    private String cssQuery;

    public String getCssQuery() {
        return cssQuery;
    }

    public void setCssQuery(String cssQuery) {
        this.cssQuery = cssQuery;
    }
}
