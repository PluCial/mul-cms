package com.plucial.mulcms.service.res;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slim3.datastore.Datastore;
import org.slim3.util.StringUtil;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.global.Lang;
import com.plucial.mulcms.dao.res.ResDao;
import com.plucial.mulcms.enums.MulAttrType;
import com.plucial.mulcms.enums.RenderingType;
import com.plucial.mulcms.enums.ResScope;
import com.plucial.mulcms.meta.res.ResMeta;
import com.plucial.mulcms.model.assets.Assets;
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
     * @param isTransTraget
     * @return
     */
    public static List<Res> getAssetsResList(Assets assets, Lang lang, boolean isTransTraget) {
        List<Res> textResList = new ArrayList<Res>();

        textResList.addAll(AppLangResService.getList(lang, isTransTraget));
        textResList.addAll(AssetsLangResService.getList(assets, lang, isTransTraget));

        return textResList;
    }

    /**
     * assets リソースの取得
     * @param page
     * @return
     */
    public static List<Res> getAssetsResList(Assets assets) {
        List<Res> textResList = new ArrayList<Res>();

        textResList.addAll(AssetsResService.getList(assets));
        for(Lang lang: assets.getLangList()) {
            textResList.addAll(AssetsLangResService.getList(assets, lang));
        }

        return textResList;
    }

    /**
     * 新しいリソースの初期化
     * @param model
     * @param resId
     * @param cssQuery
     * @param renderingType
     * @param value
     * @param renderingAttr
     * @param editMode
     */
    public static void initNewResModel(Res model, String resId, String cssQuery, RenderingType renderingType, String value, String renderingAttr, boolean editMode) {
        model.setKey(createKey());
        model.setResId(resId);
        model.setCssQuery(cssQuery);
        model.setRenderingType(renderingType);
        model.setRenderingAttr(renderingAttr);
        model.setStringToValue(value);
        model.setTransTarget(renderingType.isTransTarget());
        model.setEditMode(editMode);
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

        Elements elems = doc.select("[" + MulAttrType.resId.getAttr() + "]");
        for(Element elem: elems) {
            // Res Id
            String resId = elem.attr(MulAttrType.resId.getAttr());

            // ------------------------------------------------------
            // Res スコープ
            // ------------------------------------------------------
            ResScope resScope = ResScope.ASSETS_LANG;
            try {
                if(elem.hasAttr(MulAttrType.scope.getAttr())) resScope = ResScope.valueOf(elem.attr(MulAttrType.scope.getAttr()).toUpperCase());
            }catch(Exception e){}

            // ------------------------------------------------------
            // rendering 属性がなければ以後実行しない。
            // ------------------------------------------------------
            if(!elem.hasAttr(MulAttrType.rendering.getAttr())) continue;

            // ------------------------------------------------------
            // rendering List の取得
            // ------------------------------------------------------
            String renderings = elem.attr(MulAttrType.rendering.getAttr()).toLowerCase();
            List<String> renderingList = Arrays.asList(renderings.split("[\\s]+"));

            // ------------------------------------------------------
            // 重複を削除
            // ------------------------------------------------------
            HashSet<String> renderingHashSet = new HashSet<String>();
            renderingHashSet.addAll(renderingList);

            for(String rendering: renderingHashSet) {

                // ------------------------------------------------------
                // Rendering Typeの取得
                // ------------------------------------------------------
                RenderingType renderingType = null;
                try {
                    renderingType = RenderingType.valueOf(rendering);
                }catch(Exception e){
                    renderingType = RenderingType.attr;
                }

                // ------------------------------------------------------
                // データーの値を取得
                // ------------------------------------------------------
                String value;
                String renderingAttr = null;
                boolean editMode = false;
                
                if(renderingType == RenderingType.text || renderingType == RenderingType.long_text) {
                    value = elem.text();
                    
                    if(elem.hasAttr(MulAttrType.edit.getAttr())) editMode = true;


                }else {
                    // 指定した属性が存在しない場合は次の処理へ
                    if(!elem.hasAttr(rendering)) continue;
                    // 指定した属性の値が存在しない場合は次の処理へ
                    if(StringUtil.isEmpty(elem.attr(rendering))) continue;

                    value = elem.attr(rendering).toLowerCase();
                    renderingAttr = rendering;
                }


                // ------------------------------------------------------
                // Res の追加 or 更新
                // ------------------------------------------------------
                if(resScope == ResScope.APP_LANG) {
                    try {
                        Res model = AppLangResService.get(resId, renderingType, renderingAttr, lang);
                        model.setEditMode(editMode); 
                        ResService.update(tx, model);
                        
                    } catch (ObjectNotExistException e) {
                        AppLangResService.add(tx, resId, "[" + MulAttrType.resId.getAttr() + "=" + resId + "]", renderingType, value, renderingAttr, editMode, lang); 
                    }

                }else if(resScope == ResScope.ASSETS_LANG) {
                    try {
                        Res model = AssetsLangResService.get(resId, assets, renderingType, renderingAttr, lang);
                        model.setEditMode(editMode); 
                        ResService.update(tx, model);
                        
                    } catch (ObjectNotExistException e) {
                        AssetsLangResService.add(tx, resId, "[" + MulAttrType.resId.getAttr() + "=" + resId + "]", renderingType, value, renderingAttr, assets, editMode, lang);
                    }

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
