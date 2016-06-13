package com.plucial.mulcms.service.res;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slim3.datastore.Datastore;
import org.slim3.util.StringUtil;

import com.google.appengine.api.datastore.Transaction;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.global.Lang;
import com.plucial.mulcms.dao.res.ResDao;
import com.plucial.mulcms.enums.MulAttrType;
import com.plucial.mulcms.enums.RenderingType;
import com.plucial.mulcms.model.assets.Assets;
import com.plucial.mulcms.model.res.InnerTextRes;
import com.plucial.mulcms.model.res.Res;
import com.plucial.mulcms.service.RenderingService;


public class ResService extends RenderingService {

    /** DAO */
    private static final ResDao dao = new ResDao();
    
    /**
     * リストの取得
     * @param assets
     * @param lang
     * @return
     */
    public static List<? extends Res> getList(Assets assets, Lang lang) {
        return dao.getList(assets, lang);
    }

    /**
     * テキストリソースの追加
     * @param assets
     * @param lang
     * @param doc
     */
    public static void importByDoc(Assets assets, Lang lang, Document doc) {
        Transaction tx = Datastore.beginTransaction();
        try {
            importByDoc(tx, assets, lang, doc);

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
    public static void importByDoc(Transaction tx, Assets assets, Lang lang, Document doc) {

        Elements elems = doc.select("[" + MulAttrType.resId.getAttr() + "]");
        for(Element elem: elems) {
            // Res Id
            String resId = elem.attr(MulAttrType.resId.getAttr());

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

            // ------------------------------------------------------
            // リソースの追加 or 更新
            // ------------------------------------------------------
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
                String cssQuery = elem.nodeName() + "[" + MulAttrType.resId.getAttr() + "=" + resId + "]";
                
                if(renderingType == RenderingType.text || renderingType == RenderingType.long_text) {
                    
                    // ------------------------------------------------------
                    // inner Res
                    // ------------------------------------------------------
                    String value = elem.text();
                    boolean editMode = false;
                    if(elem.hasAttr(MulAttrType.edit.getAttr())) editMode = true;
                    boolean isLongText = renderingType == RenderingType.long_text;
                    
                    try {
                        // 重複チェック & 更新
                        InnerTextRes model = InnerTextResService.get(assets, cssQuery, lang);
                        model.setEditMode(editMode);
                        model.setLongText(isLongText);
                        ResService.update(tx, model);
                        
                    } catch (ObjectNotExistException e) {
                        // 追加
                        InnerTextResService.add(tx, assets, cssQuery, lang, value, editMode, isLongText);
                    }


                }else {
                    // ------------------------------------------------------
                    // Attr Res
                    // ------------------------------------------------------
                    // 指定した属性が存在しない場合は次の処理へ
                    if(!elem.hasAttr(rendering)) continue;
                    // 指定した属性の値が存在しない場合は次の処理へ
                    if(StringUtil.isEmpty(elem.attr(rendering))) continue;

                    String attr = rendering;
                    String attrValue = elem.attr(rendering);
                    
                    try {
                        // 重複チェック
                        AttrResService.get(assets, lang, cssQuery, attr);
                        
                    } catch (ObjectNotExistException e) {
                        // 追加
                        AttrResService.add(tx, assets, cssQuery, lang, attr, attrValue);
                    }
                    
                }

            }
        }
    }

    

}
