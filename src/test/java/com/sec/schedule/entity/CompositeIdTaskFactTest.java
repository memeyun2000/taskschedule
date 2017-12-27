package com.sec.schedule.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sec.schedule.model.CompositeIdTaskFact;

@RunWith(SpringJUnit4ClassRunner.class)
public class CompositeIdTaskFactTest {

    @Test
    public void equalsTest() {
        CompositeIdTaskFact id1 = new CompositeIdTaskFact();
        id1.setStatDt("2017-01-01");
        id1.setTaskId("Shell-A");

        CompositeIdTaskFact id2 = new CompositeIdTaskFact();
        id2.setStatDt("2017-01-01");
        id2.setTaskId("Shell-A");

        CompositeIdTaskFact id3 = new CompositeIdTaskFact();
        id3.setStatDt("2017-01-01");
        id3.setTaskId("Shell-B");

        System.out.println(id1.equals(id2));
        System.out.println(id1.equals(id3));
    }
}