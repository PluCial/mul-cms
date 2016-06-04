package com.plucial.mulcms.enums;

import java.util.ArrayList;
import java.util.List;


/**
 * App
 * @author takahara
 *
 */
public enum Provider {
    App(
        AppProperty.APP_ID, 
        AppProperty.APP_BASE_LANG,
        AppProperty.APP_DEFAULT_HOST_NAME),
    Google(
        AppProperty.APP_GCS_BUCKET_NAME,
        AppProperty.GOOGLE_API_PUBLIC_SERVER_KEY);
    
    private List<AppProperty> propatyList = new ArrayList<AppProperty>();
    
    /**
     * コンストラクター
     */
    private Provider(AppProperty... propatys) {
        for(AppProperty pro: propatys) {
        this.getPropatyList().add(pro);
        }
    }

    public List<AppProperty> getPropatyList() {
        return propatyList;
    }

    public void setPropatyList(List<AppProperty> propatyList) {
        this.propatyList = propatyList;
    }
}
