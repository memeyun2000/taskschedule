package com.sec.schedule.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sec.schedule.dao.TaskFactDao;
import com.sec.schedule.entity.TaskFact;
import com.sec.schedule.model.CompositeIdTaskFact;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskExecuteServiceTest {

    //-- test 1 --//
    @Autowired
    private TaskExecuteService taskExecuteService;

    @Test
    public void test1(){
        List<TaskFact> list = new ArrayList<TaskFact>();
        TaskFact taskFact = new TaskFact();
        taskFact.setGranularity("Y");
        taskFact.setId(new CompositeIdTaskFact());
        taskFact.getId().setStatDt("2017-01-01");

        taskFact = new TaskFact();
        taskFact.setGranularity("Y");
        taskFact.setId(new CompositeIdTaskFact());
        taskFact.getId().setStatDt("2017-01-01");
        list.add(taskFact);

        taskFact = new TaskFact();
        taskFact.setGranularity("D");
        taskFact.setId(new CompositeIdTaskFact());
        taskFact.getId().setStatDt("2017-01-01");
        list.add(taskFact);

        taskFact = new TaskFact();
        taskFact.setGranularity("M");
        taskFact.setId(new CompositeIdTaskFact());
        taskFact.getId().setStatDt("2017-01-01");
        list.add(taskFact);

        taskFact = new TaskFact();
        taskFact.setGranularity("M");
        taskFact.setId(new CompositeIdTaskFact());
        taskFact.getId().setStatDt("2017-01-01");
        list.add(taskFact);

        taskFact = new TaskFact();
        taskFact.setGranularity("S");
        taskFact.setId(new CompositeIdTaskFact());
        taskFact.getId().setStatDt("2017-01-01");
        list.add(taskFact);

        List<String> list2 = taskExecuteService.getDependStatDtList(list);
        for (String s : list2) {
            System.out.println(s);
        }

    }



    //--    test 2     --//
    // @Autowired
    TaskFactDao taskFactDao;
    // @Test
    public void test2(){
        List<String> tmpDtList = new ArrayList<String>();
        tmpDtList.add("2017-01-01");
        tmpDtList.add("2017-03-01");
        List<TaskFact> list = taskFactDao.findTaskFactListByStatDts(tmpDtList);
        System.out.println("----------------list size : " + list.size());
    }


    //--  test 3 --//
    //lambd for group by 
    @Test
    public void test3(){
        List<TaskFact> list = new ArrayList<TaskFact>();
        TaskFact taskFact = new TaskFact();
        taskFact.setGranularity("Y");
        taskFact.setId(new CompositeIdTaskFact());
        taskFact.getId().setStatDt("2017-03-01");
        list.add(taskFact);

        taskFact = new TaskFact();
        taskFact.setGranularity("Y");
        taskFact.setId(new CompositeIdTaskFact());
        taskFact.getId().setStatDt("2017-01-01");
        list.add(taskFact);

        taskFact = new TaskFact();
        taskFact.setGranularity("D");
        taskFact.setId(new CompositeIdTaskFact());
        taskFact.getId().setStatDt("2017-01-01");
        list.add(taskFact);

        taskFact = new TaskFact();
        taskFact.setGranularity("M");
        taskFact.setId(new CompositeIdTaskFact());
        taskFact.getId().setStatDt("2017-01-01");
        list.add(taskFact);

        taskFact = new TaskFact();
        taskFact.setGranularity("M");
        taskFact.setId(new CompositeIdTaskFact());
        taskFact.getId().setStatDt("2017-01-01");
        list.add(taskFact);

        taskFact = new TaskFact();
        taskFact.setGranularity("S");
        taskFact.setId(new CompositeIdTaskFact());
        taskFact.getId().setStatDt("2017-01-01");
        list.add(taskFact);

        Map<String,List<TaskFact>> s = list.stream()
            .collect(Collectors.groupingBy(t -> t.getId().getStatDt() , Collectors.toList()));

        s.get("2017-01-01").stream().forEach(v -> System.out.println(v.getGranularity()));
        System.out.println(s.size());
    }
}