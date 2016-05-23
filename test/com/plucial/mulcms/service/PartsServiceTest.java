package com.plucial.mulcms.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.slim3.tester.AppEngineTestCase;

import com.plucial.mulcms.service.assets.WidgetService;

public class PartsServiceTest extends AppEngineTestCase {

    private WidgetService service = new WidgetService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
