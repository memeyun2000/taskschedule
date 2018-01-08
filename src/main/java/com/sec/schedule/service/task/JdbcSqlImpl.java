package com.sec.schedule.service.task;

import com.sec.schedule.dao.TaskConfigDBDao;
import com.sec.schedule.dao.TaskImplJdbcSqlDao;
import com.sec.schedule.dict.TaskStatus;
import com.sec.schedule.entity.TaskConfigDB;
import com.sec.schedule.entity.TaskFact;
import com.sec.schedule.entity.TaskImplJdbcSql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("jdbcSql")
public class JdbcSqlImpl implements TaskService{
    public static Logger logger = LoggerFactory.getLogger(JdbcSqlImpl.class);

    @Autowired
    private TaskImplJdbcSqlDao jdbcSqlDao;
    @Autowired
    private TaskConfigDBDao taskConfigDBDao;

	@Override
	public String executeTask(TaskFact taskFact) {
        String retVal = TaskStatus.SUCCESS;
        TaskImplJdbcSql jdbcSql;
        TaskConfigDB taskConfigDB;

        Connection conn = null;
        Statement stmt = null;

        jdbcSql = jdbcSqlDao.findOne(taskFact.getId().getTaskId());
        taskConfigDB = taskConfigDBDao.findOne(jdbcSql.getDatabaseId());
        

        try {
            Class.forName(taskConfigDB.getDriver());
            conn = DriverManager.getConnection(taskConfigDB.getUrl(),
                                        taskConfigDB.getUsername(),
                                        taskConfigDB.getPasswd());
            stmt = conn.createStatement();
            logger.debug("exec-sql == " + jdbcSql.getSqlStr());
            stmt.execute(jdbcSql.getSqlStr());
            
        } catch (Exception e) {
            e.printStackTrace();
            retVal = TaskStatus.FAILED;
        } finally {
            try{
                stmt.close();
                conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return retVal;
	}

}