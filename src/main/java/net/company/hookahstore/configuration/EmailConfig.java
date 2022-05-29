package net.company.hookahstore.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@ComponentScan({"net.company.hookahstore.component"})
public class EmailConfig {

    @Autowired
    private ConfigurableEnvironment environment;

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(environment.getRequiredProperty("email.smtp.server"));
        if (environment.containsProperty("email.smtp.username")){
//            sender.setUsername(environment.resolveRequiredPlaceholders(environment.getRequiredProperty("email.smtp.username")));
//            sender.setPassword(environment.resolveRequiredPlaceholders(environment.getRequiredProperty("email.smtp.password")));
            sender.setUsername(environment.getRequiredProperty("email.smtp.username"));
            sender.setPassword(environment.getRequiredProperty("email.smtp.password"));
            sender.setPort(Integer.parseInt(environment.getRequiredProperty("email.smtp.port")));
            sender.setDefaultEncoding("UTF-8");
            sender.setJavaMailProperties(javaMailProperties());
        }
        return sender;
    }

    private Properties javaMailProperties(){
        Properties p = new Properties();
        p.setProperty("mail.smtp.auth","true");
        p.setProperty("mail.smtp.ssl.enable","true");
        return p;
    }
}
