package com.sec.schedule.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.sec.schedule.dao.TaskFactDao;
import com.sec.schedule.dict.TaskStatus;
import com.sec.schedule.entity.TaskFact;
import com.sec.schedule.model.CompositeIdTaskFact;
import com.sec.schedule.utils.DateUtils;

@Controller
public class TaskController {

    @Autowired
    TaskFactDao taskFactDao;

    @RequestMapping("/tasklist")
    public String tasklist(@RequestParam(required=false) String statDt , Model model) {
        if(statDt == null) {
            statDt = DateUtils.getCurrentDateStr();
        }
        
     

        List<TaskFact> taskFactList = taskFactDao.findTaskFactListByStatDt(statDt);
        model.addAttribute("tasklist", taskFactList);
        return "tasklist";
    }


    @RequestMapping(value="/updateTaskStatus",method=RequestMethod.POST)
    public String updateTaskStatus(@RequestParam String[] id) {
        Gson gson = new Gson();

        
        for(int i=0 ; i< id.length ; i++) {
            CompositeIdTaskFact taskFactId = gson.fromJson(id[i], CompositeIdTaskFact.class);
            TaskFact taskFact = taskFactDao.findOne(taskFactId);
            taskFact.setStatus(TaskStatus.PENDING);
            taskFactDao.save(taskFact);
        }
        return "forward:/tasklist";
    }
}