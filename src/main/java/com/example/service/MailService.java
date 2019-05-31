package com.example.service;

/**
 * @deprecated:
 * @author作者：mp 2019年5月31日
 */
public interface MailService {
	/**
	 * @deprecated:
	 * @author作者：mp 2019年5月31日
	 */
	public void sendSimpleMail(String to, String subject, String content);

	public void sendHtmlMail(String to, String subject, String content);

	public void sendAttachmentsMail(String to, String subject, String content, String filePath);

	public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);

}
