package com.sec.schedule.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class ExecUtils{
    public static Logger logger = Logger.getLogger(ExecUtils.class);
    
    public static boolean exec(String cmdStr) throws Exception{
        boolean retVal = false;
        Runtime runtime = Runtime.getRuntime();
        String message = "";
        String errorMessage = "";

        BufferedReader br = null;
        BufferedReader errBr = null;
        try {
            logger.debug("execStr == " + cmdStr);
            Process process = runtime.exec(cmdStr);
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while((message = br.readLine()) != null) {
                logger.debug(message);
            }

            errBr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            if((errorMessage = errBr.readLine()) == null) {
                retVal = true;
            } else {
                logger.error(errorMessage);

                while((errorMessage = errBr.readLine()) != null) {
                    logger.error(errorMessage);
                }
                throw new Exception();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if(br != null) {
                    br.close();
                }
                if(errBr != null) {
                    errBr.close();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return retVal;
    }
}