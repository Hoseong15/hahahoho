package com.example.HMS_MANAGEMENT.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    public static void sendEmail(String recipientEmail, String subject, String content) {
        // SMTP 서버 설정
        String host = "smtp.gamil.com";
        String port = "465";
        String username = "km7081@naver.com";
        String password = "dlrtmfpdls0!";

        // 메일 속성 설정
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // 세션 생성
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // 메시지 객체 생성
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(content);

            // 메일 전송
            Transport.send(message);

            System.out.println("이메일 전송 성공!");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("이메일 전송 실패!");
        }
    }

    public static void main(String[] args) {
        String recipientEmail = "recipient@example.com";
        String subject = "급여명세서";
        String content = "급여 명세서 내용을 여기에 작성하세요.";

        // 명세서 내용을 이메일로 전송
        sendEmail(recipientEmail, subject, content);
    }
}
