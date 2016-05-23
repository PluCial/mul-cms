package com.plucial.mulcms.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.jsoup.nodes.Document;
import org.junit.Test;
import org.slim3.tester.AppEngineTestCase;

import com.plucial.mulcms.enums.RenderingAction;

public class JsoupServiceTest extends AppEngineTestCase {

    private JsoupService service = new JsoupService("<html><head><title>First parse</title></head><body>"
        
        + "</body></html>");

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
        
        Document doc = service.renderingHTML("body", "<p id='aaa'>append</p>", RenderingAction.append);
        service.addAttr("body > p", "id", "bbb");
        System.out.println(doc.outerHtml());
    }
}
