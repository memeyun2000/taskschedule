package com.sec.schedule.utils;

import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * cmd:  mvn test -Dtest=DateUtilsTest#stringToDateTest
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class DateUtilsTest {

    @Test
    public void stringToDateTest(){
        String dateStr = "2017-11-01";
        Date date = DateUtils.stringToDate(dateStr, "yyyy-MM-dd");

        System.out.println(date);
    }
}