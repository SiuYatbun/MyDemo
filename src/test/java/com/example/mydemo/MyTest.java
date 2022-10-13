package com.example.mydemo;

import com.example.mydemo.common.util.MailUtil;
import org.junit.jupiter.api.Test;

public class MyTest {
    @Test
    void send_mail() {
        String to = MailUtil.PROPS.getProperty("receiver");
        String subject = "标题";
        String content = "Hello,world.";
        MailUtil.sendCommonMail(to, subject, content);
    }

    @Test
    public void test() {

    }
}
