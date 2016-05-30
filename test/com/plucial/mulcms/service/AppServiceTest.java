package com.plucial.mulcms.service;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AppServiceTest extends AppEngineTestCase {

    private AppService service = new AppService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
