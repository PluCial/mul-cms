package com.plucial.mulcms.service.form;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FormControlServiceTest extends AppEngineTestCase {

    private FormControlService service = new FormControlService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
