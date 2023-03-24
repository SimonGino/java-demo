package com.example.demo.sendmail.controller;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.example.demo.sendmail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * 测试发送邮件
 * @author Xuan
 * @date 2019/8/10 15:50
 */
@RestController
public class TestMailerSend {
    @Autowired
    private MailService mailService;
    @RequestMapping(value = "/send")
    public String send(){
        mailService.sendSimpleTextMailActual("发送主题","发送内容",new String[]{"public_class_xuan@163.com"},null,null,null);
        return "1";
    }

    @Autowired
    private JavaMailSender javaMailSender;


    public void sendSample() throws UnsupportedEncodingException {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage .setFrom("1285906731@qq.com");
        //设置目标发送人
        simpleMailMessage .setTo("1661982372@qq.com");
        simpleMailMessage.setSubject("主题");
        simpleMailMessage.setText("内容");

        //自定义发件人名称
        String nick = "";
        try {
            nick = javax.mail.internet.MimeUtility.encodeText("张三");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 设置发件人
        simpleMailMessage.setFrom(String.valueOf(new InternetAddress("1285906731@qq.com", nick)));

        javaMailSender.send(simpleMailMessage);//发送
        System.out.println("成功");

    }


    public void sendHTMLMail() throws MessagingException {
        MimeMessage mimeMailMessage = null;

        mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
        mimeMessageHelper.setFrom("1285906731@qq.com");
        mimeMessageHelper.setTo("1661982372@qq.com");
        mimeMessageHelper.setSubject("主题");
        mimeMessageHelper.setText("<h1>hello</h1>", true);
        javaMailSender.send(mimeMailMessage);

    }



    public void sendAttachmentMail() throws MessagingException {
        MimeMessage mimeMailMessage = null;

        mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
        mimeMessageHelper.setFrom("1285906731@qq.com");
        mimeMessageHelper.setTo("1661982372@qq.com");
        mimeMessageHelper.setSubject("主题");
        mimeMessageHelper.setText("发送内容");
        //文件路径  spring的FileSystemResource,使用绝对路径访问文件资源
        FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/mail.jpg"));
        mimeMessageHelper.addAttachment("mail.png", file);

        javaMailSender.send(mimeMailMessage);

    }


    public void sendInlineMail() throws MessagingException {
        MimeMessage mimeMailMessage = null;

        mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
        mimeMessageHelper.setFrom("1285906731@qq.com");
        mimeMessageHelper.setTo("1661982372@qq.com");
        mimeMessageHelper.setSubject("主题");
        mimeMessageHelper.setText("<html><body>带静态资源的邮件内容，这个一张IDEA配置的照片:<img src='cid:picture' /></body></html>", true);
        //文件路径
        FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/mail.jpg"));
        mimeMessageHelper.addInline("picture", file);

        javaMailSender.send(mimeMailMessage);

    }
}