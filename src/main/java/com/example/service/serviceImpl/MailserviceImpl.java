package com.example.service.serviceImpl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.service.MailService;

/**
 * @deprecated:
 * @author作者：mp 2019年5月31日
 */
@Service
@Component
public class MailserviceImpl implements MailService {
	private final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String from;

	/**
	 * 发送文本邮件
	 * 
	 * @param to
	 * @param subject
	 * @param content
	 */
	public void sendSimpleMail(String to, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);// 收件人
		message.setTo(to);
		message.setSubject(subject);// 主题
		message.setText(content);// 内容

		try {
			mailSender.send(message);
			logger.info("简单邮件已经发送。");
		} catch (Exception e) {
			logger.error("发送简单邮件时发生异常！", e);
		}

	}

	/**
	 * 发送html邮件
	 * 
	 * @param to
	 * @param subject
	 * @param content
	 */
	public void sendHtmlMail(String to, String subject, String content) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			// true表示需要创建一个multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			mailSender.send(message);
			logger.info("html邮件发送成功");
		} catch (MessagingException e) {
			logger.error("发送html邮件时发生异常！", e);
		}
	}

	/**
	 * 发送带附件的邮件
	 * 
	 * @param to
	 * @param subject
	 * @param content
	 * @param filePath
	 */
	public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			FileSystemResource file = new FileSystemResource(new File(filePath));
			String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
			helper.addAttachment(fileName, file);
			// helper.addAttachment("test"+fileName, file);

			mailSender.send(message);
			logger.info("带附件的邮件已经发送。");
		} catch (MessagingException e) {
			logger.error("发送带附件的邮件时发生异常！", e);
		}
	}

	/**
	 * 发送正文中有静态资源（图片）的邮件
	 * 
	 * @param to
	 * @param subject
	 * @param content
	 * @param rscPath
	 * @param rscId
	 */
	public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			FileSystemResource res = new FileSystemResource(new File(rscPath));
			helper.addInline(rscId, res);

			mailSender.send(message);
			logger.info("嵌入静态资源的邮件已经发送。");
		} catch (MessagingException e) {
			logger.error("发送嵌入静态资源的邮件时发生异常！", e);
		}
	}
}
