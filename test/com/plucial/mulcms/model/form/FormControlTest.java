package com.plucial.mulcms.model.form;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FormControlTest extends AppEngineTestCase {

    private FormControl model = new FormControl();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}