package com.plucial.mulcms.enums;


/**
 * App
 * @author takahara
 *
 */
public enum AppProperty {
    
    APP_ID(true),
    APP_DEFAULT_HOST_NAME(true),
    APP_ADMIN_EMAIL(false),
    APP_BASE_LANG(false),
    APP_GCS_BUCKET_NAME(false),
    GOOGLE_API_PUBLIC_SERVER_KEY(false);
    
    /** デフォルト値 */
    private String defaultValue;
    
    /** 不変フラグ */
    private boolean unalterable;
    
    /**
     * コンストラクター
     */
    private AppProperty(boolean unalterable) {
        this.setDefaultValue(defaultValue);
        this.setUnalterable(unalterable);
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isUnalterable() {
        return unalterable;
    }

    public void setUnalterable(boolean unalterable) {
        this.unalterable = unalterable;
    }
}
