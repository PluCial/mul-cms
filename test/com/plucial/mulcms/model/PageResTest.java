package com.plucial.mulcms.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import com.plucial.mulcms.model.res.AssetsRes;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class PageResTest extends AppEngineTestCase {

    private AssetsRes model = new AssetsRes();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
