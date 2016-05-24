package com.plucial.mulcms.model;

import java.io.Serializable;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.util.StringUtil;

import com.google.appengine.api.datastore.Text;

@Model(schemaVersion = 1)
public class TextRes extends Res implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * コンテンツ
     */
    @Attribute(unindexed = true)
    private Text content;

    public Text getContent() {
        return content;
    }

    public void setContent(Text content) {
        this.content = content;
    }

    /**
     * コンテンツの文字列を取得
     * @return
     */
    public String getContentString() {
        return content == null ? null : content.getValue();
    }
    
    /**
     * 文字列を適切に変換してコンテンツにセットする
     * @param str
     */
    public void setStringToContent(String content) {
        if(StringUtil.isEmpty(content.trim())) throw new NullPointerException();
        
        // 前後の空白を削除
        content = content.trim();

        // 改行をすべて統一
        this.content = new Text(com.plucial.gae.global.utils.StringUtil.unityIndention(content));
    }
}
