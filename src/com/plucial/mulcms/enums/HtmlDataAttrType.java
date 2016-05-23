package com.plucial.mulcms.enums;

/**
 * テキストリソース役割
 * <pre>
 * 追加や変更の場合は必ずTextResourcesService 内のUpdateも合わせて修正
 * </pre>
 * @author takahara
 *
 */
public enum HtmlDataAttrType {
    data_lang("data-lang"),
    datq_public_uri("data-public-uri"),
    data_res_edit_type("data-res-edit-type");
    
    /** 属性 */
    private String attr;
    
    /**
     * コンストラクター
     * @param attr
     */
    private HtmlDataAttrType(String attr) {
        this.setAttr(attr);
    }
    
    public String getAttr() {
        return attr;
    }
    
    public void setAttr(String attr) {
        this.attr = attr;
    }
}
