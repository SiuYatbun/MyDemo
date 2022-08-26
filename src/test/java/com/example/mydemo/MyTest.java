package com.example.mydemo;

import com.example.mydemo.common.mail.MeetingData;
import com.example.mydemo.common.util.DateUtil;
import com.example.mydemo.common.util.MailUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;

class MyTest {
    @Test
    void sendMeetingMail() throws IOException {
        Date startDate = DateUtil.parse("20220505", "yyyyMMdd");
        Date endDate = DateUtil.parse("20220506", "yyyyMMdd");
        String to = MailUtil.PROPS.getProperty("receiver");
        String subject = "标题";
        String content = "Hello,world.";
        MeetingData meetingData = new MeetingData();
        meetingData.setTo(to);
        meetingData.setStartTime(DateUtil.format(startDate, "yyyyMMdd'T'HHmmss"));
        meetingData.setEndTime(DateUtil.format(endDate, "yyyyMMdd'T'HHmmss"));
        meetingData.setLocation("会议室");
        MailUtil.sendMeetingMail(to, subject, content, meetingData);

    }

}
