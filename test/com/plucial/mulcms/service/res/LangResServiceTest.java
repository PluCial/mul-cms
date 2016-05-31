package com.plucial.mulcms.service.res;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class LangResServiceTest extends AppEngineTestCase {

    private AppLangResService service = new AppLangResService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
