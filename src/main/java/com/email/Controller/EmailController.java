package com.email.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.model.EmailRequest;
import com.email.model.EmailResponse;
import com.email.service.EmailService;

@RestController
@CrossOrigin
public class EmailController {


    @Autowired
    private EmailService emailService;

    @RequestMapping("/welcome") 
    public String welcome()
    {
        return "hello this is my email";
    }


    //api to send email
    @PostMapping("/sendemail")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest){
        boolean result = this.emailService.sendEmail(emailRequest.getSubject(), emailRequest.getMessage(), emailRequest.getTo());
        if(result){
            return ResponseEntity.ok(new EmailResponse("Email sent successfully"));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email not sent"));
        }
        
        
    }
}
