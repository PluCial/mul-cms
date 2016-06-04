package com.plucial.mulcms.exception;

public class NoSignedException extends Exception {

    private static final long serialVersionUID = 1L;
    
    private static final String message = "ログインしていません";
    
    /**
     * コンストラクタ
     */
    public NoSignedException() {
        super(message);
    }
    
    /**
     * コンストラクタ
     */
    public NoSignedException(String message) {
        super(message);
    }

}
