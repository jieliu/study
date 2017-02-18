package com.tianma.api.controller;

import com.tianma.api.domain.oauthclient.Client;
import com.tianma.api.service.oauthclient.ClientService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by zhengpeiwei on 16/4/5.
 */
@Controller
@Api
public class PageController {

    private static final String FREELINK_API = "greetingandgoodbye";

    private static final String EGOO_API = "other";

    private static final String FREESWITCH_API = "morning";

    private static final Logger LOGGER = LoggerFactory.getLogger(PageController.class);

    @Autowired
    ClientService clientService;



    @RequestMapping("/admin")
    public String frontpage(Map<String, Object> model) {
        return "apientry";
    }

    @RequestMapping("/")
    public String index1(Map<String, Object> model) {
        return "apientry";
    }

    @RequestMapping("/admin/index")
    public String index(Map<String, Object> model) {
        return "apientry";
    }

    @RequestMapping("/login")
    public String login(Map<String, Object> model,@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {
        return "login";
    }


    @RequestMapping(value = "/admin/clientinfo",method = RequestMethod.GET)
    public String clientinfo(@RequestParam(value ="client_id")String client_id,Map<String, Object> model){
        model.put("client",clientService.findById(client_id));
        return "editclient";
    }

    @RequestMapping(value = "/admin/clientinfo",method = RequestMethod.POST)
    public String putclientinfo(Map<String, Object> model){
        return "index";
    }



    @RequestMapping("/admin/apis")
    public String apis(Map<String, Object> model) {
        return "apientry";
    }



    @RequestMapping(value = "/admin/clientmodify",method = RequestMethod.POST)
    public String clientmodify(@RequestParam String id,HttpServletRequest request, HttpServletResponse respnose){
        //处理表单
        String password=request.getParameter("password");
        String passwordc=request.getParameter("passwordc");
        String addinfo=request.getParameter("addinfo");
        String[] authotype=request.getParameterValues("authotype");
        String[] resourceid=request.getParameterValues("resourceid");
        String[] scope1=request.getParameterValues("scope1");
        String[] scope2=request.getParameterValues("scope2");
        String[] scope3=request.getParameterValues("scope3");
        String validity=request.getParameter("validity");

        /*打印参数
        LOGGER.info("password is"+password);
        LOGGER.info("passwordc is"+passwordc);
        LOGGER.info("addinfo is"+addinfo);
        LOGGER.info("validity is"+validity);
        for(String s:authotype){
            LOGGER.info("authotype have "+s);

        }
        for(String s:resourceid){
            LOGGER.info("resourceid have "+s);

        }
        for(String s:scope1){
            LOGGER.info("scope1 have "+s);

        }
        if(scope2==null)LOGGER.info("scope2=null");
        */

        Client client=new Client();
        client.setClient_id(id);
        Client temp=clientService.findById(id);

        if(password!=null){client.setClient_secret(password);}
        else {client.setClient_secret(temp.getClient_secret());}//password

        if(addinfo!=null){client.setAdditional_information(addinfo);}
        else {client.setClient_secret(temp.getAdditional_information());}//addinfo

        if(validity!=null){
            Long value=Long.valueOf(validity);
            client.setAccess_token_validity(value);}
        else {client.setAccess_token_validity(temp.getAccess_token_validity());}//validity

        if(authotype!=null){
            StringBuffer type=new StringBuffer();
            for(String s:authotype){
                type.append(s+",");
            }
            type.deleteCharAt(type.length()-1);
            LOGGER.info("type= "+type);
            client.setAuthorized_grant_types(type.toString());
        }
        else {client.setAuthorized_grant_types(temp.getAuthorized_grant_types());}//authotype


        if(resourceid!=null){
            StringBuffer rid=new StringBuffer();
            for(String s:resourceid){
                rid.append(s+",");
            }
            rid.deleteCharAt(rid.length()-1);
            LOGGER.info("rid= "+rid);
            client.setResource_ids(rid.toString());
        }
        else {client.setResource_ids(temp.getResource_ids());}//resourceid


        StringBuffer scp=new StringBuffer();
        if(scope1!=null){
            for(String s:scope1){
                scp.append(s+",");
            }
        }
        else {}
        if(scope2!=null){
            for(String s:scope2){
                scp.append(s+",");
            }
        }
        else {}
        if(scope3!=null){
            for(String s:scope3){
                scp.append(s+",");
            }
        }
        else {}
        if(scp.length()>=0){
            scp.deleteCharAt(scp.length()-1);
        }
        if(scp.length()>0)
             client.setScope(scp.toString());
        else client.setScope(temp.getScope());
        //设置参数

        clientService.updateClient(client);

        return "redirect:/ singleclient?id="+client.getClient_id();
    }



    @RequestMapping("/admin/getToken")
    public String getToken(@RequestParam String code,Map<String, Object> model) {
        model.put("acode",code);
        model.put("allclient",clientService.allClient());
        return "autho";
    }

    @RequestMapping("/admin/singleapi")
    public String apis(@RequestParam String id,Map<String, Object> model) {//id就是api种类
        //查询对应API已授权的用户放进去
        StringBuffer apiname=new StringBuffer();
        switch(id) {
            case "FREELINK_API": {
                apiname.append(FREELINK_API);
                break;
            }
            case "FREESWITCH_API": {
                apiname.append(FREESWITCH_API);
                break;
            }
            case "EGOO_API": {
                apiname.append(EGOO_API);
                break;
            }
        }
        //根据apiname找内容
        model.put("clients",clientService.findByApiName(apiname));
        model.put("allclient",clientService.allClient());
        return "singleapi";
    }





    @RequestMapping("/admin/clientoverall")
      public String clientoverall(Map<String, Object> model) {
        model.put("allclient", clientService.allClient());
        return "clientoverall";
    }

    @RequestMapping("/admin/singleclient")//放一回数据
    public String singleclient(Map<String, Object> model,@RequestParam String id) {
        model.put("client", clientService.findById(id));//这里应该获取url参数,来设置id
        model.put("approval", clientService.findApprovalByClient(id));
        return "singleclient";
    }


    @RequestMapping("/admin/clientdo")
    public String clientdo(Map<String, Object> model) {
        return "clientdo";
    }



}
