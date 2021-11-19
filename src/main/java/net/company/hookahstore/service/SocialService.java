package net.company.hookahstore.service;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import net.company.hookahstore.model.SocialAccount;

public interface SocialService {
    String getAuthorizeUrl();
    SocialAccount getSocialAccount(String code)
            ;
}
