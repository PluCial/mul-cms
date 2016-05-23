package com.plucial.mulcms.controller.admin.template.page;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;

import com.plucial.mulcms.controller.mulcms.template.page.IndexController;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class IndexControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/admin/template/page/");
        IndexController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/admin/template/page/index.jsp"));
    }
}
