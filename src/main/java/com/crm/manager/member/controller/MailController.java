package com.crm.manager.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.crm.manager.common.JsonResult;

import freemarker.template.Template;

@RestController  
@RequestMapping("mail")  
public class MailController {  
    @Autowired  
    JavaMailSender mailSender;  
    
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;  //自动注入
    
    @Value("${spring.mail.username}")
    private String fromMail;
      
    @RequestMapping("sendemail")  
    public Object sendEmail()  {  
        try {  
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();  
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);  
            message.setFrom(fromMail);  
            message.setTo("772464171@qq.com");  
            message.setSubject("测试邮件主题");  
            String htmlText=getMailText("张三");//使用模板生成html邮件内容  
            message.setText(htmlText, true);  
            this.mailSender.send(mimeMessage);  
            
            return JsonResult.success("发送成功");
        } catch(Exception e) {  
        	 return JsonResult.failure(e.getMessage());
        }  
    } 
    
    
    //通过模板构造邮件内容，参数username将替换模板文件中的${username}标签。  
    private String getMailText(String username){  
        String htmlText="";  
        try {  
            //通过指定模板名获取FreeMarker模板实例  
            Template tpl=freeMarkerConfigurer.getConfiguration().getTemplate("mailTemplate/mail.ftl");  
            //FreeMarker通过Map传递动态数据  
            Map<String,Object> map=new HashMap<String,Object>();  
            map.put("username",username); //注意动态数据的key和模板标签中指定的属性相匹配  
            //解析模板并替换动态数据，最终username将替换模板文件中的${username}标签。  
            htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,map);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return htmlText;  
    } 
}
