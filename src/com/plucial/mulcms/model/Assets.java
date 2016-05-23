package com.plucial.mulcms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.CreationDate;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;
import org.slim3.datastore.ModificationDate;

import com.google.appengine.api.datastore.Key;

@Model(schemaVersion = 1)
public class Assets implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;
    
    /** ソートの最大値 */
    @Attribute(unindexed = true)
    private double childSortOrderMax = 0.0;
    
    /** ソート順 */
    private double sortOrder = 0.0;
    
    /**
     * 作成日時
     */
    @Attribute(listener = CreationDate.class)
    private Date createDate;
    
    /**
     * 更新日時
     */
    @Attribute(listener = ModificationDate.class)
    private Date updateDate;
    
    // ----------------------------------------------------------------------
    // 非永久化項目
    // ----------------------------------------------------------------------
    /** テンプレート */
    @Attribute(persistent = false)
    private Template template;
    
    /** rawResList */
    @Attribute(persistent = false)
    private List<Res> resList;
    
    // ----------------------------------------------------------------------
    // 関連
    // ----------------------------------------------------------------------
    /** 親との関連 */
    private ModelRef<Assets> parentRef = new ModelRef<Assets>(Assets.class);
    
    /** テンプレートとの関連 */
    private ModelRef<Template> templateRef = new ModelRef<Template>(Template.class);

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
        Assets other = (Assets) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public ModelRef<Template> getTemplateRef() {
        return templateRef;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<Res> getResList() {
        return resList;
    }

    public void setResList(List<Res> resList) {
        this.resList = resList;
    }

    public double getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(double sortOrder) {
        this.sortOrder = sortOrder;
    }

    public double getChildSortOrderMax() {
        return childSortOrderMax;
    }

    public void setChildSortOrderMax(double childSortOrderMax) {
        this.childSortOrderMax = childSortOrderMax;
    }

    public ModelRef<Assets> getParentRef() {
        return parentRef;
    }
}
