package com.plucial.mulcms.enums;

/**
 * テキストリソース役割
 * <pre>
 * 追加や変更の場合は必ずTextResourcesService 内のUpdateも合わせて修正
 * </pre>
 * @author takahara
 *
 */
public enum ResDataType {
    SHORT_TEXT(RenderingType.text, true),
    LONG_TEXT(RenderingType.text, true),
    HTML(RenderingType.html, false);
    
    // TODO: image
    
    private RenderingType renderingType;
    
    private boolean transTarget;
    
    private ResDataType(RenderingType renderingType, boolean transTarget) {
        this.setRenderingType(renderingType);
        this.setTransTarget(transTarget);
    }

    public RenderingType getRenderingType() {
        return renderingType;
    }

    public void setRenderingType(RenderingType renderingType) {
        this.renderingType = renderingType;
    }

    public boolean isTransTarget() {
        return transTarget;
    }

    public void setTransTarget(boolean transTarget) {
        this.transTarget = transTarget;
    }
}
