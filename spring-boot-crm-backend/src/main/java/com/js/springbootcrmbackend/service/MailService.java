package com.js.springbootcrmbackend.service;

import com.js.springbootcrmbackend.exception.CrmException;
import com.js.springbootcrmbackend.model.NotificationEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {

    @Autowired
    private final JavaMailSender mailSender;

    private final MailContentBuilder mailContentBuilder;

    @Async
    void sendMail(NotificationEmail notificationEmail){
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("crm@johannessiedersberger.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(notificationEmail.getBody());
        };

        try{
            mailSender.send(messagePreparator);
            log.info("Activation email sent!!");
        }catch (MailException e){
            log.error("Exception occurred when sending mail", e);
            throw new CrmException("Exception occurred when sending mail to " + notificationEmail.getRecipient(), e);
        }
    }
}
