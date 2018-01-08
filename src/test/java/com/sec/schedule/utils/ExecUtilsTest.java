package com.sec.schedule.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Test
    public  void stringFindTest() throws  Exception {
        String errorMessage = "xxxxxxxfewiejfiowjef\n" +  "Container exited with a non-zero exit code 1" + "\nExit code: 1\nfinal status: FAILEDy\nwejiwef";

        System.out.println(errorMessage);
        System.out.println(ExecUtils.isSparkExitCodeTrue(errorMessage));
    }
}