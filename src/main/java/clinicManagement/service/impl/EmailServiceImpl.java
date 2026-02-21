package clinicManagement.service.impl;

import clinicManagement.exception.MailSendingException;
import clinicManagement.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    @Override
    public void sendVerificationCode(String toEmail,String verificationCode) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(toEmail);
            message.setSubject("DentalClinic Verification Code");
            message.setText("Your verification code is: " + verificationCode);
            mailSender.send(message);
        }catch (MailException e){
            throw new MailSendingException("Failed to send verification code: " + e.getMessage());
        }
    }

    public static String generateVerificationCode() {
        int code = new SecureRandom().nextInt(10000, 99999);
        return String.valueOf(code);
    }

    }

