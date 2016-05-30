package com.plucial.mulcms;

import com.plucial.global.Lang;


public class App {
    
    /** アプリケーション名 */
    public static final String APP_DISPLAY_NAME = "MulCMS";
    
    /**
     * 本番ドメイン
     */
    public static final String APP_PRODUCTION_SCHEME = "https://";
    
    /**
     * 本番ドメイン
     */
    public static final String APP_PRODUCTION_DOMAIN = "guidebooq.com";
    
    /**
     * 運用会社名
     */
    public static final String MANAGEMENT_COMPANY_NAME = "Plucial,Inc.";
    
    /**
     * 運用会社名
     */
    public static final String MANAGEMENT_COMPANY_URL = "http://inc.plucial.com";
    
    /**
     * 運用会社名
     */
    public static final String MANAGEMENT_COMPANY_NAME_JP = "株式会社プラシャル";
    
    /** サンプルスポットID */
    public static final String SAMPLE_SPOT_ID = "bases";
    
    /**
     * サービスのベース言語
     */
    public static final Lang APP_BASE_LANG = Lang.en;
    
    /** メール送信元アドレス */
    public static final String EMAIL_FROM_ADDRESS = "info@guidebooq.com";

    
    /** お問い合わせメール送信先アドレス */
    public static final String EMAIL_CONTACT_TO_ADDRESS = "info@plucial.com";

    
    // ---------------------------------------------------------------------------
    // GOOGLE PROJECT 定数(本番)
    // ---------------------------------------------------------------------------
    /** アプリケーション名(Google API 用) */
    public static final String GOOGLE_API_APPLICATION_NAME = "mul-cms";
    
    /** サーバー API キー(Translate API, Map Geocoding API) */
    public static final String GOOGLE_API_PUBLIC_SERVER_KEY = "AIzaSyCPDuS7Uox7OjO6x-4bxQOZDQfUxKkWiSs";
    
    /** ウェブ アプリケーション クライアントID */
    public static final String GOOGLE_PROJECT_CLIENT_ID = "933222271716-10ckt8mill862e9kb3oq4nlji4tsvo8q.apps.googleusercontent.com";
    
    /** ウェブ アプリケーション クライアント シークレット */
    public static final String GOOGLE_PROJECT_CLIENT_SECRET = "qLzByetMsFXjzCAaqL0lO3Ln";
    
    /** 管理Email */
    public static final String PROJECT_ADMIN_ACCOUNT_EMAIL = "info@guidebooq.com";
    
    /** Google プロジェクトスコープ */
    public static final String GOOGLE_LOGIN_OAUTH_SCOPE = "https://www.googleapis.com/auth/userinfo.email";
    
    /** Google OAuthログインコールバックURI */
    public static final String GOOGLE_OAUTH_LOGIN_CALLBACK_URI = "/user/auth/google/callback";
    
    /** クラウドストレージパケット名 */
    public static final String GCS_BUCKET_NAME = "guidebooq-spot-images";
    
    // ---------------------------------------------------------------------------
    // GOOGLE PROJECT 定数(ステージング)
    // ---------------------------------------------------------------------------
    /** アプリケーション名(Google API 用) */
    public static final String GOOGLE_API_APPLICATION_NAME_STAGING = "guidebooq-staging";
    
    /** 翻訳APIの公開キー */
    public static final String GOOGLE_API_PUBLIC_SERVER_KEY_STAGING = "AIzaSyBjd4YrXnr7DyOCHN7WL0VIQ5uHo2MStg8";
    
    /** クライアントID */
    public static final String GOOGLE_PROJECT_CLIENT_ID_STAGING = "143609569366-jvm5kdkgcfjf2nsr8okipe6du1qm5jrs.apps.googleusercontent.com";
    
    /** クライアントシークレット */
    public static final String GOOGLE_PROJECT_CLIENT_SECRET_STAGING = "OaIkJn_sQjjMGucZXV5Wub9N";
    
    /** 管理Email */
    public static final String PROJECT_ADMIN_ACCOUNT_EMAIL_STAGING = "info@guidebooq.com";
    
    /** クラウドストレージパケット名 */
    public static final String GCS_BUCKET_NAME_STAGING = "guidebooq-spot-images-staging";
    
    
    // ---------------------------------------------------------------------------
    // FACEBOOK PROJECT 定数
    // ---------------------------------------------------------------------------
    /** クライアントID */
    public static final String FACEBOOK_PROJECT_CLIENT_ID = "1128230400534387";
    
    /** クライアントシークレット */
    public static final String FACEBOOK_PROJECT_CLIENT_SECRET = "49efe5202f8748b9641a9be0afdf190e";
    
    /** FaceBook OAuthログインコールバックURI */
    public static final String FACEBOOK_OAUTH_LOGIN_CALLBACK_URI = "/user/auth/facebook/callback";
    
    /** FaceBook App API PERMISSIONS */
    public static final String FACEBOOK_LOGIN_OAUTH_PERMISSIONS = "email,public_profile";
    
    
    /** Test環境 クライアントID */
//    public static final String FACEBOOK_PROJECT_CLIENT_ID = "1675899719355044";
    
    /** Test環境 クライアントシークレット */
//    public static final String FACEBOOK_PROJECT_CLIENT_SECRET = "d018e64e06b4173df93116fed024021b";
    
    
    
    
    // ---------------------------------------------------
    // Paypal(本番)
    // ---------------------------------------------------
    public static final String PAYPAL_EXPRESS_CHECKOUT_USER = "info_api1.plucial.com";
    
    public static final String PAYPAL_EXPRESS_CHECKOUT_PWD = "F3KBVPLAUR8UZVKR";
    
    public static final String PAYPAL_EXPRESS_CHECKOUT_SIGNATURE = "AFcWxV21C7fd0v3bYYYRCpSSRl31A5GaIAS0fzV2QEZP.mRbZyCu17AE";
    
    // ---------------------------------------------------
    // Paypal(SandBox)
    // ---------------------------------------------------
    public static final String PAYPAL_EXPRESS_CHECKOUT_SANDBOX_USER = "info_api1.plucial.com";
    
    public static final String PAYPAL_EXPRESS_CHECKOUT_SANDBOX_PWD = "XFYX8HMWPUVQBA8H";
    
    public static final String PAYPAL_EXPRESS_CHECKOUT_SANDBOX_SIGNATURE = "AiPC9BjkCyDFQXbSkoZcgqH3hpacAW563g9VXnaHQqZwH60nhoycdVkv";
    
    
    // ---------------------------------------------------
    // 画面表示
    // ---------------------------------------------------
    public static final int SPOT_LIST_LIMIT = 10;
    
    public static final int KEYWORD_SEARCH_SPOT_LIST_LIMIT = 9;
    
    public static final int SPOT_ITEM_LIST_LIMIT = 6;
    
    public static final int TRANS_HISTORY_LIST_LIMIT = 20;
    
    // ---------------------------------------------------
    // 登録制限
    // ---------------------------------------------------
    public static final int CONTENTS_CATCH_COPY_STRING_SIZE = 200;
    // 登録できるハウツーステップ数
    public static final int HOWTO_STEP_COUNT_LIMIT = 10;

}
