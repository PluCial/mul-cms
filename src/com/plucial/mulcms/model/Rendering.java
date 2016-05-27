package com.plucial.mulcms.model;

import java.io.Serializable;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;
import com.plucial.mulcms.enums.RenderingType;
import com.plucial.mulcms.enums.ResScope;

@Model(schemaVersion = 1)
public class Rendering implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;
    
    /** スコープ */
    private ResScope scope;
    
    /** 
     * selector-syntax
     * <pre>ex. (h3.r > a)</pre>
     */
//    @Attribute(unindexed = true)
    private String cssQuery;
    
    /** RenderingAction */
    private RenderingType action;

    /**
     * Returns the key.
     *
     * @return the key
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key
     *            the key
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Returns the version.
     *
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version
     *            the version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Rendering other = (Rendering) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

    public RenderingType getAction() {
        return action;
    }

    public void setAction(RenderingType action) {
        this.action = action;
    }

    public ResScope getScope() {
        return scope;
    }

    public void setScope(ResScope scope) {
        this.scope = scope;
    }

    public String getCssQuery() {
        return cssQuery;
    }

    public void setCssQuery(String cssQuery) {
        this.cssQuery = cssQuery;
    }
}
