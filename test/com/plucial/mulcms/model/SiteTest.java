package com.plucial.mulcms.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class SiteTest extends AppEngineTestCase {

    private Site model = new Site();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
