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
    mulTextScope("data-mul-text-scope"),
    mulTextId("data-mul-text-id"),
//    mulTextId("data-mul-text-id"),
//    mulAppText("data-mul-app-text"),
//    mulAppTextId("data-mul-app-text-id"),
//    mulPageText("data-mul-page-text"),
//    mulPageTextId("data-mul-page-text-id"),
    mulLongText("data-mul-long-text"),
    data_lang("data-lang"),
    datq_public_uri("data-public-uri"),
    data_res_edit_type("data-res-edit-type");
    
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
