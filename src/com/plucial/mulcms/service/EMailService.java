package com.plucial.mulcms.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.plucial.mulcms.model.widgets.form.MailAction;


public class EMailService {
    
    /**
     * メール送信
     * @param adminEmail
     * @param subject
     * @param message
     * @param isLocal
     * @param mailActionList
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    public static void receptionMail(
            String adminEmail,
            String subject, 
            String message,
            boolean isLocal,
            List<MailAction> mailActionList) throws UnsupportedEncodingException, MessagingException {
        
        if(isLocal) {
            System.out.println(subject);
            System.out.println(message.toString());
            return;
        }
        
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage msg = new MimeMessage(session);

        //発信元情報：メールアドレス、名前
        msg.setFrom(new InternetAddress(adminEmail));

        //送信先情報
        List<InternetAddress> recipientAddressList = new ArrayList<InternetAddress>();
        for(MailAction action: mailActionList) {
            recipientAddressList.add(new InternetAddress(action.getSendEmail().getEmail()));
        }
        msg.addRecipients(Message.RecipientType.TO,
            (InternetAddress[])recipientAddressList.toArray());

        msg.setSubject(subject, "ISO-2022-JP");
        msg.setText(message);

        Transport.send(msg);
    }

}
