package com.revature.controllers;

import com.revature.models.MailHelper;
import com.revature.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@CrossOrigin(origins = "*")
public class MailController {

    private MailService mailService;
    @Autowired
    public MailController(MailService mailService){
        this.mailService = mailService;
    }

    @PostMapping("/mail")
    public void handleSendEmail(@RequestBody MailHelper mailHelper) {
        mailService.sendEmail(mailHelper);
    }
}
