package net.company.hookahstore.service.impl;

import net.company.hookahstore.model.CurrentAccount;
import net.company.hookahstore.model.NotificationMessage;
import net.company.hookahstore.service.NotificationSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import java.util.concurrent.ExecutorService;

@Service
public class EmailNotificationSenderService implements NotificationSenderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailNotificationSenderService.class);

    @Autowired
    private ExecutorService executorService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${email.fromEmail}")
    private String fromEmail;
    @Value("${email.fromName}")
    private String fromName;
    @Value("${email.sendTryCount}")
    private int tryCount;


    @Override
    public void sendNotification(NotificationMessage message) {
        executorService.submit(new EmailItem(message,tryCount));
    }

    @Override
    public String getDestionationAddress(CurrentAccount currentAccount) {
        return currentAccount.getEmail();
    }

    private class EmailItem implements Runnable {
        private final NotificationMessage notificationMessage;
        private int tryCount;

        private EmailItem(NotificationMessage notificationMessage, int tryCount) {
            this.notificationMessage = notificationMessage;
            this.tryCount = tryCount;
        }

        @Override
        public void run() {
            try{
                LOGGER.debug("Send a new email to{}",notificationMessage.getDestinationAddress());
                MimeMessageHelper message = new MimeMessageHelper(javaMailSender.createMimeMessage(),false);
                message.setSubject(notificationMessage.getSubject());
                message.setTo(new InternetAddress(notificationMessage.getDestinationAddress(),notificationMessage.getDestinationName()));
                message.setFrom(fromEmail,fromName);
                message.setText(notificationMessage.getContent());
                MimeMailMessage msg = new MimeMailMessage(message) ;
                javaMailSender.send(msg.getMimeMessage());
                LOGGER.debug("Email to {} successful sent",notificationMessage.getDestinationAddress());
            } catch (Exception e){
                LOGGER.error("Can't send email to " + notificationMessage.getDestinationAddress()+": " + e.getMessage(),e);
                tryCount--;
                if (tryCount>0){
                    LOGGER.debug("trying again to send email: tryCount={}, destionationEmail={}",tryCount, notificationMessage.getDestinationAddress());
                    executorService.submit(this);
                } else {
                    LOGGER.error("Email not sent to " + notificationMessage.getDestinationAddress());
                }
            }
        }
    }

}
