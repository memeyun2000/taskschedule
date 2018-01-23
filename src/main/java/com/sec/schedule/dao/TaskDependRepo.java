package com.sec.schedule.dao;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sec.schedule.entity.TaskDepend;


@Component
public class TaskDependRepo {
    @Autowired
    private TaskDependDao taskDependDao;

    public Set<String> findAllPrevDependTask(String taskId) {
        //已查找的任务列表
        Set<String> searchedSet = new HashSet<>();
        //前置依赖任务列表
        Set<String> set = new HashSet<>();

        searchedSet.add(taskId);
        List<TaskDepend> _list = taskDependDao.findDependTaskListByTaskId(taskId);

        Iterator<TaskDepend> _iter = _list.iterator();
        while (_iter.hasNext()) {
            String prevDependTaskId = _iter.next().getId().getDependTaskId();
            set.add(prevDependTaskId);

            //递归查找前置依赖任务
            if (!searchedSet.contains(prevDependTaskId)) {
                searchedSet.add(prevDependTaskId);
                findAllPrevDependTask(prevDependTaskId);
            }
        }
        return set;
    }

    public Set<String> findAllNextDependTask(String taskId) {
        //已查找的任务列表
        Set<String> searchedSet = new HashSet<>();
        //前置依赖任务列表
        Set<String> set = new HashSet<>();

        searchedSet.add(taskId);
        List<TaskDepend> _list = taskDependDao.findDependTaskListByDependTaskId(taskId);

        Iterator<TaskDepend> _iter = _list.iterator();
        while (_iter.hasNext()) {
            String nextDependTaskId = _iter.next().getId().getTaskId();
            set.add(nextDependTaskId);

            //递归查找前置依赖任务
            if (!searchedSet.contains(nextDependTaskId)) {
                searchedSet.add(nextDependTaskId);
                findAllNextDependTask(nextDependTaskId);
            }
        }
        return set;
    }
}