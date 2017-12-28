package com.sec.schedule.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ExecUtilsTest {

    @Test
    public void exec1Test() throws Exception{
        ExecUtils.exec("echo 'hello world'");
    }

    @Test
    public void exec2Test() throws Exception{
        ExecUtils.exec("joiwjeoifjwoiejfo");
    }
}