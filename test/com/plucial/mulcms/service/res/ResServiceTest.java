package com.plucial.mulcms.service.res;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ResServiceTest extends AppEngineTestCase {

    private ResService service = new ResService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
