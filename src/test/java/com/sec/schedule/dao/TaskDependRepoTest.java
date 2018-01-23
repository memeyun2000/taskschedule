package com.sec.schedule.dao;

import java.util.Iterator;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskDependRepoTest {

    @Autowired
    TaskDependRepo taskDependRepo;

    @Test
    public void findAllPrevDependTaskTest() {
        Set<String> set = taskDependRepo.findAllPrevDependTask("PS_MSBD_HD_EMPL");

        Iterator<String> iter = set.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

    @Test
    public void findAllNextDependTaskTest() {
        Set<String> set = taskDependRepo.findAllNextDependTask("PS_MSBD_HD_EMPL");

        Iterator<String> iter = set.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}