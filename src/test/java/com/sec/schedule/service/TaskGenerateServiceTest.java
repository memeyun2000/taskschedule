package com.sec.schedule.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by guoqingyun on 2017/12/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskGenerateServiceTest {

    @Autowired
    private TaskGenerateService taskGenerateService;
    @Test
    public void checkStatDtGranularityTest() {
        List<String> list = taskGenerateService.checkStatDtGranularity("2017-03-31");
        for (String dt:list) {
            System.out.println(dt);
        }
    }
}
