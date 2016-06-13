package com.plucial.mulcms.model.res;

import java.io.Serializable;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

@Model(schemaVersion = 1)
public class InnerRes extends Res implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /** 変更モード */
    @Attribute(unindexed = true)
    private boolean editMode = false;

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }
}
