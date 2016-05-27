package com.plucial.mulcms.exception;

/**
 * データ重複エラー
 * @author takahara
 *
 */
public class DataMismatchException extends Exception {

    private static final long serialVersionUID = 1L;
    
    private static final String message = "重複しています。";
    
    /**
     * コンストラクタ
     */
    public DataMismatchException() {
        super(message);
    }
    
    /**
     * コンストラクタ
     */
    public DataMismatchException(String message) {
        super(message);
    }

}
