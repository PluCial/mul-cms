package com.plucial.mulcms.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class PagesetTest extends AppEngineTestCase {

    private Collection model = new Collection();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
