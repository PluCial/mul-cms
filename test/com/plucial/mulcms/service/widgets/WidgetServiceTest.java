package com.plucial.mulcms.service.widgets;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class WidgetServiceTest extends AppEngineTestCase {

    private WidgetService service = new WidgetService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
