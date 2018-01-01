package com.sec.schedule.service.task;

import com.sec.schedule.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sec.schedule.dao.TaskImplSparkJobDao;
import com.sec.schedule.dict.TaskStatus;
import com.sec.schedule.entity.TaskFact;
import com.sec.schedule.entity.TaskImplSparkJob;
import com.sec.schedule.utils.ExecUtils;

@Service("sparkJob")
public class SparkJobImpl implements TaskService {
    public static Logger logger = Logger.getLogger(SparkJobImpl.class);
    @Autowired
    TaskImplSparkJobDao sparkDao;

    @Override
    public String executeTask(TaskFact taskFact) {
        String retVal = TaskStatus.SUCCESS;

        TaskImplSparkJob sparkJob;
        try {
            sparkJob = sparkDao.findOne(taskFact.getId().getTaskId());
            //task_impl_cmd 查找 执行的命令
            String cmd = getCmd(sparkJob, taskFact);

            logger.debug(cmd);
            //TODO:
            // ExecUtils.exec(getCmd(sparkJob));
        } catch (Exception e) {
            e.printStackTrace();
            retVal = TaskStatus.FAILED;
        } finally {

        }
        return retVal;
    }


    private String getCmd(TaskImplSparkJob sparkJob, TaskFact taskFact) {
        String str = "spark-submit --master %s --deploy-mode %s --driver-memory %s --executor-memory %s --executor-cores %s --queue %s --class %s --name %s %s %s %s %s %s %s";
        String dateStrMonBegin = DateUtils.formatDate(DateUtils.getMonthBegin(DateUtils.stringToDateShort(taskFact.getId().getStatDt())), "yyyy-MM-dd");
        return String.format(str,
                sparkJob.getMaster() == null ? "yarn" : sparkJob.getMaster(),
                sparkJob.getDeployMode() == null ? "cluster" : sparkJob.getDeployMode(),
                sparkJob.getDriverMemory() == null ? "4" : sparkJob.getDriverMemory(),
                sparkJob.getExectorMemory() == null ? "2" : sparkJob.getExectorMemory(),
                sparkJob.getExectorCores() == null ? "1" : sparkJob.getExectorCores(),
                sparkJob.getQueue() == null ? "thequeue" : sparkJob.getQueue(),
                sparkJob.getClassname() == null ? "" : sparkJob.getClassname(),
                sparkJob.getTaskId() == null ? "" : sparkJob.getTaskId() + "-" + dateStrMonBegin,
                sparkJob.getJarName() == null ? "" : sparkJob.getJarName(),
                dateStrMonBegin,
                sparkJob.getArgs2() == null ? "" : sparkJob.getArgs1(),
                sparkJob.getArgs3() == null ? "" : sparkJob.getArgs2(),
                sparkJob.getArgs4() == null ? "" : sparkJob.getArgs3(),
                sparkJob.getArgs5() == null ? "" : sparkJob.getArgs4()
        );

    }

}