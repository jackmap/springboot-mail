package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMailApplicationTests {

	    @Autowired
	    private JavaMailSender mailSender;

	    @Test
	    public void sendSimpleMail() throws Exception {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setFrom("710623754@qq.com");
	        message.setTo("1541835901@qq.com");
	        message.setSubject("主题：简单邮件");
	        message.setText("测试邮件内容");
	        mailSender.send(message);
	    }
//	    @Test
//	    public void testHtmlMail() throws Exception {
//	        String content="<html>\n" +
//	                "<body>\n" +
//	                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
//	                "</body>\n" +
//	                "</html>";
//	        mailService.sendHtmlMail("ityouknow@126.com","test simple mail",content);
//	    }
//	
//	    @Test
//	    public void sendAttachmentsMail() {
//	        String filePath="e:\\tmp\\application.log";
//	        mailService.sendAttachmentsMail("ityouknow@126.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
//	    }
//	
//	
//	    @Test
//	    public void sendInlineResourceMail() {
//	        String rscId = "neo006";
//	        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
//	        String imgPath = "C:\\Users\\summer\\Pictures\\favicon.png";
//	
//	        mailService.sendInlineResourceMail("ityouknow@126.com", "主题：这是有图片的邮件", content, imgPath, rscId);
//	    }
//	
//	
//	    @Test
//	    public void sendTemplateMail() {
//	        //创建邮件正文
//	        Context context = new Context();
//	        context.setVariable("id", "006");
//	        String emailContent = templateEngine.process("emailTemplate", context);
//	
//	        mailService.sendHtmlMail("ityouknow@126.com","主题：这是模板邮件",emailContent);
//	    }
}
