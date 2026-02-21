package clinicManagement.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendVerificationCode(String toEmail,String verificationCode);
}
