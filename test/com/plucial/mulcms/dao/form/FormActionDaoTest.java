package com.plucial.mulcms.dao.form;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FormActionDaoTest extends AppEngineTestCase {

    private FormActionDao dao = new FormActionDao();

    @Test
    public void test() throws Exception {
        assertThat(dao, is(notNullValue()));
    }
}
