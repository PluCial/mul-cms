package com.plucial.mulcms.service.widgets.form;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.jsoup.nodes.Document;

import com.google.appengine.api.datastore.Email;
import com.plucial.gae.global.exception.TransException;
import com.plucial.gae.global.utils.StringUtil;
import com.plucial.global.Lang;
import com.plucial.mulcms.dao.widgets.form.ReceptionMailActionDao;
import com.plucial.mulcms.model.widgets.form.Form;
import com.plucial.mulcms.model.widgets.form.FormControl;
import com.plucial.mulcms.model.widgets.form.ReceptionMailAction;
import com.plucial.mulcms.service.GoogleTransService;


public class ReceptionMailActionService extends MailActionService {
    
    /** DAO */
    private static final ReceptionMailActionDao dao = new ReceptionMailActionDao();
    
    /**
     * 追加
     * @param form
     * @param adminEmail
     * @param template
     */
    public static void add(Form form, String sendEmail) {
        ReceptionMailAction model = new ReceptionMailAction();
        model.setKey(createKey());
        model.getFormRef().setModel(form);
        model.setSendEmail(new Email(sendEmail));
        
        // 保存
        dao.put(model);
    }
    
    /**
     * メールBodyの取得
     * @param userLang
     * @param mailSrcLang
     * @param controlList
     * @param userLocaleProp
     * @param googleApiPublicServerKey
     * @param googleApiApplicationName
     * @return
     */
    public static String getMailBody(Lang userLang, Lang mailSrcLang, List<FormControl> controlList, Properties userLocaleProp, String googleApiPublicServerKey, String googleApiApplicationName) {
        StringBuilder body = new StringBuilder();
        // 翻訳必要なコントローラーリスト
        List<FormControl> transSrcControlList = new ArrayList<FormControl>();
        
        
        // オリジナルコンテンツの生成
        for(FormControl cl: controlList) {
            body.append("[" + cl.getControlName() + "]");
            body.append("\n");
            body.append(cl.getPostValue());
            body.append("\n\n");
            
            // 翻訳対象に追加
            if(cl.isTransFlg()) {
                transSrcControlList.add(cl);
            }
        }
                
        // 翻訳コンテンツの生成
        if(userLang != mailSrcLang && transSrcControlList.size() > 0) { 
            body.append("\n");
            body.append("-------------------------------------------------");
            body.append("\n");
            body.append(userLocaleProp.getProperty("lang." + mailSrcLang.toString()) + " -> " + userLang.getName());
            body.append("\n");
            body.append("-------------------------------------------------");
            body.append("\n");
            try {
                Document transResult = getTranslatedMailBody(userLang, mailSrcLang, transSrcControlList, googleApiPublicServerKey, googleApiApplicationName);
                
                // 翻訳したテキストリソースを追加
                for(FormControl cl: transSrcControlList) {
                    // 改行が含まれるため、text()ではなくhtml()で取得する
                    String translatedPostValue = transResult.getElementById(cl.getKey().getName()).html();
                    
                    // getElementById から取得した値に余計な改行が含まれるため、一度手動で除去してからhtml改行をtext改行に置き換える
                    String strTmp = StringUtil.clearTextIndention(translatedPostValue);
                    String content = StringUtil.changeBrToTextIndention(strTmp);
                    
                    body.append("[" + cl.getControlName() + "]");
                    body.append("\n");
                    body.append(content);
                    body.append("\n\n");
                }
                
            } catch (Exception e) {
                return new String(body);
            }
        }
        
        return new String(body);
    }
    
    /**
     * 翻訳
     * @param userLang
     * @param mailSrcLang
     * @param transSrcControlList
     * @param googleApiPublicServerKey
     * @param googleApiApplicationName
     * @return
     * @throws IOException 
     * @throws TransException 
     */
    public static Document getTranslatedMailBody(Lang userLang, Lang mailSrcLang, List<FormControl> transSrcControlList, String googleApiPublicServerKey, String googleApiApplicationName) throws TransException, IOException {
        
        Document transResult = null;
        if(transSrcControlList.size() > 0) {
            GoogleTransService googleTransService = 
                    new GoogleTransService(googleApiPublicServerKey, googleApiApplicationName);
            transResult = googleTransService.mailTrans(mailSrcLang, userLang, transSrcControlList);
        }
        
        return transResult;
    }

}
