package com.plucial.mulcms.service.form;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ReceptionMailServiceTest extends AppEngineTestCase {

    private ReceptionMailActionService service = new ReceptionMailActionService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
