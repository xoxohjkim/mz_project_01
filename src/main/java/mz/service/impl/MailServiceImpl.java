package mz.service.impl;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import mz.dto.Mail;
import mz.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
    JavaMailSender mailSender;
 
    @Override
    public void sendEmail(Mail mail) {
 
    	
        final MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

                helper.setFrom(mail.getMailFrom()); // recipient
                helper.setTo(mail.getMailTo()); //sender
                helper.setSubject(mail.getMailSubject()); // mail title
                helper.setText(mail.getMailContent(), true); // mail content
            }
        };
 
        mailSender.send(preparator);
	}

}
