package com.plucial.mulcms.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.slim3.tester.AppEngineTestCase;

import com.plucial.mulcms.service.template.PageTemplateService;
import com.plucial.mulcms.service.template.WidgetTemplateService;
import com.plucial.mulcms.service.template.TemplateService;

public class TemplateServiceTest extends AppEngineTestCase {
    
    @Before 
    public void initialize() {
        PageTemplateService.put("about page", new JsoupService("<html><body></body></html>").getDoc());
        WidgetTemplateService.put("about parts1", "<html><body></body></html>");
        WidgetTemplateService.put("about parts2", "<html><body></body></html>");
    }

    @Test
    public void test() throws Exception {

        // サイズ
        assertThat(TemplateService.getList(), is(notNullValue()));
        assertThat(TemplateService.getList().size(), is(3));
        
        assertThat(PageTemplateService.getList(), is(notNullValue()));
        assertThat(PageTemplateService.getList().size(), is(1));
        
        assertThat(WidgetTemplateService.getList(), is(notNullValue()));
        assertThat(WidgetTemplateService.getList().size(), is(2));
    }
}
