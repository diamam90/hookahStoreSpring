package net.company.hookahstore.service;

import net.company.hookahstore.model.CurrentAccount;

public interface NotificationManagerService {
    void sendRestoreAddressLink(CurrentAccount currentAccount,String restoreLink);
    void sendPasswordChanged(CurrentAccount currentAccount);
}
