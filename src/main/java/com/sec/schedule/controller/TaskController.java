package com.sec.schedule.controller;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import com.sec.schedule.dao.TaskImplSparkJobDao;
import com.sec.schedule.entity.TaskImplSparkJob;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.sec.schedule.dao.TaskFactDao;
import com.sec.schedule.dict.TaskStatus;
import com.sec.schedule.entity.TaskFact;
import com.sec.schedule.model.CompositeIdTaskFact;
import com.sec.schedule.model.Message;
import com.sec.schedule.model.PageInfo;
import com.sec.schedule.model.TaskFactModel;
import com.sec.schedule.utils.DateUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Controller
public class TaskController extends BaseController{
    public static Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    TaskFactDao taskFactDao;

    @Autowired
    TaskImplSparkJobDao taskImplSparkJobDao;

    @RequestMapping("/tasklist")
    public String tasklist(@RequestParam(required=false,defaultValue="0") String newSearchFlag ,
                           @ModelAttribute(value="taskFactModel") TaskFactModel taskFactModel,
                           @ModelAttribute(value="pageInfo") PageInfo pageInfo,
                           Model model) {
        logger.debug("page:{},pageSize:{}", pageInfo.getPageNum(),pageInfo.getPageSize());
        
        Pageable page = getPageable(pageInfo);
        logger.debug("taskFactModel:{}" ,taskFactModel.getId().getStatDt());
        // List<TaskFact> taskFactList = null;
        Page<TaskFact> taskFactpage = null;
        
        //默认搜索内容
        if(newSearchFlag.equals("1")) {
            String statDt = DateUtils.getCurrentDateStr();
            //默认查询当天的任务
            taskFactModel.setStatDtBegin(statDt);
            taskFactModel.setStatDtEnd(statDt);
        } else {

        }
        
        //点击查询时触发的逻辑
        taskFactpage = taskFactDao.findAll(new Specification<TaskFact>() {
            @Override
            public Predicate toPredicate(Root<TaskFact> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(taskFactModel.getStatDtBegin())) {
                    list.add(cb.greaterThanOrEqualTo(root.get("id").get("statDt").as(String.class),taskFactModel.getStatDtBegin()));
                }
                if(StringUtils.isNotBlank(taskFactModel.getStatDtEnd())) {
                    list.add(cb.lessThanOrEqualTo(root.get("id").get("statDt").as(String.class),taskFactModel.getStatDtEnd()));
                }
                if(StringUtils.isNotBlank(taskFactModel.getId().getTaskId())) {
                    list.add(cb.like(root.get("id").get("taskId").as(String.class),"%" + taskFactModel.getId().getTaskId() + "%"));
                }
                if(StringUtils.isNotBlank(taskFactModel.getTaskType())) {
                    list.add(cb.like(root.get("taskType").as(String.class),"%" + taskFactModel.getTaskType() + "%"));
                }
                if(StringUtils.isNotBlank(taskFactModel.getGranularity())) {
                    list.add(cb.equal(root.get("granularity").as(String.class),taskFactModel.getGranularity() ));
                }
                if(StringUtils.isNotBlank(taskFactModel.getStatus())) {
                    list.add(cb.equal(root.get("status").as(String.class),taskFactModel.getStatus()));
                }

                Predicate [] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));

            }
        },page);
        
        
        // model.addAttribute("tasklist", taskFactList);
        model.addAttribute("page",taskFactpage);
        // model.addAttribute("page",page);
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

    @RequestMapping(value="/test",method=RequestMethod.GET)
    public String test(@ModelAttribute(value="message")Message message) {
        message.setInfo("hello world");
        return "taskSparkJobInfo";
    }

}