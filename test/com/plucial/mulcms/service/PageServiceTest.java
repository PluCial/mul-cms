package com.plucial.mulcms.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.slim3.tester.AppEngineTestCase;

import com.plucial.global.Lang;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.PageTemplate;
import com.plucial.mulcms.model.WidgetTemplate;
import com.plucial.mulcms.service.assets.PageService;
import com.plucial.mulcms.service.assets.WidgetService;
import com.plucial.mulcms.service.template.PageTemplateService;
import com.plucial.mulcms.service.template.WidgetTemplateService;

public class PageServiceTest extends AppEngineTestCase {

    private PageService service = new PageService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
        
        PageTemplate pageTemp = PageTemplateService.put("TOP", new JsoupService("<html><head><title>First parse</title></head><body></body></html>").getDoc());
        
        WidgetTemplate partsTemp1 = WidgetTemplateService.put("about Section", "<div class='about'><p>about</p></div>", "body");
        
        Page index = PageService.put("index", pageTemp);
        WidgetService.put(index, partsTemp1);
        
        System.out.println(PageService.getHtml("index", Lang.ja));
    }
}
