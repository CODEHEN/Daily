package com.day.Controller;

import com.day.pojo.Report;
import com.day.pojo.User;
import com.day.repositories.ReportRepository;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RestController
public class ReportController {

    @Autowired
    HttpSession session;

    @Autowired
    ReportRepository  reportRepository;
    @RequestMapping("pages/txtedit/addReport/{id}")
    public String addReport(Report report, HttpServletRequest request,@PathVariable("id")Integer id){

           User user = (User) request.getSession().getAttribute("user");

           report.setCreatedId(user.getId());
           report.setReaded(Boolean.FALSE);
           if(id.equals(0)) {
            report.setReportType(0);
           }else {
            report.setReportType(1);
           }
           Date date=new Date();
           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
           report.setCreatedAt(formatter.format(date));
           report.setCreatedGroup(user.getGroupname());
           report.setCreatedPerson(user.getName());
           reportRepository.save(report);
           return "0";
       }

       @RequestMapping("/reports")
       @ResponseBody
    public String reports(String time,String group){
           List<Report> reports;
        if (time==null) {
            reports = reportRepository.findAll();

        }else{
            reports=reportRepository.findAllByCreatedAtAndCreatedGroup(time,group);
        }
           JSONArray jsonArray = new JSONArray();
           jsonArray.add(reports);
           String msg='{'+"\"code\""+':'+0+','+"\"data\""+':'+'['+ jsonArray.toJSONString().replace("[", "").replace("]", "")+']'+'}';
           return msg;
       }
       @RequestMapping("/groupReports")
       @ResponseBody
       public String groupReports(String time){

           User use= (User) session.getAttribute("user");
           List<Report> reports;
           if (time==null) {
               reports = reportRepository.findAllByCreatedGroup(use.getGroupname());
           }else{
               reports=reportRepository.findAllByCreatedAtAndCreatedGroup(time,use.getGroupname());
           }
           JSONArray jsonArray = new JSONArray();
           jsonArray.add(reports);
           String msg='{'+"\"code\""+':'+0+','+"\"data\""+':'+'['+ jsonArray.toJSONString().replace("[", "").replace("]", "")+']'+'}';
           return msg;
       }
    @RequestMapping("user/setReaded/{id}")
    @Transactional
       public String setReaded(@PathVariable("id")Long id){
        reportRepository.setReaded(id);
        return "0";
       }
    }

