package com.plucial.mulcms.service.assets;

import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.datastore.Transaction;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.global.Lang;
import com.plucial.mulcms.dao.AssetsDao;
import com.plucial.mulcms.enums.HtmlDataAttrType;
import com.plucial.mulcms.meta.AssetsMeta;
import com.plucial.mulcms.model.Assets;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.Template;
import com.plucial.mulcms.model.TextRes;
import com.plucial.mulcms.service.res.TextResService;


public class AssetsService {
    
    /** DAO */
    private static final AssetsDao dao = new AssetsDao();
    
    /**
     * 新しいモデルの設定
     * @param model
     * @param template
     */
    protected static void settingNewModel(Assets model, Template template, String html) {
        model.getTemplateRef().setModel(template);
        model.setHtml(new Text(html));
    }
    
    /**
     * 取得
     * @param keyString
     * @return
     * @throws ObjectNotExistException
     */
    public static Assets get(String keyString) throws ObjectNotExistException {
        Assets model = dao.getOrNull(createKey(keyString));
        if(model == null) throw new ObjectNotExistException();
        return model;
    }
    
    /**
     * テンプレートの設定
     * @param model
     */
    public static void settingTemplate(Assets model) {
        Template template = (Template)model.getTemplateRef().getModel();
        model.setTemplate(template);
    }
    
    /**
     * 削除
     * @param keyString
     */
    protected static void delete(String keyString) {
        dao.delete(createKey(keyString));
    }
    
    /**
     * テキストリソースの追加
     * @param tx
     * @param page
     * @param lang
     * @param template
     * @return
     */
    protected static Document settingTextRes(Transaction tx, Page page, Lang lang, Template template) {
        Document doc = Jsoup.parseBodyFragment(template.getHtmlString());
        Elements elms = doc.select("[" + HtmlDataAttrType.mulCms.getAttr() + "]");

        for(Element elem: elms) {
            // App Scope Text Res
            if(elem.hasAttr(HtmlDataAttrType.textResId.getAttr())) {
                TextResService.put(tx, elem.attr(HtmlDataAttrType.textResId.getAttr()), lang, elem.text());
                
            }else {
                // App Scope Text Res
                TextRes textRes = TextResService.put(tx, page, lang, elem.text());
                elem.attr(HtmlDataAttrType.textResId.getAttr(), textRes.getResId());
            }
        }
        
        return doc;
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
        return Datastore.createKey(AssetsMeta.get(), keyString);
    }
    
    
    /**
     * キーの作成
     * @return
     */
    protected static Key createKey() {
        // キーを乱数にする
        UUID uniqueKey = UUID.randomUUID();
        return createKey(uniqueKey.toString());
    }

}
