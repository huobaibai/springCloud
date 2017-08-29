package com.huobaibai.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSendMail {
	@Autowired
	private JavaMailSender mailSender;
	@Test
	public void sendMail() throws Exception {
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setFrom("9905049@qq.com");
//		message.setTo("9905049@qq.com");
//		message.setSubject("主题：简单邮件");
//		message.setText("测试邮件内容");
//		mailSender.send(message);
		
		
		
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("9905049@qq.com");
		helper.setTo("9905049@qq.com");
		helper.setSubject("主题：有附件");
		helper.setText("有附件的邮件");
//		FileSystemResource file = new FileSystemResource(new File("weixin.jpg"));
//		helper.addAttachment("附件-1.jpg", file);
//		helper.addAttachment("附件-2.jpg", file);
//		Map<String, Object> model = new HashMap();
//		model.put("username", "didi");
////		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "template.vm", "UTF-8", model);
//		String text =VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "welcome.vm", "UTF-8", model)); 
//		helper.setText(text, true);
		mailSender.send(mimeMessage);
		
	}
}
