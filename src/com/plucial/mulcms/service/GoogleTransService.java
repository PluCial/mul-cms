package com.plucial.mulcms.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.TranslateRequestInitializer;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;
import com.google.appengine.api.datastore.Transaction;
import com.google.apphosting.api.ApiProxy.ArgumentException;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.gae.global.exception.TransException;
import com.plucial.gae.global.utils.StringUtil;
import com.plucial.global.Lang;
import com.plucial.mulcms.model.assets.Assets;
import com.plucial.mulcms.model.res.InnerRes;
import com.plucial.mulcms.model.res.InnerTextRes;
import com.plucial.mulcms.model.widgets.form.FormControl;
import com.plucial.mulcms.service.res.InnerTextResService;
import com.plucial.mulcms.service.res.ResService;

public class GoogleTransService {
	
	private String googleApiServiceKey;
	private String googleApiAppName;
	
	/**
	 * コンストラクター
	 * @param googleApiServiceKey
	 * @param googleApiAppName
	 */
	public GoogleTransService(String googleApiServiceKey, String googleApiAppName) {
		this.googleApiServiceKey = googleApiServiceKey;
		this.googleApiAppName = googleApiAppName;
	}
	
	/**
	 * 機械翻訳
	 * @param tx
	 * @param page
	 * @param transSrcLang
	 * @param transTargetLang
	 * @param transSrcList
	 * @param updateAll
	 * @throws TransException
	 */
	public void machineTrans(Transaction tx, Assets assets, Lang transSrcLang, Lang transTargetLang, List<? extends InnerRes> transSrcList, boolean transAll) throws TransException {
		try {
            Document transResult = machineTrans(transSrcLang, transTargetLang, transSrcList);
            
            // 翻訳したテキストリソースを追加
            for(InnerRes srcRes: transSrcList) {
                // 改行が含まれるため、text()ではなくhtml()で取得する
                String tcText = transResult.getElementById(srcRes.getKey().getName()).html();

                // getElementById から取得した値に余計な改行が含まれるため、一度手動で除去してからhtml改行をtext改行に置き換える
                String strTmp = StringUtil.clearTextIndention(tcText);
                String content = StringUtil.changeBrToTextIndention(strTmp);

                if(srcRes instanceof InnerTextRes) {
                    
                    // App Lang Res
                    try {
                        InnerTextRes targetRes = InnerTextResService.get(assets, srcRes.getCssQuery(), transTargetLang);
                        
                        // 更新
                        if(transAll) {
                            targetRes.setCssQuery(srcRes.getCssQuery());
                            targetRes.setStringToValue(content);
                            targetRes.setLongText(((InnerTextRes) srcRes).isLongText());
                            targetRes.setEditMode(srcRes.isEditMode());

                            ResService.update(tx, targetRes);
                        }
                        
                    }catch(ObjectNotExistException e) {
                        // 追加
                        InnerTextResService.add(
                            tx, 
                            assets, 
                            srcRes.getCssQuery(), 
                            transTargetLang, 
                            content, 
                            srcRes.isEditMode(), 
                            ((InnerTextRes) srcRes).isLongText());
                    }
                }
            }
            
            
        } catch (Exception e) {
            throw new TransException(e);
        }
	}
    
    /**
     * 機械翻訳
     * @param contents
     * @return
     * @throws ArgumentException 
     * @throws IOException 
     * @throws Exception 
     */
    private Document machineTrans(
    		Lang srcLang, 
    		Lang targetLang,
    		List<? extends InnerRes> transSrcList) throws TransException, IOException {
        
        if(transSrcList == null || transSrcList.size() <= 0) {
            throw new TransException("翻訳するコンテンツはありません。");
        }
        
        // 通常モード
        String transSrc = "";
        
        for(InnerRes tc: transSrcList) {
            String content = tc.getValueString();
            content = StringUtil.changeIndentionToHtml(content); // 改行コードを<br /> に変換して翻訳する

            transSrc = transSrc + "<div id=\"" + tc.getKey().getName() + "\">" + content +  "</div>";
        }
        
        // 翻訳処理
        String transResult = machineTrans(srcLang, targetLang, transSrc);
        
        // HTMLに変換
        return Jsoup.parse(transResult);
    }
    
    /**
     * 機械翻訳
     * @param contents
     * @return
     * @throws ArgumentException 
     * @throws IOException 
     * @throws Exception 
     */
    public Document mailTrans(
            Lang srcLang, 
            Lang targetLang,
            List<FormControl> transSrcList) throws TransException, IOException {
        
        if(transSrcList == null || transSrcList.size() <= 0) {
            throw new TransException("翻訳するコンテンツはありません。");
        }
        
        // 通常モード
        String transSrc = "";
        
        for(FormControl tc: transSrcList) {
            String content = tc.getPostValue();
            content = StringUtil.changeIndentionToHtml(content); // 改行コードを<br /> に変換して翻訳する

            transSrc = transSrc + "<div id=\"" + tc.getKey().getName() + "\">" + content +  "</div>";
        }
        
        // 翻訳処理
        String transResult = machineTrans(srcLang, targetLang, transSrc);
        
        // HTMLに変換
        return Jsoup.parse(transResult);
    }
    
    
    /**
     * Google 翻訳
     * @param transSrc
     * @return
     * @throws IOException 
     */
    public String machineTrans(Lang srcLang, Lang targetLang, String source) throws IOException {
        
        // 翻訳Translate の生成
        Translate translate = new Translate.Builder(new NetHttpTransport(), new JacksonFactory(), null)
        .setGoogleClientRequestInitializer(new TranslateRequestInitializer(googleApiServiceKey))
        .setApplicationName(googleApiAppName)
        .build();
        
        // 翻訳
        List<String> qList = new ArrayList<String>();
        qList.add(source);
        TranslationsListResponse response = translate.translations()
                .list(qList, targetLang.getLangKey())
                .setSource(srcLang.getLangKey())
                .setFormat("html")
                .execute();
        
        // 翻訳結果の取得
        List<TranslationsResource> resourceList = response.getTranslations();
        TranslationsResource resource = resourceList.get(0);
        
        return resource.getTranslatedText();
    }

}
