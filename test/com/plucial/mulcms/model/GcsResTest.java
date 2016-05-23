package com.plucial.mulcms.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class GcsResTest extends AppEngineTestCase {

    private ImageRes model = new ImageRes();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
