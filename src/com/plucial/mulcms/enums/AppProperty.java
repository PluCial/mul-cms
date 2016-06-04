package com.plucial.mulcms.enums;


/**
 * App
 * @author takahara
 *
 */
public enum AppProperty {
    
    APP_ID("Google Application Id", true),
    APP_DEFAULT_HOST_NAME("Google Default Host Name", true),
    APP_GCS_BUCKET_NAME("Google Cloud Storage Bucket Name", false),
    GOOGLE_API_PUBLIC_SERVER_KEY("Google API Public Service Key", false);
    
    /** 表示名 */
    private String name;
    
    /** デフォルト値 */
    private String defaultValue;
    
    /** 不変フラグ */
    private boolean unalterable;
    
    /**
     * コンストラクター
     */
    private AppProperty(String name, boolean unalterable) {
        this.setName(name);
        this.setDefaultValue(defaultValue);
        this.setUnalterable(unalterable);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
