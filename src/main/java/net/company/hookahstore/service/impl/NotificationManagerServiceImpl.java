package net.company.hookahstore.service.impl;

import net.company.hookahstore.model.CurrentAccount;
import net.company.hookahstore.model.NotificationMessage;
import net.company.hookahstore.service.NotificationManagerService;
import net.company.hookahstore.service.NotificationSenderService;
import net.company.hookahstore.service.NotificationTemplateService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationManagerServiceImpl implements NotificationManagerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationManagerServiceImpl.class);
    @Autowired
    NotificationSenderService notificationSenderService;

    @Autowired
    NotificationTemplateService notificationTemplateService;

    @Override
    public void sendRestoreAddressLink(CurrentAccount currentAccount, String restoreLink) {
        LOGGER.info("Resotre link: {} for account {}",restoreLink,currentAccount);
        Map<String, Object> model = new HashMap<>();
        model.put("currentAccount", currentAccount);
        model.put("restoreLink", restoreLink);
        processNotification(currentAccount, "restoreAccessNotification", model);
    }

    @Override
    public void sendPasswordChanged(CurrentAccount currentAccount) {
        LOGGER.info("Password changed for account {}",currentAccount);
        Map<String, Object> model = new HashMap<>();
        model.put("currentAccount",currentAccount);
        processNotification(currentAccount,"passwordChangedNotification",model);
    }

    private void processNotification(CurrentAccount currentAccount, String templateName, Object model) {
        String destinationAddress = notificationSenderService.getDestionationAddress(currentAccount);
        if (StringUtils.isNotBlank(destinationAddress)) {
            NotificationMessage notificationMessage = notificationTemplateService.createNotificationMessage(templateName, model);
            notificationMessage.setDestinationAddress(destinationAddress);
            notificationMessage.setDestinationName(currentAccount.getName());
            notificationSenderService.sendNotification(notificationMessage);
        } else {
            LOGGER.error("Notification ignored: destinationAddress is empty for account " + currentAccount);
        }
    }
}
