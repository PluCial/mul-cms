package com.plucial.mulcms.enums;

/**
 * テキストリソース役割
 * <pre>
 * 追加や変更の場合は必ずTextResourcesService 内のUpdateも合わせて修正
 * </pre>
 * @author takahara
 *
 */
public enum MulAttrType {
    scope("data-scope"),
    resId("data-res-id"),
    rendering("data-rendering"),
    edit("data-edit"),
    formId("data-form-id"),
    langSelect("data-lang-select");
    
    /** 属性 */
    private String attr;
    
    /**
     * コンストラクター
     * @param attr
     */
    private MulAttrType(String attr) {
        this.setAttr(attr);
    }
    
    public String getAttr() {
        return attr;
    }
    
    public void setAttr(String attr) {
        this.attr = attr;
    }
}
