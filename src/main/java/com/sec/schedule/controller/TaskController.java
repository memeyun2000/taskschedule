package com.sec.schedule.controller;

import java.util.ArrayList;
import java.util.List;

import com.sec.schedule.dao.TaskImplSparkJobDao;
import com.sec.schedule.entity.TaskImplSparkJob;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.sec.schedule.dao.TaskFactDao;
import com.sec.schedule.dict.TaskStatus;
import com.sec.schedule.entity.TaskFact;
import com.sec.schedule.model.CompositeIdTaskFact;
import com.sec.schedule.utils.DateUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Controller
public class TaskController {

    @Autowired
    TaskFactDao taskFactDao;

    @Autowired
    TaskImplSparkJobDao taskImplSparkJobDao;

    @RequestMapping("/tasklist")
    public String tasklist(@RequestParam(required=false) String statDt , Model model) {
        if(statDt == null) {
            statDt = DateUtils.getCurrentDateStr();
        }

        List<TaskFact> taskFactList = taskFactDao.findTaskFactListByStatDt(statDt);
        model.addAttribute("tasklist", taskFactList);
        model.addAttribute("statDtBegin", statDt);
        model.addAttribute("statDtEnd", statDt);
        return "tasklist";
    }


    @RequestMapping("/taskSparkJobInfo")
    public String taskSparkJobList(Model model) {
        List<TaskImplSparkJob> taskFactList = taskImplSparkJobDao.findAll();
        model.addAttribute("tasklist", taskFactList);
        return "taskSparkJobInfo";
    }


    @RequestMapping(value="/searchTasklist",method = RequestMethod.POST)
    public String searchTasklist(@RequestParam(required = false) String statDtBegin ,
                                 @RequestParam(required = false) String statDtEnd ,
                                 @RequestParam(required = false) String taskId ,
                                 @RequestParam(required = false) String taskType ,
                                 @RequestParam(required = false) String granularity ,
                                 @RequestParam(required = false) String status ,
                                  Model model) {

        List<TaskFact> taskFactList = taskFactDao.findAll(new Specification<TaskFact>() {
            @Override
            public Predicate toPredicate(Root<TaskFact> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(statDtBegin)) {
                    list.add(cb.greaterThanOrEqualTo(root.get("id").get("statDt").as(String.class),statDtBegin));
                }
                if(StringUtils.isNotBlank(statDtEnd)) {
                    list.add(cb.lessThanOrEqualTo(root.get("id").get("statDt").as(String.class),statDtEnd));
                }
                if(StringUtils.isNotBlank(taskId)) {
                    list.add(cb.like(root.get("id").get("taskId").as(String.class),"%" + taskId + "%"));
                }
                if(StringUtils.isNotBlank(taskType)) {
                    list.add(cb.like(root.get("taskType").as(String.class),"%" + taskType + "%"));
                }
                if(StringUtils.isNotBlank(granularity)) {
                    list.add(cb.equal(root.get("granularity").as(String.class),granularity ));
                }
                if(StringUtils.isNotBlank(status)) {
                    list.add(cb.equal(root.get("status").as(String.class),status));
                }

                Predicate [] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));

            }
        });
        model.addAttribute("statDtBegin", statDtBegin);
        model.addAttribute("statDtEnd", statDtEnd);
        model.addAttribute("taskId", taskId);
        model.addAttribute("taskType", taskType);
        model.addAttribute("granularity", granularity);
        model.addAttribute("status", status);
        model.addAttribute("tasklist",taskFactList);
        return "tasklist";
    }


    @RequestMapping(value="/updateTaskStatus",method=RequestMethod.POST)
    public String updateTaskStatus(@RequestParam(required = false) String[] checkboxid,
                                    @RequestParam(required = false) String[] checkboxstatdt,
                                   @RequestParam(required = false) String submitStatus,
                                   @RequestParam(required = false) String statDtBegin ,
                                   @RequestParam(required = false) String statDtEnd ,
                                   @RequestParam(required = false) String taskId ,
                                   @RequestParam(required = false) String taskType ,
                                   @RequestParam(required = false) String granularity ,
                                   @RequestParam(required = false) String status,
                                     Model model) {
        List<TaskFact> list = new ArrayList<>();

        
        for(int i=0 ; i< checkboxid.length ; i++) {
            CompositeIdTaskFact taskFactId = new CompositeIdTaskFact();
            taskFactId.setTaskId(checkboxid[i]);
            taskFactId.setStatDt(checkboxstatdt[i]);
            TaskFact taskFact = taskFactDao.findOne(taskFactId);
            taskFact.setStatus(submitStatus);
            list.add(taskFact);
        }
        if(list.size() >0 ) {
            taskFactDao.save(list);
        }

        model.addAttribute("statDtBegin", statDtBegin);
        model.addAttribute("statDtEnd", statDtEnd);
        model.addAttribute("taskId", taskId);
        model.addAttribute("taskType", taskType);
        model.addAttribute("granularity", granularity);
        model.addAttribute("status", status);
        return "forward:/searchTasklist";
    }
}