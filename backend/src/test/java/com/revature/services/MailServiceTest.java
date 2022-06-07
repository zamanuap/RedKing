package com.revature.services;

import com.revature.models.MailHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailServiceTest {
    @Autowired
    private MailService mailService;

    @Test
    public void testSendEmailWhenRegister() {
        MailHelper mailHelper = new MailHelper();
        mailHelper.setEmail("mohammad478@revature.com");
        mailHelper.setFirstName("Monir");
        mailHelper.setMsgType("Register");
        mailService.sendEmail(mailHelper);

    }
    @Test
    public void testSendEmailWhenWin() {
        MailHelper mailHelper = new MailHelper();
        mailHelper.setEmail("mohammad478@revature.com");
        mailHelper.setFirstName("Monir");
        mailHelper.setMsgType("");
        mailService.sendEmail(mailHelper);

    }
}
