package com.sec.schedule.lambdatest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class LambdaTest {

    @Test
    public void sortedTest() {
        List<String> list = new ArrayList<>();
        list.add("2017-03-02");
        list.add("2018-05-31");
        list.add("2015-01-02");
        list.add("2013-03-02");
        list.add("2017-09-02");
        list.add("2018-02-02");

        List<String> list2 = list.stream().sorted((dtStr1 , dtStr2) -> dtStr1.compareTo(dtStr2)).collect(Collectors.toList());
        for (String s : list2) {
            System.out.println(s);
        }
    }
}