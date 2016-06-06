package com.plucial.mulcms.service.form;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FormActionServiceTest extends AppEngineTestCase {

    private FormActionService service = new FormActionService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
