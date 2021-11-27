package net.company.hookahstore.servlet.page;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import net.company.hookahstore.Constants;
import net.company.hookahstore.service.SocialService;
import net.company.hookahstore.servlet.AbstractController;
import net.company.hookahstore.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/from-social")
public class FromSocialController  extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String targetUrl= (String)req.getSession().getAttribute("target");
       // req.getSession().setAttribute(Constants.CURRENT_ACCOUNT, getSocialService().getSocialAccount(code));
        RoutingUtils.forwardToPage(targetUrl,req,resp);
    }
}
