package com.plucial.mulcms.service.form;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FormServiceTest extends AppEngineTestCase {

    private FormService service = new FormService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
