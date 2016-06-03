package com.plucial.mulcms.enums;

/**
 * テキストリソース役割
 * <pre>
 * 追加や変更の場合は必ずTextResourcesService 内のUpdateも合わせて修正
 * </pre>
 * @author takahara
 *
 */
public enum RenderingType {
    text(true),
    long_text(true), 
//    html(false), TODO: いつか
    attr(false);
    
    private boolean transTarget;
    
    private RenderingType(boolean transTarget) {
        this.setTransTarget(transTarget);
    }

    public boolean isTransTarget() {
        return transTarget;
    }

    public void setTransTarget(boolean transTarget) {
        this.transTarget = transTarget;
    }
}
