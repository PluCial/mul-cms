package com.plucial.mulcms.controller.admin.template.page;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;

import com.plucial.mulcms.controller.mulcms.template.page.AddEntryController;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AddEntryControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/admin/template/page/addEntry");
        AddEntryController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is(nullValue()));
    }
}
