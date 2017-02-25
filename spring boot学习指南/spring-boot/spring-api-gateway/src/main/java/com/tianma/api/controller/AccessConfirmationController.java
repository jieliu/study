package com.tianma.api.controller;

/**
 * Created by zhengpeiwei on 16/3/15.
 */

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Qualifier;
        import org.springframework.security.oauth2.common.util.OAuth2Utils;
        import org.springframework.security.oauth2.provider.AuthorizationRequest;
        import org.springframework.security.oauth2.provider.ClientDetails;
        import org.springframework.security.oauth2.provider.ClientDetailsService;
        import org.springframework.security.oauth2.provider.approval.ApprovalStore;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.SessionAttributes;
        import org.springframework.web.servlet.ModelAndView;

        import java.security.Principal;
        import java.util.LinkedHashMap;
        import java.util.Map;




@Controller
@SessionAttributes("authorizationRequest")
public class AccessConfirmationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessConfirmationController.class);


    @Autowired
    @Qualifier("jdbcclientdetail")
    private ClientDetailsService clientDetailsService;

    private ApprovalStore approvalStore;


    @RequestMapping(value = "/oauth/confirm_access" )//这是自定义授权页面
    public ModelAndView getAccessConfirmation(Map<String, Object> model, Principal principal) throws Exception {



        AuthorizationRequest clientAuth = (AuthorizationRequest) model.remove("authorizationRequest");
        LOGGER.info("id= "+clientAuth.getClientId());
        ClientDetails client = clientDetailsService.loadClientByClientId(clientAuth.getClientId());
        LOGGER.info("client_id= "+client.getClientId());
        model.put("auth_request", clientAuth);//放入授权请求信息
        model.put("client", client);//放入client信息
        Map<String, String> scopes = new LinkedHashMap<String, String>();
        for (String scope : clientAuth.getScope()) {
            LOGGER.info("scope: "+scope);
            scopes.put(OAuth2Utils.SCOPE_PREFIX + scope, "false");
        }//根据clientauth放入scope请求信息
        model.put("scopes", scopes);//scope放入模型
        return new ModelAndView("access_confirmation", model);//发送数据给access_confirm页面展现
    }


    @RequestMapping("/oauth/error")//自定义UI之错误页面
    public String handleError(Map<String, Object> model) throws Exception {
        model.put("message", "There was a problem with the OAuth2 protocol");
        return "oauth_error";//在model里放入错误信息返回给oauth_error页面即可
    }



    public void setApprovalStore(ApprovalStore approvalStore) {
        this.approvalStore = approvalStore;
    }

}
