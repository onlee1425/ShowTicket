package zb.Team.showticket.user.component;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailComponent {
    private final JavaMailSender javaMailSender;

    public void sendMail(String email, String subject, String text){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject(subject);
        msg.setText(text);

        javaMailSender.send(msg);
    }
}
