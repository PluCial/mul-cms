package com.plucial.mulcms.controller.mulcms.page;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class SettingControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/mulcms/page/setting");
        SettingController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/mulcms/page/setting.jsp"));
    }
}
