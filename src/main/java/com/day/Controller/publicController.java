package com.day.Controller;

import com.day.pojo.User;
import com.day.repositories.UserRepository;
import net.minidev.json.JSONArray;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class publicController {
    @Resource
    UserRepository userRepository;

    @RequestMapping("/allStudent")
    @ResponseBody
    @Transactional
    public JSONArray allStudent(String time){
    if (time==""){
    Date date=new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    time=formatter.format(date);

    }
        List<User> users=userRepository.nameStatu();
        List<User> nameStatus=userRepository.ReportStatu(time);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(users);
        jsonArray.add(nameStatus);
        return jsonArray;


    }

}
