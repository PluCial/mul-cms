package com.plucial.mulcms.model.res;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AssetsLangTest extends AppEngineTestCase {

    private AssetsLangRes model = new AssetsLangRes();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}