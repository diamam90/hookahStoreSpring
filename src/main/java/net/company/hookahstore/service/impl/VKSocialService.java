package net.company.hookahstore.service.impl;

import com.vk.api.sdk.actions.Users;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.users.Fields;
import net.company.hookahstore.model.SocialAccount;
import net.company.hookahstore.service.ServiceManager;
import net.company.hookahstore.service.SocialService;


public class VKSocialService implements SocialService {
    private final String idClient;
    private final String redirectUrl;
    private final String secret;



    public VKSocialService(ServiceManager serviceManager){
        idClient=serviceManager.getApplicationProperty("social.vk.idClient");
        redirectUrl=serviceManager.getApplicationProperty("app.host")+"/from-social";
        secret=serviceManager.getApplicationProperty("social.vk.secret");
    }
    /* EXAMPLE
    https://oauth.vk.com/authorize?client_id=1&display=page&redirect_uri=http://example.com/callback&scope=friends&response_type=code&v=5.131
    */

    @Override
    public String getAuthorizeUrl() {
        StringBuilder url = new StringBuilder("https://oauth.vk.com/authorize?client_id=");
        url.append(idClient).append("&display=page&redirect_uri=").append(redirectUrl).append("&response_type=code&v=5.131");
        return url.toString();
    }

    @Override
    public SocialAccount getSocialAccount(String code){
        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);
        try {
            UserAuthResponse authResponse = vk.oAuth().userAuthorizationCodeFlow(Integer.parseInt(idClient), secret, redirectUrl, code).execute();
            UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
            String name = vk.users().get(actor).fields().executeAsString();
            return new SocialAccount(name,authResponse.getEmail(),actor.getPhone());
        } catch (ApiException e){
            throw new RuntimeException(e.getMessage());
        } catch (ClientException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
