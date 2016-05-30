package com.plucial.mulcms.service.res;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.global.Lang;
import com.plucial.mulcms.dao.TextResDao;
import com.plucial.mulcms.enums.MulAttrType;
import com.plucial.mulcms.enums.RenderingType;
import com.plucial.mulcms.meta.ResMeta;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.TextRes;


public class TextResService extends ResService {
    
    /** DAO */
    private static final TextResDao dao = new TextResDao();
    
    /**
     * リソースの取得
     * @param resId
     * @param lang
     * @return
     * @throws ObjectNotExistException
     */
    public static TextRes get(String keyString) throws ObjectNotExistException {
        TextRes model =  dao.getOrNull(createKey(keyString));
        if(model == null) throw new ObjectNotExistException();
        return model;
    }
    
    /**
     * リソースの取得
     * @param resId
     * @param lang
     * @return
     * @throws ObjectNotExistException
     */
    public static TextRes get(String resId, Lang lang) throws ObjectNotExistException {
        TextRes model =  dao.getOrNull(createKey(resId, lang));
        if(model == null) throw new ObjectNotExistException();
        return model;
    }
    
    /**
     * App リソースリストを取得
     * @param lang
     * @return
     */
    public static List<TextRes> getAppResList(Lang lang) {
        return dao.getAppResList(lang);
    }
    
    /**
     * Page リソースリストを取得
     * @param page
     * @param lang
     * @return
     */
    public static List<TextRes> getPageResList(Page page, Lang lang) {
        return dao.getPageResList(page, lang);
    }
    
    /**
     * App Res List + Page Res List
     * @param page
     * @param lang
     * @return
     */
    public static List<TextRes> getPageAllResList(Page page, Lang lang) {
        List<TextRes> textResList = new ArrayList<TextRes>();
        textResList.addAll(getAppResList(lang));
        textResList.addAll(getPageResList(page, lang));
        
        return textResList;
    } 
    
    /**
     * テキストリソースの追加
     * @param tx
     * @param page
     * @param lang
     * @param doc
     */
    public static void addTextResByPage(Transaction tx, Page page, Lang lang, Document doc) {

        // ------------------------------------------------------
        // App Text Res
        // ------------------------------------------------------
        Elements appTexts = doc.select("[" + MulAttrType.mulAppTextId.getAttr() + "]");
        for(Element elem: appTexts) {
            // 存在しない場合のみ追加
            try {
                TextResService.get(elem.attr(MulAttrType.mulAppTextId.getAttr()), lang);
            } catch (ObjectNotExistException e) {
                TextResService.put(tx, elem.attr(MulAttrType.mulAppTextId.getAttr()), lang, elem.text());
            }
            
        }
        
        // ------------------------------------------------------
        // Page Text Res
        // ------------------------------------------------------
        Elements pageTexts = doc.select("[" + MulAttrType.mulPageText.getAttr() + "]");
        for(Element elem: pageTexts) {
            // App Scope Text Res
            TextRes textRes = TextResService.put(tx, page, lang, elem.text());
            elem.attr(MulAttrType.mulPageTextId.getAttr(), textRes.getResId());
        }
    }
    
    /**
     * 追加(Scope: App)
     * @param resId
     * @param lang
     * @param content
     * @return
     */
    public static TextRes put(String resId, Lang lang, String content) {

        TextRes model = null;
        Transaction tx = Datastore.beginTransaction();
        try {
            model = put(tx, resId, lang, content);
            tx.commit();
        }finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }
        
        return model;
    }
    
    /**
     * 追加(Scope: App)
     * @param tx
     * @param resId
     * @param lang
     * @param content
     * @return
     */
    public static TextRes put(Transaction tx, String resId, Lang lang, String content) {
        TextRes model = new TextRes();
        model.setKey(createKey(resId, lang));
        model.setLang(lang);
        model.setResId(resId);
        model.setAppScope(true);
        model.setStringToContent(content);
        
        model.setCssQuery("[" + MulAttrType.mulAppTextId.getAttr() + "=" + resId + "]");
        model.setRenderingType(RenderingType.text);
        
        // 保存
        Datastore.put(tx, model);
        
        return model;
    }
    
    /**
     * 追加(Scope: Page)
     * @param page
     * @param keyString
     * @param lang
     * @param content
     * @return
     */
    public static TextRes put(Page page, Lang lang, String content) {

        TextRes model = null;
        Transaction tx = Datastore.beginTransaction();
        try {
            model = put(tx, page, lang, content);
            tx.commit();
        }finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }
        
        return model;
    }
    
    /**
     * 追加(Scope: Page)
     * @param tx
     * @param page
     * @param keyString
     * @param lang
     * @param content
     * @return
     */
    public static TextRes put(Transaction tx, String resId, Page page, Lang lang, String content) {
        TextRes model = new TextRes();
        
        model.setKey(createKey(resId.toString(), lang));
        model.setLang(lang);
        model.setResId(resId);
        model.setStringToContent(content);
        model.getAssetsRef().setModel(page);
        
        model.setCssQuery("[" + MulAttrType.mulPageTextId.getAttr() + "=" + resId + "]");
        model.setRenderingType(RenderingType.text);
        
        // 保存
        Datastore.put(tx, model);
        
        return model;
    }
    
    /**
     * 追加(Scope: Page)
     * @param tx
     * @param page
     * @param keyString
     * @param lang
     * @param content
     * @return
     */
    public static TextRes put(Transaction tx, Page page, Lang lang, String content) {
        TextRes model = new TextRes();
        // キーを乱数にする
        UUID resId = UUID.randomUUID();
        
        model.setKey(createKey(resId.toString(), lang));
        model.setLang(lang);
        model.setResId(resId.toString());
        model.setStringToContent(content);
        model.getAssetsRef().setModel(page);
        
        model.setCssQuery("[" + MulAttrType.mulPageTextId.getAttr() + "=" + resId + "]");
        model.setRenderingType(RenderingType.text);
        
        // 保存
        Datastore.put(tx, model);
        
        return model;
    }
    
    /**
     * 更新
     * @param model
     */
    public static void update(TextRes model) {
        dao.put(model);
    }
    
    /**
     * 削除
     * @param tx
     * @param model
     */
    public static void delete(Transaction tx, TextRes model) {
        Datastore.delete(tx, model.getKey());
    }
    
    /**
     * 削除
     * @param model
     */
    public static void delete(TextRes model) {
        dao.delete(model.getKey());
    }
    
    /**
     * リソースの初期化
     * @param page
     */
    public static void initialize(Transaction tx, Page page, Lang lang, Document templateDoc) {
        List<TextRes> list = getPageResList(page, lang);
        
        // 削除
        for(TextRes model: list) {
            Datastore.delete(model.getKey());
        }
        
        // 登録
        addTextResByPage(tx, page, lang, templateDoc);
    }
    
    // ----------------------------------------------------------------------
    // キーの作成
    // ----------------------------------------------------------------------
    /**
     * キーの作成
     * @param keyString
     * @return
     */
    protected static Key createKey(String keyString) {
        return Datastore.createKey(ResMeta.get(), keyString);
    }
    
    /**
     * キーの作成
     * @param keyString
     * @return
     */
    protected static Key createKey(String keyString, Lang lang) {
        return Datastore.createKey(ResMeta.get(), keyString + "_" + lang.toString());
    }

}
