package com.github.javaperson.common.service.impl;

import com.github.javaperson.common.Task;
import com.github.javaperson.common.service.TaskManagerService;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleTaskManagerService extends ScheduledThreadPoolExecutor implements
        TaskManagerService
{
	/**
	 * Used to create a unique identifier for each task
	 */
	private AtomicInteger taskNum;

	public SimpleTaskManagerService(int corePoolSize)
	{
		super(corePoolSize);
		taskNum = new AtomicInteger(0);
	}

	@Override
	public void execute(Task task)
	{
		super.execute(task);
	}

	@Override
	public ScheduledFuture schedule(final Task task, long delay, TimeUnit unit)
	{
		task.setId(taskNum.incrementAndGet());
		return super.schedule(task, delay, unit);
	}

	@Override
	public ScheduledFuture scheduleAtFixedRate(Task task, long initialDelay,
			long period, TimeUnit unit)
	{
		task.setId(taskNum.incrementAndGet());
		return super.scheduleAtFixedRate(task, initialDelay, period, unit);
	}

	@Override
	public ScheduledFuture scheduleWithFixedDelay(Task task,
			long initialDelay, long delay, TimeUnit unit)
	{
		task.setId(taskNum.incrementAndGet());
		return super.scheduleWithFixedDelay(task, initialDelay, delay, unit);
	}

}
