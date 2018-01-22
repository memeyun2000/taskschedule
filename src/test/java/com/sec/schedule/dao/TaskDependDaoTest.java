package com.sec.schedule.dao;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sec.schedule.entity.TaskDepend;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskDependDaoTest{

    @Autowired
    TaskDependDao taskDependDao;

    @Test
    public void test1(){
        List<TaskDepend> list = taskDependDao.findDependTaskListByDependTaskId("PS_MSBD_HD_EMPL");


        for(int i=0;i<list.size();i++) {
            System.out.println(list.get(i).getId().getTaskId());
        }

    }
}