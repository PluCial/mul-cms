package com.plucial.mulcms.service;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class RenderingServiceTest extends AppEngineTestCase {

    private RenderingService service = new RenderingService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
