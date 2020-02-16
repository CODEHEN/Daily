package com.day.Controller;

import com.day.pojo.User;
import com.day.repositories.UserRepository;
import net.minidev.json.JSONArray;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TeacherController {

    @Resource
    UserRepository userRepository;

    @RequestMapping("/tealist")
    public String stulist(){
        List<User> maps=userRepository.findAllByRole("ROLE_Teacher");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(maps);
        String msg='{'+"\"code\""+':'+0+','+"\"data\""+':'+'['+ jsonArray.toJSONString().replace("[", "").replace("]", "")+']'+'}';
        return msg;
    }
    @RequestMapping("/user/addTea")
    public String addTea(User user){
        user.setEnabled(Boolean.TRUE);
        user.setClassname("Teacher");
        user.setGroupname("5");
        user.setPassword("{noop}"+user.getPassword());
        user.setRole("ROLE_Teacher");
        userRepository.save(user);
        return "0";
    }

    @PostMapping("/user/updateTea/{id}")
    @Transactional
    public String updataStu(@PathVariable("id")Integer id, User user){
        user.setRole("ROLE_Teacher");
        userRepository.updateTea(user.getEmail(),user.getName(), user.getPassword(), user.getPhonenumber(), user.getRole(), user.getSex(), user.getUsername(), user.getId());
        return "0";

    }
    @PostMapping("/user/deleteTea/{id}")
    @Transactional
    public String deleteTea(@PathVariable("id")int id){
        userRepository.deleteUserByid(id);
        return "0";
    }

}
