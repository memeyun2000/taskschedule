package com.sec.schedule.service;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by guoqingyun on 2017/12/29.
 */
public interface TaskExecutorMonitorService extends Runnable{
    public void monitorThreadPool();

    public ThreadPoolExecutor getExecutor();

    public void setExecutor();
}
