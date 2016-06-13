package com.plucial.mulcms.dao.res;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AssetsLangDaoTest extends AppEngineTestCase {

    private InnerTextResDao dao = new InnerTextResDao();

    @Test
    public void test() throws Exception {
        assertThat(dao, is(notNullValue()));
    }
}
