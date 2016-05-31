package com.plucial.mulcms.model.res;

import java.io.Serializable;

import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.plucial.mulcms.model.Assets;

@Model(schemaVersion = 1)
public class AssetsRes extends Res implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Pageとの関連 */
    private ModelRef<Assets> assetsRef = new ModelRef<Assets>(Assets.class);

    public ModelRef<Assets> getAssetsRef() {
        return assetsRef;
    }
}
