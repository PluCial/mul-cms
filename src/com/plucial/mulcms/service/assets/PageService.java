package com.plucial.mulcms.service.assets;

import java.util.List;

import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.global.Lang;
import com.plucial.mulcms.dao.PageDao;
import com.plucial.mulcms.enums.RenderingAction;
import com.plucial.mulcms.exception.TooManyException;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.PageTemplate;
import com.plucial.mulcms.model.Widget;
import com.plucial.mulcms.model.WidgetTemplate;
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
        settingNewModel(model, template);
        
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
     * HTML 取得
     * @param keyString
     * @param lang
     * @return
     * @throws ObjectNotExistException
     */
    public static String getHtml(String keyString, Lang lang) throws ObjectNotExistException {
        
        // ページの取得
        Page model = dao.getOrNull(createKey(keyString));
        if(model == null) throw new ObjectNotExistException();
        
        // Pageテンプレートの取得
        PageTemplate pageTemp = (PageTemplate)model.getTemplateRef().getModel();
        JsoupService jsoupService = new JsoupService(pageTemp.getContentString());
        
        // Widget List の取得
        List<Widget> widgetList = WidgetService.getList(model);
        for(Widget widget: widgetList) {
            WidgetTemplate widgetTemp = (WidgetTemplate)widget.getTemplateRef().getModel();
            jsoupService.renderingHTML(widgetTemp.getCssQuery(), widgetTemp.getContentString(), RenderingAction.append);
        }
        
        return jsoupService.getDoc().outerHtml();
    }

}
