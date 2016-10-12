package com.gaoyang.util;

import com.gaoyang.mail.Email_Authenticator;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Ray on 2016/4/17.
 */
public class MailUtil {

    public static void sendMail(String subject, String content) throws Exception {
        String to = "gaoyang@ssports.com";
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.exmail.qq.com");
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.auth", "true");
        Email_Authenticator authenticator = new Email_Authenticator(
                "register@ssports.com", "2z3XAIGu");
        Session sendMailSession = Session.getDefaultInstance(properties,
                authenticator);
        MimeMessage mailMessage = new MimeMessage(sendMailSession);
        mailMessage.setFrom(new InternetAddress("register@ssports.com"));

        mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(
                to));
        mailMessage.setSubject(subject, "UTF-8");
        mailMessage.setSentDate(new Date());

        Multipart mainPart = new MimeMultipart();

        BodyPart html = new MimeBodyPart();
        html.setContent(content.trim(), "text/html; charset=utf-8");
        mainPart.addBodyPart(html);
        mailMessage.setContent(mainPart);
        Transport.send(mailMessage);
    }
}
