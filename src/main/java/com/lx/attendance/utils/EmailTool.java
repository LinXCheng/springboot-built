package com.lx.attendance.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class EmailTool {
    @Autowired
    private JavaMailSender javaMailSender;
    public static EmailTool emailTool; 
    @PostConstruct  
    public void init() {
    	emailTool = this; 
    	emailTool.javaMailSender = this.javaMailSender; 
    }

    /**
     * 发送简单的邮件
     * @param To 接收方邮箱
     * @param subject  主题
     * @param Text 内容文本
     */
    public static boolean sendSimpleMail(String To,String subject,String Text){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("LxServiceVerify@163.com");
            message.setTo(To);
            message.setSubject(subject);
            message.setText(Text);
            emailTool.javaMailSender.send(message);
            return true;
        } catch (MailException e) {
            logControl.logPrint(EmailTool.class,null,e.getMessage());
            return false;
        }
    }
}