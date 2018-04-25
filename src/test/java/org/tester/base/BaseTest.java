package org.tester.base;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.tester.config.AppConfig;
import org.tester.page.factory.PageFactory;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class BaseTest {

    @Autowired
    protected PageFactory pageFactory;
}
