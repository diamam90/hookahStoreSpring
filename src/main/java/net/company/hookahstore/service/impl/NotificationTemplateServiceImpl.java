package net.company.hookahstore.service.impl;

import net.company.hookahstore.component.NotificationContentResolver;
import net.company.hookahstore.exception.CantCompleteClientRequestException;
import net.company.hookahstore.model.NotificationMessage;
import net.company.hookahstore.service.NotificationTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Map;

@Service
public class NotificationTemplateServiceImpl implements NotificationTemplateService {
    public static final Logger LOGGER = LoggerFactory.getLogger(NotificationTemplateServiceImpl.class);
    private Map<String, NotificationMessage> notificationTemplates;

    @Value("${notification.config.path}")
    private String notificationConfigPath;

    @Autowired
    private NotificationContentResolver notificationContentResolver;

    @Override
    public NotificationMessage createNotificationMessage(String templateName, Object model) {
        NotificationMessage message = notificationTemplates.get(templateName);
        if (message == null) {
            throw new CantCompleteClientRequestException("Notification template '" + templateName + "' not found");
        }
        String resolvedContent = notificationContentResolver.resolve(message.getContent(), model);
        return new NotificationMessage(message.getSubject(), resolvedContent);
    }

    @PostConstruct
    private void postConstruct() {
        notificationTemplates = Collections.unmodifiableMap(getNotificationTemplates());
        LOGGER.info("Loaded {} notification templates", notificationTemplates.size());
    }

    private Map<String, NotificationMessage> getNotificationTemplates() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.setValidating(false);
        reader.loadBeanDefinitions(new PathResource(notificationConfigPath));
        return beanFactory.getBeansOfType(NotificationMessage.class);
    }

}
