package com.example.mydemo.common.util;

import com.example.mydemo.common.enums.Ext;
import com.example.mydemo.common.mail.MeetingData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class MailUtil {
    private static final Logger logger = LoggerFactory.getLogger(MailUtil.class);
    private static final String MEETING_TEMPLATE = "meetingInvitationTemplate.txt";
    public static final Properties PROPS;

    static {
        try {
            PROPS = PropertiesUtil.getProperties(new ClassPathResource("mail-dev" + Ext.SETTING).getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendCommonMail(String to, String subject, String content) {
        send(to, subject, content, null);
    }

    public static void sendMeetingMail(String to, String subject, String content, MeetingData meetingData) {
        send(to, subject, content, meetingData);
    }

    private static void send(String to, String subject, String content, MeetingData meetingData) {
        doSend(to, subject, content, meetingData);
    }

    private static void doSend(String to, String subject, String content, MeetingData meetingData) {
        Session session = createSession();
        MimeMessage message = new MimeMessage(session);
        try {
            //发件人
            message.setFrom(new InternetAddress(PROPS.getProperty("user")));
            //收件人
            String[] tos = to.split(";");
            Address[] addresses = new InternetAddress[tos.length];
            for (int i = 0; i < tos.length; i++) {
                addresses[i] = new InternetAddress(tos[i]);
            }
            message.addRecipients(Message.RecipientType.TO, addresses);
            //标题
            message.setSubject(subject);
            //正文
            MimeBodyPart contentPart = new MimeBodyPart();
            contentPart.setText(content);
            //全文
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(contentPart, 0);
            //会议事件
            if (Objects.nonNull(meetingData)) {
                BodyPart meetingPart = new MimeBodyPart();
                String meeting = TemplateUtil.processTemplate(MEETING_TEMPLATE, TemplateUtil.dataToMap(meetingData));
                meetingPart.setDataHandler(new DataHandler(new ByteArrayDataSource(meeting,
                        "text/calendar;method=REQUEST;charset=UTF-8")));
                multipart.addBodyPart(meetingPart);
            }
            message.setContent(multipart);

            Transport.send(message);
        } catch (Exception e) {
            logger.error("邮件发送异常", e);
        } finally {
            logger.info("邮件发送完毕！");
        }
    }

    private static Session createSession() {
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                String user = PROPS.getProperty("user");
                String password = PROPS.getProperty("password");
                return new PasswordAuthentication(user, password);
            }
        };
        return Session.getInstance(PROPS, authenticator);
    }
}
