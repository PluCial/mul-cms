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
    mulScope("data-mul-scope"),
    mulId("data-mul-id"),
    mulDataType("data-mul-data-type");
//    mulTextScope("data-mul-text-scope"),
//    mulTextId("data-mul-text"),
//    mulLongText("data-mul-long-text");
    
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
