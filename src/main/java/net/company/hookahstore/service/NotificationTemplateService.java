package net.company.hookahstore.service;

import net.company.hookahstore.model.NotificationMessage;

public interface NotificationTemplateService {

    NotificationMessage createNotificationMessage(String templateName,Object model);
}
