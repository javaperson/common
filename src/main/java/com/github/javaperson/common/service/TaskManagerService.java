package com.github.javaperson.common.service;

import com.github.javaperson.common.Task;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public interface TaskManagerService {
    public void execute(Task task);


    public ScheduledFuture schedule(Task task, long delay, TimeUnit unit);

    public ScheduledFuture scheduleAtFixedRate(Task task, long initialDelay,
                                               long period, TimeUnit unit);

    public ScheduledFuture scheduleWithFixedDelay(Task task,
                                                  long initialDelay, long delay, TimeUnit unit);

}
