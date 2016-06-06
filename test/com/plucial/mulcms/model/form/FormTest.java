package com.plucial.mulcms.model.form;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FormTest extends AppEngineTestCase {

    private Form model = new Form();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
