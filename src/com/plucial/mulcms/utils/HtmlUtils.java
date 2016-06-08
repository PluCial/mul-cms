package com.plucial.mulcms.utils;

public class HtmlUtils {
    
    /**
     * 
     * @param content
     * @return
     */
    public static String getJspDisplayString(String content) {
        if(content == null || content.trim().length() <= 0) return "";
        
        // HTMLエスケープ処理
        String newdata = htmlEscape(content);
        
        return changeIndentionToHtml(newdata);
    }
    
    /**
     * <p>[概 要] HTMLエスケープ処理</p>
     * <p>[詳 細] </p>
     * <p>[備 考] </p>
     * @param  str 文字列
     * @return HTMLエスケープ後の文字列
     */
    public static String htmlEscape(String str){
        StringBuffer result = new StringBuffer();
        for(char c : str.toCharArray()) {
            switch (c) {
            case '&' :
                result.append("&amp;");
                break;
            case '<' :
                result.append("&lt;");
                break;
            case '>' :
                result.append("&gt;");
                break;
            case '"' :
                result.append("&quot;");
                break;
            case '\'' :
                result.append("&#39;");
                break;
//            case ' ' :
//                result.append("&nbsp;");
//                break;
            default :
                result.append(c);
                break;
            }
        }
        return result.toString();
    }
    
    public static String htmlDecode(String str ) {
        if(str==null) {
            return str;
        }
        str = str.replaceAll("&nbsp;" , " " );
        str = str.replaceAll("&quot;", "\"");
        str = str.replaceAll("&gt;"  , ">" );
        str = str.replaceAll("&lt;"  , "<" );
        str = str.replaceAll("&amp;" , "&" );
        return str;
     }
    
    /**
     * 文字列を適切なHTMLに変換
     * @param data
     * @return
     */
    public static String changeIndentionToHtml(String content) {
        if(content == null || content.trim().length() <= 0) return null;
        
        String newContent = content.replaceAll("\\r\\n|\\n\\r|\\r|\\n", "<br />");
        
        return newContent;
    }

}
