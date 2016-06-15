package com.plucial.mulcms.dao.widgets;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class WidgetDaoTest extends AppEngineTestCase {

    private WidgetDao dao = new WidgetDao();

    @Test
    public void test() throws Exception {
        assertThat(dao, is(notNullValue()));
    }
}
