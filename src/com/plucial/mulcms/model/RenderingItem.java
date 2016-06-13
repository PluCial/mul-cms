package com.plucial.mulcms.model;

import org.jsoup.nodes.Document;

import com.plucial.global.Lang;

public interface RenderingItem {
    
    /**
     * レンダーリング
     * @param org.jsoup.nodes.Document
     */
    public void reenderingDoc(Document doc, Lang localeLang, String domainUrl, boolean isSigned);

}
