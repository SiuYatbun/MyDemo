package com.example.mydemo;

import com.example.mydemo.common.util.PropertiesUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

@SpringBootTest
class MyTest {
    @Value("${lsq}")
    String lsq;

    @Test
    void test() throws IOException {
        //        Date startDate = DateUtil.parse("20220505", "yyyyMMdd");
//        Date endDate = DateUtil.parse("20220506", "yyyyMMdd");
//        String to = "yatbun86606212@outlook.com";
//        String subject = "标题";
//        String content = "Hello,world.";
//        MeetingData meetingData = new MeetingData();
//        meetingData.setTo(to);
//        meetingData.setStartTime(DateUtil.format(startDate, "yyyyMMdd'T'HHmmss"));
//        meetingData.setEndTime(DateUtil.format(endDate, "yyyyMMdd'T'HHmmss"));
//        meetingData.setLocation("会议室");
//        MailUtil.sendMeetingMail(to, subject, content, meetingData);

        System.out.println(lsq);
        Properties properties = PropertiesUtil.getProperties(new ClassPathResource("mail-dev.setting").getFile());
        System.out.println(properties);
    }

}
