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
import com.plucial.mulcms.dao.res.ResDao;
import com.plucial.mulcms.enums.MulAttrType;
import com.plucial.mulcms.enums.RenderingType;
import com.plucial.mulcms.enums.ResDataType;
import com.plucial.mulcms.enums.ResScope;
import com.plucial.mulcms.meta.res.ResMeta;
import com.plucial.mulcms.model.Assets;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.res.Res;


public class ResService {
    
    /** DAO */
    private static final ResDao dao = new ResDao();
    
    /**
     * リソースの取得
     * @param resId
     * @param lang
     * @return
     * @throws ObjectNotExistException
     */
    public static Res get(String keyString) throws ObjectNotExistException {
        Res model =  dao.getOrNull(createKey(keyString));
        if(model == null) throw new ObjectNotExistException();
        return model;
    }
    
    /**
     * Assets の全リソースリストを取得
     * @param assets
     * @param lang
     * @return
     */
    public static List<Res> getAssetsAllResList(Assets assets, Lang lang) {
        List<Res> textResList = new ArrayList<Res>();
        
        textResList.addAll(AppResService.getList());
        textResList.addAll(AssetsResService.getList(assets));
        textResList.addAll(AppLangResService.getList(lang));
        textResList.addAll(AssetsLangResService.getList(assets, lang));
        
        return textResList;
    }
    
    /**
     * 翻訳対象リストの取得
     * @param assets
     * @param lang
     * @return
     */
    public static List<Res> getAssetsTransResList(Assets assets, Lang lang) {
        List<Res> textResList = new ArrayList<Res>();
        
        textResList.addAll(AppLangResService.getTransSrcList(lang));
        textResList.addAll(AssetsLangResService.getTransSrcList(assets, lang));
        
        return textResList;
    }
    
    /**
     * Page リソースの取得
     * @param page
     * @return
     */
    public static List<Res> getPageResList(Page page) {
        List<Res> textResList = new ArrayList<Res>();
        
        textResList.addAll(AssetsResService.getList(page));
        for(Lang lang: page.getLangList()) {
        textResList.addAll(AssetsLangResService.getList(page, lang));
        }
        
        return textResList;
    }
    
    /**
     * 新しいリソースの初期化
     * @param model
     * @param resId
     * @param cssQuery
     * @param resDataType
     * @param renderingType
     * @param value
     */
    public static void initNewResModel(Res model, String resId, String cssQuery, ResDataType resDataType, RenderingType renderingType, String value) {
        model.setKey(createKey());
        model.setResId(resId);
        model.setCssQuery(cssQuery);
        model.setResDataType(resDataType);
        model.setRenderingType(renderingType);
        model.setStringToValue(value);
    }
    
    /**
     * テキストリソースの追加
     * @param assets
     * @param lang
     * @param doc
     */
    public static void addResByDoc(Assets assets, Lang lang, Document doc) {
        Transaction tx = Datastore.beginTransaction();
        try {
            addResByDoc(tx, assets, lang, doc);
            
            tx.commit();
        }finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }
    }
    
    /**
     * テキストリソースの追加
     * @param tx
     * @param page
     * @param lang
     * @param doc
     */
    public static void addResByDoc(Transaction tx, Assets assets, Lang lang, Document doc) {

        Elements appTexts = doc.select("[" + MulAttrType.mulTextId.getAttr() + "]");
        for(Element elem: appTexts) {
            // Res Id
            String resId = elem.attr(MulAttrType.mulTextId.getAttr());
            // Res Data TYpe
            ResDataType resDataType = elem.hasAttr(MulAttrType.mulLongText.getAttr()) ? ResDataType.LONG_TEXT :ResDataType.SHORT_TEXT;
            
            // Res スコープ
            ResScope resScope = ResScope.assets;
            try {
                if(elem.hasAttr(MulAttrType.mulTextScope.getAttr())) resScope = ResScope.valueOf(elem.attr(MulAttrType.mulTextScope.getAttr()));
            }catch(Exception e){}
            
            // ------------------------------------------------------
            // Add
            // ------------------------------------------------------
            if(resScope == ResScope.app) {
                try {
                    AppResService.get(resId);
                } catch (ObjectNotExistException e) {
                    AppResService.add(tx, resId, "[" + MulAttrType.mulTextId.getAttr() + "=" + resId + "]", resDataType, RenderingType.text, elem.text());
                }

            }else if(resScope == ResScope.app_lang) {
                try {
                    AppLangResService.get(resId, lang);
                } catch (ObjectNotExistException e) {
                    AppLangResService.add(tx, resId, "[" + MulAttrType.mulTextId.getAttr() + "=" + resId + "]", resDataType, RenderingType.text, elem.text(), lang); 
                }

            }else if(resScope == ResScope.assets) {
                try {
                    AssetsResService.get(resId, assets);
                } catch (ObjectNotExistException e) {
                    AssetsResService.add(tx, resId, "[" + MulAttrType.mulTextId.getAttr() + "=" + resId + "]", resDataType, RenderingType.text, elem.text(), assets);
                }

            }else if(resScope == ResScope.assets_lang) {
                try {
                    AssetsLangResService.get(resId, assets, lang);
                } catch (ObjectNotExistException e) {
                    AssetsLangResService.add(tx, resId, "[" + MulAttrType.mulTextId.getAttr() + "=" + resId + "]", resDataType, RenderingType.text, elem.text(), assets, lang);
                }

            }
            
        }
    }
    
    /**
     * 更新
     * @param model
     */
    public static void update(Res model) {
        dao.put(model);
    }
    
    /**
     * 更新
     * @param model
     */
    public static void update(Transaction tx, Res model) {
        Datastore.put(tx, model);
    }
    
    /**
     * 削除
     * @param tx
     * @param model
     */
    public static void delete(Transaction tx, Res model) {
        Datastore.delete(tx, model.getKey());
    }
    
    /**
     * 削除
     * @param model
     */
    public static void delete(Res model) {
        dao.delete(model.getKey());
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
     * @return
     */
    protected static Key createKey() {
        UUID uuid = UUID.randomUUID();
        return Datastore.createKey(ResMeta.get(), uuid.toString());
    }

}
