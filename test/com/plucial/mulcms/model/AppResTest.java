package com.plucial.mulcms.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import com.plucial.mulcms.model.res.AppRes;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AppResTest extends AppEngineTestCase {

    private AppRes model = new AppRes();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
