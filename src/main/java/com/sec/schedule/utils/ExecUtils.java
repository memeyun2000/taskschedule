package com.sec.schedule.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecUtils{
    public static Logger logger = LoggerFactory.getLogger(ExecUtils.class);
    
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
                logger.debug(errorMessage);

                while((errorMessage = errBr.readLine()) != null) {
                    logger.debug(errorMessage);
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

    public static boolean execSpakJob(String cmdStr) throws Exception{
        boolean retVal = true;
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
            if((errorMessage = errBr.readLine()) != null) {
                logger.debug(errorMessage);
                if(!isSparkExitCodeTrue(errorMessage)){
                    retVal = false;
                }

                while((errorMessage = errBr.readLine()) != null) {
                    logger.debug(errorMessage);
                    if(!isSparkExitCodeTrue(errorMessage)){
                        retVal = false;
                    }
                }
                if(retVal == false) {
                    // throw new Exception();
                }
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

    public static boolean isSparkExitCodeTrue(String message) {
        boolean retVal = true;
        Pattern pattern = Pattern.compile("(final status: )(\\w*)");
        Matcher matcher = pattern.matcher(message);

        if(matcher.find()) {
            String findStr = "";
            findStr = matcher.group(2);
            if(findStr.equals("FAILED")) {
                return false;
            } else {
                return true;
            }
        }
        return retVal;
    }
}