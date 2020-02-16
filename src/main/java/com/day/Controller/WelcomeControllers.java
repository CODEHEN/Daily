package com.day.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class WelcomeControllers {


    @RequestMapping("/loginpage")
    public String login(Model model){
        return "loginPage";
    }



    @RequestMapping({"/","/index"})
    public String index(){
        return "index";
    }
    @RequestMapping("console")
    public String console(){return "console";}

    @RequestMapping("logout")
    public String logOut(HttpSession session){
        session.invalidate();
        return "loginPage";

    }


}
