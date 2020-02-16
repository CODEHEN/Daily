package com.day.Controller;


import com.day.pojo.User;
import com.day.repositories.UserRepository;
import net.minidev.json.JSONArray;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class StudentController {

    @Resource
    UserRepository userRepository;

    @RequestMapping("alllist")
    public String alllist(){
        List<User> maps=userRepository.findAll();
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(maps);
        String msg='{'+"\"code\""+':'+0+','+"\"data\""+':'+'['+ jsonArray.toJSONString().replace("[", "").replace("]", "")+']'+'}';
        return msg;
    }


    @RequestMapping("stulist")
    @Transactional
    public String stulist(){
        List<User> maps1=userRepository.findAllByRole("ROLE_Student");
        List<User> maps2=userRepository.findAllByRole("ROLE_GroupLeader");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(maps1);
        jsonArray.add(maps2);
        String msg='{'+"\"code\""+':'+0+','+"\"data\""+':'+'['+ jsonArray.toJSONString().replace("[", "").replace("]", "")+']'+'}';
        return msg;
    }

 @RequestMapping("/user/addStu")
    public String addStu(User user){
        user.setEnabled(Boolean.TRUE);
        user.setPassword("{noop}"+user.getPassword());
        userRepository.save(user);
        return "0";
    }

    @PostMapping("/user/updateStu/{id}")
    @Transactional
   public String updataStu(@PathVariable("id")Integer id, User user){

        userRepository.updateStu(user.getClassname(), user.getEmail(), user.getGroupname(), user.getName(), user.getPassword(), user.getPhonenumber(), user.getRole(), user.getSex(), user.getUsername(), user.getId());
        return "0";

    }
    @PostMapping("/user/deleteStu/{id}")
    @Transactional
    public String deleteStu(@PathVariable("id")int id){
       userRepository.deleteUserByid(id);
        return "0";
    }
}
