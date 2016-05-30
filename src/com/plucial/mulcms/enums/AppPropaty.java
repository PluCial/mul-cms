package com.plucial.mulcms.enums;

import com.plucial.global.Lang;

/**
 * App
 * @author takahara
 *
 */
public enum AppPropaty {
    GOOGLE_API_APPLICATION_NAME("Google Project Name", "MulCMS"),
    GOOGLE_API_PUBLIC_SERVER_KEY("Google API Service Key", "AIzaSyCPDuS7Uox7OjO6x-4bxQOZDQfUxKkWiSs"),
    USER_LANG("User Lang", Lang.ja.toString());
    
    /** 表示名 */
    private String name;
    
    /** デフォルト値 */
    private String defaultValue;
    
    /**
     * コンストラクター
     */
    private AppPropaty(String name, String defaultValue) {
        this.setName(name);
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
}
