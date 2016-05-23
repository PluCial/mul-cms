package com.plucial.mulcms.controller;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FrontControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/front");
        FrontController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/front.jsp"));
    }
}
