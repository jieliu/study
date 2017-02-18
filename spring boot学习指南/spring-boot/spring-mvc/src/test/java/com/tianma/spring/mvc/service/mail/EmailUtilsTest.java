package com.tianma.spring.mvc.service.mail;

import com.tianma.spring.mvc.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by egoo on 16-8-16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class EmailUtilsTest {

    @Autowired
    private EmailUtils emailUtils;

    @Test
    public void sendSimpleEmail() throws Exception {
        emailUtils.sendSimpleEmail("835088577@qq.com","fiboliu@163.com", "test", "hello test email!!");
    }

    @Test
    public void sendAttachmentsEmail() throws Exception {
        emailUtils.sendAttachmentsEmail("835088577@qq.com","fiboliu@163.com", "test", "hello test email!!");
    }

    @Test
    public void sendInlineMail() throws Exception {
        emailUtils.sendInlineMail("835088577@qq.com","fiboliu@163.com", "test", "hello test email!!");
    }

}