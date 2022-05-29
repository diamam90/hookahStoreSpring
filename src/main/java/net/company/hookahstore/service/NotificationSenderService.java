package net.company.hookahstore.service;

import net.company.hookahstore.model.CurrentAccount;
import net.company.hookahstore.model.NotificationMessage;

public interface NotificationSenderService {
    void sendNotification(NotificationMessage message);

    String getDestionationAddress(CurrentAccount currentAccount);
}
