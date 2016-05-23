package com.plucial.mulcms.service.assets;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AssetsServiceTest extends AppEngineTestCase {

    private AssetsService service = new AssetsService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
