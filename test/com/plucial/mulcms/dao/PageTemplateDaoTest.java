package com.plucial.mulcms.dao;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class PageTemplateDaoTest extends AppEngineTestCase {

    private PageTemplateDao dao = new PageTemplateDao();

    @Test
    public void test() throws Exception {
        assertThat(dao, is(notNullValue()));
    }
}
