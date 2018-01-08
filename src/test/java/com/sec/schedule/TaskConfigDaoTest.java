package com.sec.schedule;

import com.sec.schedule.dao.TaskConfigDao;
import com.sec.schedule.entity.TaskConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.config.Task;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guoqingyun on 2018/1/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskConfigDaoTest {

    @Autowired
    TaskConfigDao taskConfigDao;
    @Test
    public void transactionTest() {

        List<TaskConfig> list = new ArrayList();
        for (int i=0 ; i <10 ; i++) {
            TaskConfig taskConfig = new TaskConfig();
            taskConfig.setDictKey("hello world" + i );
            taskConfig.setDictValue("xixi" + i );

            list.add(taskConfig);
        }
        //加上下面这段 如果数据都未插入 则说明有事物存在
        TaskConfig taskConfig = new TaskConfig();
        taskConfig.setDictValue("hello world1jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
        taskConfig.setDictKey("hello world1");
        list.add(taskConfig);

//        taskConfigDao.save(list);

    }
}
