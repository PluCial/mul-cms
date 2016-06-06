package com.plucial.mulcms.model.form;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ReceptionMailTest extends AppEngineTestCase {

    private ReceptionMail model = new ReceptionMail();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
