package com.plucial.mulcms.service.assets;

import java.util.List;

import org.jsoup.nodes.Document;

import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.global.Lang;
import com.plucial.mulcms.dao.PageDao;
import com.plucial.mulcms.enums.RenderingAction;
import com.plucial.mulcms.exception.TooManyException;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.PageTemplate;
import com.plucial.mulcms.model.Widget;
import com.plucial.mulcms.service.JsoupService;


public class PageService extends AssetsService {
    
    /** DAO */
    private static final PageDao dao = new PageDao();
    
    /**
     * 追加
     * @param keyString
     * @param template
     * @param name
     * @return
     * @throws TooManyException 
     */
    public static Page put(String keyString, PageTemplate template) throws TooManyException {
        
        // 重複チェック
        try {
            get(keyString);
            throw new TooManyException();
        } catch (ObjectNotExistException e) {
            // 重複してなければ登録可能
        }
        
        Page model = new Page();
        String html = template.getHtmlString();
        settingNewModel(model, template, html);
        
        model.setKey(createKey(keyString));
        
        dao.put(model);
        
        return model;
    }
    
    /**
     * リストの取得
     * @return
     */
    public static List<Page> getList() {
        return dao.getList();
    }
    
    /**
     * 取得
     * @param keyString
     * @return
     * @throws ObjectNotExistException
     */
    public static Page get(String keyString) throws ObjectNotExistException {
        Page model = dao.getOrNull(createKey(keyString));
        if(model == null) throw new ObjectNotExistException();
        
        return model;
    }
    
    /**
     * HTMLドキュメントの取得
     * @param page
     * @return
     * @throws ObjectNotExistException
     */
    public static Document getHtmlDocument(Page page) throws ObjectNotExistException {
        // Pageテンプレートの取得
        JsoupService jsoupService = new JsoupService(page.getHtmlString());
        
        // Widget List の取得
        List<Widget> widgetList = WidgetService.getList(page);
        for(Widget widget: widgetList) {
            jsoupService.renderingHTML(widget.getCssQuery(), widget.getHtmlString(), RenderingAction.append);
        }
        
        return jsoupService.getDoc();
    }
    
    /**
     * HTML 取得
     * @param keyString
     * @param lang
     * @return
     * @throws ObjectNotExistException
     */
    public static String getHtml(String keyString, Lang lang) throws ObjectNotExistException {
        
        // ページの取得
        Page model = get(keyString);
        
        return getHtmlDocument(model).outerHtml();
    }
    
    /**
     * 更新
     * @param model
     */
    public static void update(Page model) {
        dao.put(model);
    }

}
