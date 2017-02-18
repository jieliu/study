package com.tianma.spring.mvc.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by fiboliu on 16-8-16.
 */
@Service
public class EmailUtils {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailUtils(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSimpleEmail(String from, String to, String subject, String content) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);//发送者.
        message.setTo(to);//接收者.
        message.setSubject(subject);//邮件主题.
        message.setText(content);//邮件内容.

        mailSender.send(message);//发送邮件
    }

    public void sendAttachmentsEmail(String from, String to, String subject, String content) throws MessagingException {

        //这个是javax.mail.internet.MimeMessage下的，不要搞错了。
        MimeMessage mimeMessage =  mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        //基本设置.
        helper.setFrom(from);//发送者.
        helper.setTo(to);//接收者.
        helper.setSubject(subject);//邮件主题.
        helper.setText(content);//邮件内容.

        //org.springframework.core.io.FileSystemResource下的:
        //附件1,获取文件对象.
        ClassPathResource file1 = new ClassPathResource("img/head1.jpg");
        //添加附件，这里第一个参数是在邮件中显示的名称，也可以直接是head.jpg，但是一定要有文件后缀，不然就无法显示图片了。
        helper.addAttachment("头像1.jpg", file1);
        //附件2
        ClassPathResource file2 = new ClassPathResource("img/head1.jpg");
        helper.addAttachment("头像2.jpg", file2);

        mailSender.send(mimeMessage);
    }

    public void sendInlineMail(String from, String to, String subject, String content) throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        //基本设置.
        helper.setFrom(from);//发送者.
        helper.setTo(to);//接收者.
        helper.setSubject(subject);//邮件主题.
        // 邮件内容，第二个参数指定发送的是HTML格式
        //说明：嵌入图片<img src='cid:head'/>，其中cid:是固定的写法，而aaa是一个contentId。
        helper.setText("<body>这是图片：<img src='cid:head1' />这是图片：<img src='cid:head2' /></body>", true);

        ClassPathResource file1 = new ClassPathResource("img/head1.jpg");
        //添加附件，这里第一个参数是在邮件中显示的名称，也可以直接是head.jpg，但是一定要有文件后缀，不然就无法显示图片了。
        helper.addAttachment("头像1.jpg", file1);
        helper.addInline("head1", file1);
        //附件2
        ClassPathResource file2 = new ClassPathResource("img/head2.jpg");
        helper.addInline("head2", file2);

        mailSender.send(mimeMessage);
    }
}
