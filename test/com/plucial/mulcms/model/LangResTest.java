package com.plucial.mulcms.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import com.plucial.mulcms.model.res.AppLangRes;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class LangResTest extends AppEngineTestCase {

    private AppLangRes model = new AppLangRes();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
